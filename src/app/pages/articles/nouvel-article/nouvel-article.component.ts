import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ArticleDto} from '../../../../gs-api/src/models/article-dto';
import {CategoryDto} from '../../../../gs-api/src/models/category-dto';
import {ArticleService} from '../../../services/article.service';
import {CategorieService} from '../../../services/categorie.service';
import {PhotosService} from '../../../../gs-api/src/services/photos.service';
import SavePhotoParams = PhotosService.SavePhotoParams;

@Component({
  selector: 'app-nouvel-article',
  templateUrl: './nouvel-article.component.html',
  styleUrls: ['./nouvel-article.component.scss']
})
export class NouvelArticleComponent implements OnInit {
  articleDto: ArticleDto = {};
  categoryDto: CategoryDto = {};
  listCategorie: Array<CategoryDto> = [];
  errorMsg: Array<string> = [];
  imgUrl: string | ArrayBuffer = 'assets/product.png';
  file: File | null = null;

  constructor(private router: Router,
              private articleService: ArticleService,
              private activatredRoute: ActivatedRoute,
              private categorieService: CategorieService,
              private photoService: PhotosService) { }

  ngOnInit(): void {
    this.categorieService.findAllCategory()
        .subscribe(categories => {
          this.listCategorie = categories;
        });

    const idArticle = this.activatredRoute.snapshot.params.idArticle;
    if (idArticle){
      this.articleService.findArticleById(idArticle)
          .subscribe(article => {
              this.articleDto = article;
              this.categoryDto = this.articleDto.category ? this.articleDto.category : {};
          });
    }
  }

  cancel(): void {
    this.router.navigate(['articles']);
  }

  enregistrerArticle(): void {
    this.articleDto.category = this.categoryDto;
    this.articleService.enregistrerArticle(this.articleDto)
        .subscribe(art => {
          // Pour enregistrer la photo
          this.savePhoto(art.id, art.codeArticle);
        }, error => {
          this.errorMsg = error.error.errors;
        });
  }

    calculerTTC(): void {
      if (this.articleDto.prixUnitaireHT && this.articleDto.tauxTVA) {
        // PrixUnitaireTTC = PrixUnitaireHT + PrixUnitaireHT * TauxTVA / 100
        this.articleDto.prixUnitaireTTC =
           +this.articleDto.prixUnitaireHT + (+(this.articleDto.prixUnitaireHT * (this.articleDto.tauxTVA / 100)));
      }
    }
    // Cette méthode permet d'aploader une photo dans un formulaire
    onFileInput(files: FileList | null): void {
        if (files){
            this.file = files.item(0);
            if (this.file){
                const fileReader = new FileReader();
                fileReader.readAsDataURL(this.file);
                fileReader.onload = (event) => {
                    if (fileReader.result) {
                        this.imgUrl = fileReader.result;
                    }
                };
            }
        }
    }
    // Cette permet d'enregistrer une photo via le service photo généré.
    savePhoto(idArticle?: number, title?: string): void{
        if (idArticle && title && this.file) {
            const params: SavePhotoParams = {
                id: idArticle,
                file: this.file,
                titre: title,
                context: 'article'
            };
            this.photoService.savePhoto(params)
                .subscribe(photo => {
                   this.router.navigate(['articles']);
                });
        } else {
            this.router.navigate(['articles']);
        }
    }
}

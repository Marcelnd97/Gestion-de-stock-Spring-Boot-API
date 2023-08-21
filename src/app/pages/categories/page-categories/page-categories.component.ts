import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CategorieService} from '../../../services/categorie.service';
import {CategoryDto} from '../../../../gs-api/src/models/category-dto';

@Component({
  selector: 'app-page-categories',
  templateUrl: './page-categories.component.html',
  styleUrls: ['./page-categories.component.scss']
})
export class PageCategoriesComponent implements OnInit {
  listCategory: Array<CategoryDto> = [];
  selectedCatIdToDelete ? = -1;
  errorMsg = '';

  categorie = '';
  totalLength: any;
  page = 1;
  constructor(private router: Router,
              private categorieService: CategorieService) { }

  ngOnInit(): void {
    this.findAllCateg();
  }
  findAllCateg(): void{
    this.categorieService.findAllCategory().subscribe(resp => {
      this.listCategory = resp;
    });
  }
  confirmerEtSupprimerCat(): void {
    if (this.selectedCatIdToDelete !== -1){
      this.categorieService.delete(this.selectedCatIdToDelete)
          .subscribe(res => {
            this.findAllCateg();
          }, error => {
            this.errorMsg = error.error.message;
          });
    }
  }

  annulerSuppressionCat(): void {
    this.selectedCatIdToDelete = -1;
  }

  selectCatPourSupprimer(id?: number): void {
    this.selectedCatIdToDelete = id;
  }

  modifierCategory(id?: number): void{
    this.router.navigate(['nouvellecategorie', id]);
  }

  nouvelleCategory(): void {
    this.router.navigate(['nouvellecategorie']);
  }
}

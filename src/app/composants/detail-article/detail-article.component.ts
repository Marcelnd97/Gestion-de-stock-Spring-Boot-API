import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {ArticleService} from '../../services/article.service';

@Component({
  selector: 'app-detail-article',
  templateUrl: './detail-article.component.html',
  styleUrls: ['./detail-article.component.scss']
})
export class DetailArticleComponent implements OnInit {
  @Input()
  articleDto: ArticleDto = {};
  @Output()
  suppressionResult = new EventEmitter();
  constructor(private router: Router,
              private articleService: ArticleService) { }

  ngOnInit(): void {
  }

  modifierArticle(): void {
    this.router.navigate(['nouvelarticle', this.articleDto.id]);
  }

  confirmerEtSupprimerArt(): void {
    if (this.articleDto.id) {
      this.articleService.deleteArticleById(this.articleDto.id)
          .subscribe(deleteArt => {
            this.suppressionResult.emit('success');
          }, error => {
            this.suppressionResult.emit(error.error.error);

          });
    }
  }
}

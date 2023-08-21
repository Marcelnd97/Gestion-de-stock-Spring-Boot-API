import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ArticleDto} from '../../../../gs-api/src/models/article-dto';
import {ArticleService} from '../../../services/article.service';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-page-article',
  templateUrl: './page-article.component.html',
  styleUrls: ['./page-article.component.scss']
})
export class PageArticleComponent implements OnInit {
  errorMsg = '';
  listArticle: Array<ArticleDto> = [];
  totalLength: any;
  page = 1;

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  codeArticle = '';
  codeArticles = '';
  searchedArticle: ArticleDto = {};
  articleNotYetSelected = false;

  constructor(private router: Router,
              private articleService: ArticleService) { }

  ngOnInit(): void {
    this.findListArticle();
    this.dtOptions = {
      pagingType: 'full-number',
      pageLength: 5,
      processing: true
    };
  }

  findListArticle(): void{
    this.articleService.findAllArticle()
        .subscribe(articles => {
          this.listArticle = articles;
          // this.dtTrigger.next();
        });
  }

    nouvelArticle(): void {
        this.router.navigate(['nouvelarticle']);
    }
 // cette methode permet le rafraichissement de la page après la suppression d'un article.

    handleSuppression(event: any): void {
      if (event === 'success'){
        this.findListArticle();
      }else {
        this.errorMsg = event;
      }
    }

  filterArticleDetail(): void {
    if (this.codeArticle.length === 0) {
      this.findListArticle();
    }
  }

  selectArticleClick(article: ArticleDto): void {
    this.searchedArticle = article;
    this.codeArticle = article.codeArticle ? article.codeArticle : '';
    this.listArticle = this.listArticle.filter(art => art.codeArticle?.includes(this.codeArticle));
    this.articleNotYetSelected = true;
  }
}

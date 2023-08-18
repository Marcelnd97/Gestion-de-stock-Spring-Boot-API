import {Component, Input, OnInit} from '@angular/core';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {MouveStockDto} from '../../../gs-api/src/models/mouve-stock-dto';

@Component({
  selector: 'app-detail-mvt-stk-article',
  templateUrl: './detail-mvt-stk-article.component.html',
  styleUrls: ['./detail-mvt-stk-article.component.scss']
})
export class DetailMvtStkArticleComponent implements OnInit {
  @Input()
  mouvStockArticle: MouveStockDto = {};
  article: ArticleDto = {};
  constructor() { }

  ngOnInit(): void {
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {MouveStockDto} from '../../../gs-api/src/models/mouve-stock-dto';
import {MvstkService} from '../../services/mvstk.service';

@Component({
  selector: 'app-detail-mvt-stk-article',
  templateUrl: './detail-mvt-stk-article.component.html',
  styleUrls: ['./detail-mvt-stk-article.component.scss']
})
export class DetailMvtStkArticleComponent implements OnInit {
  @Input()
  mouvStockArticle: MouveStockDto = {};
  article: ArticleDto = {};
  corectionNeg = '';
  corectionPos = '';
  constructor(private mvstkService: MvstkService) { }

  ngOnInit(): void {
  }

    registreMvSt(): void {
    if (this.corectionNeg) {
      // @ts-ignore
      this.mvstkService.registreMouvStkNeg(this.mouvStockArticle)
          .subscribe(correctionStockNeg => {
            this.mouvStockArticle = correctionStockNeg;
          });
    }else if (this.corectionPos){
      // @ts-ignore
      this.mvstkService.registreMouvStkPos(this.mouvStockArticle)
          .subscribe(correctionStockPos => {
            this.mouvStockArticle = correctionStockPos;
          });
    }
  }
}

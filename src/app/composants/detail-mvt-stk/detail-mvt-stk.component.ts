import {Component, Input, OnInit} from '@angular/core';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {MouveStockDto} from '../../../gs-api/src/models/mouve-stock-dto';
import {MvstkService} from '../../services/mvstk.service';
import {TypeMouve} from '../typeMouve';

@Component({
  selector: 'app-detail-mvt-stk',
  templateUrl: './detail-mvt-stk.component.html',
  styleUrls: ['./detail-mvt-stk.component.scss']
})
export class DetailMvtStkComponent implements OnInit {
  @Input()
  detailmvstk: MouveStockDto = {};
  listDetailmvstk: Array<MouveStockDto> = [];

  readonly typeMouve = TypeMouve;

  constructor(private mouveService: MvstkService) { }

  ngOnInit(): void {
    this.getMouveStock();
  }

  getMouveStock(): void{
    this.mouveService.findAllMvstk()
        .subscribe(mouve => {
          this.listDetailmvstk = mouve;
        });
  }
}

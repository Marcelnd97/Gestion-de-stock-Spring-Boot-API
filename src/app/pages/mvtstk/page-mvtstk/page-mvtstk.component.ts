import { Component, OnInit } from '@angular/core';
import {MouveStockDto} from '../../../../gs-api/src/models/mouve-stock-dto';
import {MvstkService} from '../../../services/mvstk.service';

@Component({
  selector: 'app-page-mvtstk',
  templateUrl: './page-mvtstk.component.html',
  styleUrls: ['./page-mvtstk.component.scss']
})
export class PageMvtstkComponent implements OnInit {
  mouvStockArticle: Array<MouveStockDto> = [];
  constructor(private mvarticleService: MvstkService) { }

  ngOnInit(): void {
    this.getMouveStock();
  }

  getMouveStock(): void{
    this.mvarticleService.findAllMvstk()
        .subscribe(mouve => {
          this.mouvStockArticle = mouve;
        });
  }

}

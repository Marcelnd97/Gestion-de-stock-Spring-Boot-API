import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MouveStockDto} from '../../../../gs-api/src/models/mouve-stock-dto';
import {MvstkService} from '../../../services/mvstk.service';
import {jsPDF} from 'jspdf';

@Component({
  selector: 'app-page-mvtstk',
  templateUrl: './page-mvtstk.component.html',
  styleUrls: ['./page-mvtstk.component.scss']
})
export class PageMvtstkComponent implements OnInit {
  mouvStockArticle: Array<MouveStockDto> = [];
  codeArticles: any;
  totalLength: any;
  page = 1;

  @ViewChild('cardPdf', {static: false}) el!: ElementRef;

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

  mvstockPdf(): void {
    const pdf = new jsPDF('p', 'pt', 'a2', true);
    pdf.html(this.el.nativeElement, {
      // tslint:disable-next-line:no-shadowed-variable
      callback: (pdf) => {
        pdf.save('mouveStock.pdf');
      }
    });
  }

}

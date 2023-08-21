import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {MouveStockDto} from '../../gs-api/src/models/mouve-stock-dto';
import {MouveStockService} from '../../gs-api/src/services/mouve-stock.service';

@Injectable({
  providedIn: 'root'
})
export class MvstkService {

  constructor(private mouvstkService: MouveStockService) { }

  findAllMvstk(): Observable<MouveStockDto[]> {
      return this.mouvstkService.findAllMouveStock();
  }
  // @ts-ignore
  registreMouvStkNeg(corectStockNeg: MouveStockDto): Observable<MouveStockDto> {
      return this.mouvstkService.correctionStockPos(corectStockNeg);
  }
  registreMouvStkPos(corectStockPos: MouveStockDto): Observable<MouveStockDto> {
      return this.mouvstkService.correctionStockPos(corectStockPos);
  }
}

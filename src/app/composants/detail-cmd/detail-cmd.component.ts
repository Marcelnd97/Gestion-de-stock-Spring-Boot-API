import {Component, Input, OnInit} from '@angular/core';
import {LigneCommandeClientDto} from '../../../gs-api/src/models/ligne-commande-client-dto';

@Component({
  selector: 'app-detail-cmd',
  templateUrl: './detail-cmd.component.html',
  styleUrls: ['./detail-cmd.component.scss']
})
export class DetailCmdComponent implements OnInit {
  @Input()
  ligneCommande: LigneCommandeClientDto = {};
  totalId = 0;
  constructor() { }

  ngOnInit(): void {
    this.calculerTotalColonId();
  }
  calculerTotalColonId(): void {
    this.totalId = 0;
  }

}

import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CltfrsService} from '../../../services/cltfrs.service';
import {ClientDto} from '../../../../gs-api/src/models/client-dto';

@Component({
  selector: 'app-page-client',
  templateUrl: './page-client.component.html',
  styleUrls: ['./page-client.component.scss']
})
export class PageClientComponent implements OnInit {

  listClient: Array<ClientDto> = [];
  errorMsg = '';
  client = '';
  totalLength: any;
  page = 1;

  constructor(private router: Router,
              private cltFrsService: CltfrsService) { }

  ngOnInit(): void {
    this.findAllClients();
  }

  findAllClients(): void {
    this.cltFrsService.findAllClient()
        .subscribe(clients => {
          this.listClient = clients;
        });
  }

  nouveauClient(): void{
    this.router.navigate(['nouveauclient']);
  }

  // cette methode permet le rafraichissement de la page après la suppression d'un client.

  handleSuppression(event: any): void {
    if (event === 'success') {
      this.findAllClients();
    } else {
      this.errorMsg = event;
    }
  }
}

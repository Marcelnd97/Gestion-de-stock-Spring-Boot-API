import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-mage-cmd-clt-frs',
  templateUrl: './page-cmd-clt-frs.component.html',
  styleUrls: ['./page-cmd-clt-frs.component.scss']
})
export class PageCmdCltFrsComponent implements OnInit {
    origin = '';

  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data.origin;
    });
  }

  nouvelleCommande(): void{
    if (this.origin === 'client'){
    this.router.navigate(['nouvellecommandeclt']);
    }else if (this.origin === 'fournisseur'){
    this.router.navigate(['nouvellecommandefrs']);
    }
  }
}

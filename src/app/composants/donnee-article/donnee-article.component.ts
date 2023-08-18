import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-donnee-article',
  templateUrl: './donnee-article.component.html',
  styleUrls: ['./donnee-article.component.scss']
})
export class DonneeArticleComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  printPage(): void {
      window.print();
  }
}

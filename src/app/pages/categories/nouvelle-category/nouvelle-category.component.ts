import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CategorieService} from '../../../services/categorie.service';
import {CategoryDto} from '../../../../gs-api/src/models/category-dto';

@Component({
  selector: 'app-nouvelle-category',
  templateUrl: './nouvelle-category.component.html',
  styleUrls: ['./nouvelle-category.component.scss']
})
export class NouvelleCategoryComponent implements OnInit {
  categoryDto: CategoryDto = {};
  errorMsg: Array<string> = [];
  constructor(private router: Router,
              private categorieService: CategorieService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const idCategory = this.activatedRoute.snapshot.params.idCategory;
    if (idCategory){
      this.categorieService.findById(idCategory)
          .subscribe(cate => {
            this.categoryDto = cate;
          });
    }
  }

  enregistrerCategory(): void {
    this.categorieService.enregisterCategory(this.categoryDto).subscribe(resp => {
      this.router.navigate(['categories']);
    }, error => {
      this.errorMsg = error.error.errors;
    });
  }

  cancel(): void{
    this.router.navigate(['categories']);
  }
}

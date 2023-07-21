import { Injectable } from '@angular/core';
import {UserService} from './user.service';
import {Observable, of} from 'rxjs';
import {CategoryDto} from '../../gs-api/src/models/category-dto';
import {CategoryService} from '../../gs-api/src/services/category.service';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  constructor(private userService: UserService,
              private categoryService: CategoryService) { }

  enregisterCategory(categoryDto: CategoryDto): Observable<CategoryDto>{
    categoryDto.idEntreprise = this.userService.getConnectedUser()?.entreprise?.id;
    return this.categoryService.save(categoryDto);
  }
  findAllCategory(): Observable<CategoryDto[]>{
    return this.categoryService.findAll();
  }
  findById(idCategory: number): Observable<CategoryDto>{
    return this.categoryService.findById(idCategory);
  }

  delete(idCategory?: number): Observable<any> {
    if (idCategory){
      return this.categoryService.delete(idCategory);
    }
    return of();
  }
}

import { Injectable } from '@angular/core';
import {ArticlesService} from '../../gs-api/src/services/articles.service';
import {UserService} from './user.service';
import {ArticleDto} from '../../gs-api/src/models/article-dto';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private articlesService: ArticlesService,
              private userService: UserService) { }

  enregistrerArticle(articleDto: ArticleDto): Observable<ArticleDto> {
    articleDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id;
    return this.articlesService.save(articleDto);
  }

  findAllArticle(): Observable<ArticleDto[]> {
    return this.articlesService.findAll();
  }

  findArticleById(idArticle?: number): Observable<ArticleDto> {
    if (idArticle){
    return this.articlesService.findById(idArticle);
    }
    return of();
  }
  deleteArticleById(idArticle: number): Observable<any> {
    if (idArticle){
      return this.articlesService.delete(idArticle);
    }
    return of();
  }

    findArticleByCode(codeArticle: string): Observable<ArticleDto> {
        return this.articlesService.findByCodeArticle(codeArticle);
    }
}

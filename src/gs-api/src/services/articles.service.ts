/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { ArticleDto } from '../models/article-dto';
import { LigneCommandeClientDto } from '../models/ligne-commande-client-dto';
import { LigneCommandeFournisseurDto } from '../models/ligne-commande-fournisseur-dto';
import { LigneVentesDto } from '../models/ligne-ventes-dto';
@Injectable({
  providedIn: 'root',
})
class ArticlesService extends __BaseService {
  static readonly findAllPath = '/gestiondestock/v1/articles/all';
  static readonly findByCodeArticlePath = '/gestiondestock/v1/articles/codeART/{codeArticle}';
  static readonly savePath = '/gestiondestock/v1/articles/create';
  static readonly deletePath = '/gestiondestock/v1/articles/delete/{idArticle}';
  static readonly findArticleByIdCategoryPath = '/gestiondestock/v1/articles/filtre/category/{idCategory}';
  static readonly findHistoriqueCommandeClientPath = '/gestiondestock/v1/articles/historique/Commande/client/{idArticle}';
  static readonly findHistoriqueCommandeFournisseurPath = '/gestiondestock/v1/articles/historique/Commande/fournisseur/{idArticle}';
  static readonly findHistoriqueVentePath = '/gestiondestock/v1/articles/historique/vente/{idArticle}';
  static readonly findByIdPath = '/gestiondestock/v1/articles/{idArticle}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Cette méthode permet de rechercher et de renvoyer la liste des articles quiexistent dans la BDD
   * @return La liste des articles / une liste vide
   */
  findAllResponse(): __Observable<__StrictHttpResponse<Array<ArticleDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ArticleDto>>;
      })
    );
  }
  /**
   * Cette méthode permet de rechercher et de renvoyer la liste des articles quiexistent dans la BDD
   * @return La liste des articles / une liste vide
   */
  findAll(): __Observable<Array<ArticleDto>> {
    return this.findAllResponse().pipe(
      __map(_r => _r.body as Array<ArticleDto>)
    );
  }

  /**
   * Cette méthode permet de rechercher unarticles par son CODE
   * @param codeArticle undefined
   * @return L'article a été trouvé dans la BDD
   */
  findByCodeArticleResponse(codeArticle: string): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/codeART/${codeArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * Cette méthode permet de rechercher unarticles par son CODE
   * @param codeArticle undefined
   * @return L'article a été trouvé dans la BDD
   */
  findByCodeArticle(codeArticle: string): __Observable<ArticleDto> {
    return this.findByCodeArticleResponse(codeArticle).pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }

  /**
   * Cette méthode permet d'enregistrer ou modifier un article
   * @param body undefined
   * @return L'objet article créé ou modifié
   */
  saveResponse(body?: ArticleDto): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/articles/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * Cette méthode permet d'enregistrer ou modifier un article
   * @param body undefined
   * @return L'objet article créé ou modifié
   */
  save(body?: ArticleDto): __Observable<ArticleDto> {
    return this.saveResponse(body).pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }

  /**
   * Cette méthode permet de supprimer unarticle par ID dans la BDD
   * @param idArticle undefined
   * @return L'article a été supprimé
   */
  deleteResponse(idArticle: number): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/articles/delete/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * Cette méthode permet de supprimer unarticle par ID dans la BDD
   * @param idArticle undefined
   * @return L'article a été supprimé
   */
  delete(idArticle: number): __Observable<ArticleDto> {
    return this.deleteResponse(idArticle).pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }

  /**
   * @param idCategory undefined
   * @return successful operation
   */
  findArticleByIdCategoryResponse(idCategory: number): __Observable<__StrictHttpResponse<Array<ArticleDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/filtre/category/${idCategory}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ArticleDto>>;
      })
    );
  }
  /**
   * @param idCategory undefined
   * @return successful operation
   */
  findArticleByIdCategory(idCategory: number): __Observable<Array<ArticleDto>> {
    return this.findArticleByIdCategoryResponse(idCategory).pipe(
      __map(_r => _r.body as Array<ArticleDto>)
    );
  }

  /**
   * @param idArticle undefined
   * @return successful operation
   */
  findHistoriqueCommandeClientResponse(idArticle: number): __Observable<__StrictHttpResponse<Array<LigneCommandeClientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/historique/Commande/client/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneCommandeClientDto>>;
      })
    );
  }
  /**
   * @param idArticle undefined
   * @return successful operation
   */
  findHistoriqueCommandeClient(idArticle: number): __Observable<Array<LigneCommandeClientDto>> {
    return this.findHistoriqueCommandeClientResponse(idArticle).pipe(
      __map(_r => _r.body as Array<LigneCommandeClientDto>)
    );
  }

  /**
   * @param idArticle undefined
   * @return successful operation
   */
  findHistoriqueCommandeFournisseurResponse(idArticle: number): __Observable<__StrictHttpResponse<Array<LigneCommandeFournisseurDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/historique/Commande/fournisseur/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneCommandeFournisseurDto>>;
      })
    );
  }
  /**
   * @param idArticle undefined
   * @return successful operation
   */
  findHistoriqueCommandeFournisseur(idArticle: number): __Observable<Array<LigneCommandeFournisseurDto>> {
    return this.findHistoriqueCommandeFournisseurResponse(idArticle).pipe(
      __map(_r => _r.body as Array<LigneCommandeFournisseurDto>)
    );
  }

  /**
   * @param idArticle undefined
   * @return successful operation
   */
  findHistoriqueVenteResponse(idArticle: number): __Observable<__StrictHttpResponse<Array<LigneVentesDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/historique/vente/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<LigneVentesDto>>;
      })
    );
  }
  /**
   * @param idArticle undefined
   * @return successful operation
   */
  findHistoriqueVente(idArticle: number): __Observable<Array<LigneVentesDto>> {
    return this.findHistoriqueVenteResponse(idArticle).pipe(
      __map(_r => _r.body as Array<LigneVentesDto>)
    );
  }

  /**
   * Cette méthode permet de rechercher un article par son ID
   * @param idArticle undefined
   * @return L'article a été trouvé dans la BDD
   */
  findByIdResponse(idArticle: number): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/articles/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ArticleDto>;
      })
    );
  }
  /**
   * Cette méthode permet de rechercher un article par son ID
   * @param idArticle undefined
   * @return L'article a été trouvé dans la BDD
   */
  findById(idArticle: number): __Observable<ArticleDto> {
    return this.findByIdResponse(idArticle).pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }
}

module ArticlesService {
}

export { ArticlesService }

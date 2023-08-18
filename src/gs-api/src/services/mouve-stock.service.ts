/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { MouveStockDto } from '../models/mouve-stock-dto';
@Injectable({
  providedIn: 'root',
})
class MouveStockService extends __BaseService {
  static readonly correctionStockNegPath = '/gestiondestock/v1/mouveStock/correctionneg';
  static readonly correctionStockPosPath = '/gestiondestock/v1/mouveStock/correctionpos';
  static readonly entreeStockPath = '/gestiondestock/v1/mouveStock/entree';
  static readonly mvtStkArticlePath = '/gestiondestock/v1/mouveStock/filter/article/{idArticle}';
  static readonly findAllMouveStockPath = '/gestiondestock/v1/mouveStock/findAllMvstk';
  static readonly sortieStockPath = '/gestiondestock/v1/mouveStock/sortie';
  static readonly stockReelArticlePath = '/gestiondestock/v1/mouveStock/stockreel/{idArticle}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param body undefined
   * @return successful operation
   */
  correctionStockNegResponse(body?: MouveStockDto): __Observable<__StrictHttpResponse<MouveStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouveStock/correctionneg`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouveStockDto>;
      })
    );
  }
  /**
   * @param body undefined
   * @return successful operation
   */
  correctionStockNeg(body?: MouveStockDto): __Observable<MouveStockDto> {
    return this.correctionStockNegResponse(body).pipe(
      __map(_r => _r.body as MouveStockDto)
    );
  }

  /**
   * @param body undefined
   * @return successful operation
   */
  correctionStockPosResponse(body?: MouveStockDto): __Observable<__StrictHttpResponse<MouveStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouveStock/correctionpos`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouveStockDto>;
      })
    );
  }
  /**
   * @param body undefined
   * @return successful operation
   */
  correctionStockPos(body?: MouveStockDto): __Observable<MouveStockDto> {
    return this.correctionStockPosResponse(body).pipe(
      __map(_r => _r.body as MouveStockDto)
    );
  }

  /**
   * @param body undefined
   * @return successful operation
   */
  entreeStockResponse(body?: MouveStockDto): __Observable<__StrictHttpResponse<MouveStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouveStock/entree`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouveStockDto>;
      })
    );
  }
  /**
   * @param body undefined
   * @return successful operation
   */
  entreeStock(body?: MouveStockDto): __Observable<MouveStockDto> {
    return this.entreeStockResponse(body).pipe(
      __map(_r => _r.body as MouveStockDto)
    );
  }

  /**
   * @param idArticle undefined
   * @return successful operation
   */
  mvtStkArticleResponse(idArticle: number): __Observable<__StrictHttpResponse<Array<MouveStockDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/mouveStock/filter/article/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<MouveStockDto>>;
      })
    );
  }
  /**
   * @param idArticle undefined
   * @return successful operation
   */
  mvtStkArticle(idArticle: number): __Observable<Array<MouveStockDto>> {
    return this.mvtStkArticleResponse(idArticle).pipe(
      __map(_r => _r.body as Array<MouveStockDto>)
    );
  }

  /**
   * @return successful operation
   */
  findAllMouveStockResponse(): __Observable<__StrictHttpResponse<Array<MouveStockDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/mouveStock/findAllMvstk`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<MouveStockDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAllMouveStock(): __Observable<Array<MouveStockDto>> {
    return this.findAllMouveStockResponse().pipe(
      __map(_r => _r.body as Array<MouveStockDto>)
    );
  }

  /**
   * @param body undefined
   * @return successful operation
   */
  sortieStockResponse(body?: MouveStockDto): __Observable<__StrictHttpResponse<MouveStockDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/mouveStock/sortie`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<MouveStockDto>;
      })
    );
  }
  /**
   * @param body undefined
   * @return successful operation
   */
  sortieStock(body?: MouveStockDto): __Observable<MouveStockDto> {
    return this.sortieStockResponse(body).pipe(
      __map(_r => _r.body as MouveStockDto)
    );
  }

  /**
   * @param idArticle undefined
   * @return successful operation
   */
  stockReelArticleResponse(idArticle: number): __Observable<__StrictHttpResponse<number>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/mouveStock/stockreel/${idArticle}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: parseFloat((_r as HttpResponse<any>).body as string) }) as __StrictHttpResponse<number>
      })
    );
  }
  /**
   * @param idArticle undefined
   * @return successful operation
   */
  stockReelArticle(idArticle: number): __Observable<number> {
    return this.stockReelArticleResponse(idArticle).pipe(
      __map(_r => _r.body as number)
    );
  }
}

module MouveStockService {
}

export { MouveStockService }

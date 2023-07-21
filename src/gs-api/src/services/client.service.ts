/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { ClientDto } from '../models/client-dto';
import { ArticleDto } from '../models/article-dto';
@Injectable({
  providedIn: 'root',
})
class ClientService extends __BaseService {
  static readonly findAllPath = '/gestiondestock/v1/client/all';
  static readonly savePath = '/gestiondestock/v1/client/create';
  static readonly deletePath = '/gestiondestock/v1/client/delete/{idClient}';
  static readonly findByIdPath = '/gestiondestock/v1/client/{idClient}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @return successful operation
   */
  findAllResponse(): __Observable<__StrictHttpResponse<Array<ClientDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/client/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ClientDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll(): __Observable<Array<ClientDto>> {
    return this.findAllResponse().pipe(
      __map(_r => _r.body as Array<ClientDto>)
    );
  }

  /**
   * Cette méthode permet d'enregistrer ou modifier un client
   * @param body undefined
   * @return L'objet client créé ou modifié
   */
  saveResponse(body?: ClientDto): __Observable<__StrictHttpResponse<ArticleDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/client/create`,
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
   * Cette méthode permet d'enregistrer ou modifier un client
   * @param body undefined
   * @return L'objet client créé ou modifié
   */
  save(body?: ClientDto): __Observable<ArticleDto> {
    return this.saveResponse(body).pipe(
      __map(_r => _r.body as ArticleDto)
    );
  }

  /**
   * @param idClient undefined
   */
  deleteResponse(idClient: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/client/delete/${idClient}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * @param idClient undefined
   */
  delete(idClient: number): __Observable<null> {
    return this.deleteResponse(idClient).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @param idClient undefined
   * @return successful operation
   */
  findByIdResponse(idClient: number): __Observable<__StrictHttpResponse<ClientDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/client/${idClient}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ClientDto>;
      })
    );
  }
  /**
   * @param idClient undefined
   * @return successful operation
   */
  findById(idClient: number): __Observable<ClientDto> {
    return this.findByIdResponse(idClient).pipe(
      __map(_r => _r.body as ClientDto)
    );
  }
}

module ClientService {
}

export { ClientService }

/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { VenteDto } from '../models/vente-dto';
@Injectable({
  providedIn: 'root',
})
class VenteService extends __BaseService {
  static readonly findAllPath = '/gestiondestock/v1/vente/all';
  static readonly findVenteByCodePath = '/gestiondestock/v1/vente/codeV/{codeVente}';
  static readonly savePath = '/gestiondestock/v1/vente/create';
  static readonly deletePath = '/gestiondestock/v1/vente/delete/{idvente}';
  static readonly findByIdPath = '/gestiondestock/v1/vente/{idvente}';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @return successful operation
   */
  findAllResponse(): __Observable<__StrictHttpResponse<Array<VenteDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/vente/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<VenteDto>>;
      })
    );
  }
  /**
   * @return successful operation
   */
  findAll(): __Observable<Array<VenteDto>> {
    return this.findAllResponse().pipe(
      __map(_r => _r.body as Array<VenteDto>)
    );
  }

  /**
   * @param codeVente undefined
   * @return successful operation
   */
  findVenteByCodeResponse(codeVente: string): __Observable<__StrictHttpResponse<VenteDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/vente/codeV/${codeVente}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<VenteDto>;
      })
    );
  }
  /**
   * @param codeVente undefined
   * @return successful operation
   */
  findVenteByCode(codeVente: string): __Observable<VenteDto> {
    return this.findVenteByCodeResponse(codeVente).pipe(
      __map(_r => _r.body as VenteDto)
    );
  }

  /**
   * @param body undefined
   * @return successful operation
   */
  saveResponse(body?: VenteDto): __Observable<__StrictHttpResponse<VenteDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = body;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/gestiondestock/v1/vente/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<VenteDto>;
      })
    );
  }
  /**
   * @param body undefined
   * @return successful operation
   */
  save(body?: VenteDto): __Observable<VenteDto> {
    return this.saveResponse(body).pipe(
      __map(_r => _r.body as VenteDto)
    );
  }

  /**
   * @param idvente undefined
   */
  deleteResponse(idvente: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/gestiondestock/v1/vente/delete/${idvente}`,
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
   * @param idvente undefined
   */
  delete(idvente: number): __Observable<null> {
    return this.deleteResponse(idvente).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * @param idvente undefined
   * @return successful operation
   */
  findByIdResponse(idvente: number): __Observable<__StrictHttpResponse<VenteDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/gestiondestock/v1/vente/${idvente}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<VenteDto>;
      })
    );
  }
  /**
   * @param idvente undefined
   * @return successful operation
   */
  findById(idvente: number): __Observable<VenteDto> {
    return this.findByIdResponse(idvente).pipe(
      __map(_r => _r.body as VenteDto)
    );
  }
}

module VenteService {
}

export { VenteService }

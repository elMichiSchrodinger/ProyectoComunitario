import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {InsertarPropuesta} from '../../models/modelscrm/insertarpropuesta';
import {Observable} from 'rxjs';
import {InsertarGarantias} from '../../models/modelscrm/insertargarantias';
import {InsertarBeneficios} from '../../models/modelscrm/insertarbeneficios';

@Injectable({
  providedIn: 'root'
})
export class ElaborarpropuestaService {
  private apiUrl = 'http://localhost:8080/insertpro';
  private apiUrl1 = 'http://localhost:8080/insertga';
  private apiUrl2 = 'http://localhost:8080/insertbe';
  private idpropuesta!:string;

  constructor(private http: HttpClient) {}

  insertarPropuesta(propuesta: InsertarPropuesta): Observable<InsertarPropuesta> {
    return this.http.post<InsertarPropuesta>(this.apiUrl, propuesta);
  }
  insertarGarantia(garantia: InsertarGarantias): Observable<InsertarGarantias> {
    return this.http.post<InsertarGarantias>(this.apiUrl1, garantia);
  }
  insertarBeneficio(beneficio: InsertarBeneficios): Observable<InsertarBeneficios> {
    return this.http.post<InsertarBeneficios>(this.apiUrl2, beneficio);
  }
  setIdpropuesta(id_propuesta:string){
    this.idpropuesta=id_propuesta;
  }
  getIdpropuesta(){
    return this.idpropuesta;
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {InsertarpropuestaModel} from '../../models/modelscrm/insertarpropuesta.model';
import {Observable} from 'rxjs';
import {InsertargarantiasModel} from '../../models/modelscrm/insertargarantias.model';
import {InsertarbeneficiosModel} from '../../models/modelscrm/insertarbeneficios.model';

@Injectable({
  providedIn: 'root'
})
export class ElaborarpropuestaService {
  private apiUrl = 'http://localhost:8080/insertpro';
  private apiUrl1 = 'http://localhost:8080/insertga';
  private apiUrl2 = 'http://localhost:8080/insertbe';
  private idpropuesta!:string;

  constructor(private http: HttpClient) {}

  insertarPropuesta(propuesta: InsertarpropuestaModel): Observable<InsertarpropuestaModel> {
    return this.http.post<InsertarpropuestaModel>(this.apiUrl, propuesta);
  }
  insertarGarantia(garantia: InsertargarantiasModel): Observable<InsertargarantiasModel> {
    return this.http.post<InsertargarantiasModel>(this.apiUrl1, garantia);
  }
  insertarBeneficio(beneficio: InsertarbeneficiosModel): Observable<InsertarbeneficiosModel> {
    return this.http.post<InsertarbeneficiosModel>(this.apiUrl2, beneficio);
  }
  setIdpropuesta(id_propuesta:string){
    this.idpropuesta=id_propuesta;
  }
  getIdpropuesta(){
    return this.idpropuesta;
  }
}

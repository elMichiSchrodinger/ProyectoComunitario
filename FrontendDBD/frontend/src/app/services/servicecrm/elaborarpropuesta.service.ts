import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Insertarpropuesta} from '../../models/modelscrm/insertarpropuesta.model';
import {Observable} from 'rxjs';
import {Insertargarantias} from '../../models/modelscrm/insertargarantias.model';
import {Insertarbeneficios} from '../../models/modelscrm/insertarbeneficios.model';

@Injectable({
  providedIn: 'root'
})
export class ElaborarpropuestaService {
  private apiUrl = 'http://localhost:8080/insertpro';
  private apiUrl1 = 'http://localhost:8080/insertga';
  private apiUrl2 = 'http://localhost:8080/insertbe';
  private idpropuesta!:string;

  constructor(private http: HttpClient) {}

  insertarPropuesta(propuesta: Insertarpropuesta): Observable<Insertarpropuesta> {
    return this.http.post<Insertarpropuesta>(this.apiUrl, propuesta);
  }
  insertarGarantia(garantia: Insertargarantias): Observable<Insertargarantias> {
    return this.http.post<Insertargarantias>(this.apiUrl1, garantia);
  }
  insertarBeneficio(beneficio: Insertarbeneficios): Observable<Insertarbeneficios> {
    return this.http.post<Insertarbeneficios>(this.apiUrl2, beneficio);
  }
  setIdpropuesta(id_propuesta:string){
    this.idpropuesta=id_propuesta;
  }
  getIdpropuesta(){
    return this.idpropuesta;
  }
}

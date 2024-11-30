import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MostrarListaInv} from '../../models/modelscrm/listaclientesinv.model';

@Injectable({
  providedIn: 'root'
})
export class ListaclientesinvService {
  private apiUrl = 'http://localhost:8080/mostrarlistinv';
  private nombre!:string;
  private idcliente!:string;
  constructor(private http:HttpClient) { }
  mostrarlistainv(): Observable<MostrarListaInv[]> {
    return this.http.get<MostrarListaInv[]>(this.apiUrl);
  }
  private apiUrl1 = 'http://localhost:8080/mostrarlistpro';
  mostrarlistapro(): Observable<MostrarListaInv[]> {
    return this.http.get<MostrarListaInv[]>(this.apiUrl1);
  }
  setNombre(nombre:string){
    this.nombre=nombre;
  };
  getNombre(){
    return this.nombre;
  }
  setIdcliente(idcliente:string){
    this.nombre=idcliente;
  };
  getIdcliente(){
    return this.idcliente;
  }
}

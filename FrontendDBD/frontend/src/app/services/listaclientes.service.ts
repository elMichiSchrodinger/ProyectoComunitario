import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MostrarLista} from '../models/listaclientes.model';

@Injectable({
  providedIn: 'root'
})
export class ListaclientesService {
  private apiUrl = 'http://localhost:8080/mostrarlist';
  constructor(private http:HttpClient) { }
  mostrarlista(): Observable<MostrarLista[]> {
    return this.http.get<MostrarLista[]>(this.apiUrl);
  }
}

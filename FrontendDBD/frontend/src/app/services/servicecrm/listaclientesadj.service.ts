import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MostrarListaAdj} from '../../models/modelscrm/listaclientesadj.model';

@Injectable({
  providedIn: 'root'
})
export class ListaclientesadjService {
  private apiUrl = 'http://localhost:8080/mostrarlistadj';
  constructor(private http:HttpClient) { }
  mostrarlistaadj(): Observable<MostrarListaAdj[]> {
    return this.http.get<MostrarListaAdj[]>(this.apiUrl);
  }
}

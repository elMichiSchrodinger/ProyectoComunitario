import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Candidato} from '../models/candidato.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CandidatoService {
  private apiUrl="http://localhost:8080/candidatos";
  constructor(private http: HttpClient) { }
  crearCandidato(candidato: Candidato):Observable<Candidato> {
    return this.http.post<Candidato>(`${this.apiUrl}/crear`, candidato);
  }
}

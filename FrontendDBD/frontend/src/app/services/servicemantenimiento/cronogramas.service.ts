import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ListaCronogramas } from '../../models/modelsmantenimiento/lista-cronogramas';
import { Cronograma } from '../../models/modelsmantenimiento/cronograma';
@Injectable({
    providedIn: 'root'
})
export class CronogramasService {
    private apiUrl = 'http://localhost:8080/cmms/cronogramas';

    constructor(private http: HttpClient) { }

    obtenerCronogramas(): Observable<ListaCronogramas[]> {
        return this.http.get<ListaCronogramas[]>(this.apiUrl);
    }

    obtenerCronogramaPorId(id: string): Observable<Cronograma> {
        return this.http.get<Cronograma>(`${this.apiUrl}/${id}`);
    }
    completarCronograma(idCronograma: string): Observable<any> {
        return this.http.put(`${this.apiUrl}/${idCronograma}`, null, { responseType: 'text' });
    }

}
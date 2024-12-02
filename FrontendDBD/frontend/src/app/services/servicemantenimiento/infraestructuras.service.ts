import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ListaInfraestructuras } from '../../models/modelsmantenimiento/lista-infraestructuras';
import { Infraestructura } from '../../models/modelsmantenimiento/infraestructura';
@Injectable({
    providedIn: 'root'
})
export class InfraestructurasService {
    private apiUrl = 'http://localhost:8080/cmms/infraestructuras'; // Cambia la URL según tu API

    constructor(private http: HttpClient) { }

    obtenerInfraestructuras(): Observable<ListaInfraestructuras[]> {
        return this.http.get<ListaInfraestructuras[]>(this.apiUrl);
    }

    obtenerInfraestructuraPorId(id: string): Observable<Infraestructura> {
        return this.http.get<Infraestructura>(`${this.apiUrl}/${id}`);
    }

    agregarNuevaInfraestructura(infraestructura: Infraestructura): Observable<Infraestructura> {
        return this.http.post<Infraestructura>(this.apiUrl, infraestructura);
    }
}
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Insertarinvitacion} from '../../models/modelscrm/insertarinvitacion.model';
import {Observable} from 'rxjs';
import {Insertarrequerimientos} from '../../models/modelscrm/insertarrequerimientos.model';

@Injectable({
  providedIn: 'root'
})
export class ElaborarinvitacionService {
  private baseUrl = 'http://localhost:8080';
  private apiUrl = 'http://localhost:8080/insertreq';

  constructor(private http: HttpClient) {}

  insertarInvitacion(invitacion: Insertarinvitacion): Observable<Insertarinvitacion> {
    return this.http.post<Insertarinvitacion>(`${this.baseUrl}/insertinv`, invitacion);
  }
  insertRequerimiento(requerimiento: Insertarrequerimientos): Observable<Insertarrequerimientos> {
    return this.http.post<Insertarrequerimientos>(this.apiUrl, requerimiento);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {InsertarinvitacionModel} from '../../models/modelscrm/insertarinvitacion.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ElaborarinvitacionService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  insertarInvitacion(invitacion: InsertarinvitacionModel): Observable<InsertarinvitacionModel> {
    return this.http.post<InsertarinvitacionModel>(`${this.baseUrl}/insertinv`, invitacion);
  }
}

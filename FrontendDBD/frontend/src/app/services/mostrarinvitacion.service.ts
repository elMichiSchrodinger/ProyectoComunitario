import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MostrarInvitacion} from '../models/mostrarinvitacion.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MostrarinvitacionService {
  private apiUrl = 'http://localhost:8080/mostrarinv';
  private id_invitacion!:string;
  constructor(private http: HttpClient) {}

  obtenerInvitacion(id_invitacion: string): Observable<MostrarInvitacion> {
    return this.http.get<MostrarInvitacion>(`${this.apiUrl}/${id_invitacion}`);
  }
  setInvitacion(id_invitacion:string){
    this.id_invitacion=id_invitacion;
  }
  getInvitacion(){
    return this.id_invitacion;
  }
}

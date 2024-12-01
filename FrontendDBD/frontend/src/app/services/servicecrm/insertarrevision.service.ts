import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Insertarrevision} from '../../models/modelscrm/insertarrevision.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InsertarrevisionService {
  private apiUrl = 'http://localhost:8080/insertreva';
  private apiUrl2 = 'http://localhost:8080/insertrevr';
  private apiUrl1= 'http://localhost:8080';
  private apiUrl3= 'http://localhost:8080';
  private apiUrl4= 'http://localhost:8080';
  private idrevision!:string;

  constructor(private http: HttpClient) {}

  aceptarRevision(revision: Insertarrevision): Observable<Insertarrevision> {
    return this.http.post<Insertarrevision>(this.apiUrl, revision);
  }
  rechazarRevision(revision: Insertarrevision): Observable<Insertarrevision> {
    return this.http.post<Insertarrevision>(this.apiUrl2, revision);
  }
  aceptarInvitacion(id_invitacion: string): Observable<any> {
    const body = { id_invitacion };
    return this.http.put(`${this.apiUrl3}/aceptarinv`, body);
  }
  rechazarInvitacion(id_invitacion: string): Observable<any> {
    const body = { id_invitacion };
    return this.http.put(`${this.apiUrl1}/rechazarinv`, body);
  }
  completarInvitacion(id_invitacion: string): Observable<any> {
    const body = { id_invitacion };
    return this.http.put(`${this.apiUrl4}/completarinv`, body);
  }
  setIdrevision(id_revision:string){
    this.idrevision=id_revision;
  }
  getIdrevision(){
    return this.idrevision;
  }
}

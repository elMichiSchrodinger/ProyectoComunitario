import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Postulaciones} from '../models/postulaciones.model';
import {DetallePostulacion} from '../models/detallePostulacion.model';
import {Postulacion} from '../models/postulacion.model';

@Injectable({
  providedIn: 'root'
})
export class PostulacionesService {
  private apiUrl='http://localhost:8080/postulaciones';
  private postulacion!: Postulaciones;
  constructor(private http: HttpClient) {}
  setIdPostulacion(postulacion:Postulaciones){
    this.postulacion = postulacion;
  }
  getIdPostulacion(){
    return this.postulacion;
  }
  obtenerpostulaciones(area?:string, titulo?:string):Observable<Postulaciones[]>{
    let params = new HttpParams();
    if (area) params = params.set('area',area);
    if (titulo) params = params.set('titulo',titulo);

    return this.http.get<Postulaciones[]>(this.apiUrl, { params });
  }
  obtenerPostulacion(postulacion: Postulaciones):Observable<DetallePostulacion>{
    return this.http.get<DetallePostulacion>(`${this.apiUrl}/${postulacion.id_postulacion}`);
  }
  aprobarPostulacion(postulacion:Postulaciones):Observable<Postulaciones>{
    const url = `${this.apiUrl}/aprobar`;
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<Postulaciones>(url,postulacion,{headers});
  }
  desaprobarPostulacion(postulacion:Postulaciones):Observable<Postulaciones> {
    const url = `${this.apiUrl}/desaprobar`;
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.put<Postulaciones>(url,postulacion,{headers});
  }
  crearPostulacion(postulacion:Postulacion):Observable<Postulacion> {
    return this.http.post<Postulacion>(`${this.apiUrl}/crear`,postulacion);
  }
}

import { Injectable } from '@angular/core';
import {Empleado} from '../models/empleado.model';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  private apiUrl='http://localhost:8080/empleado';
  private empleado!: Empleado;
  constructor(private http: HttpClient) { }
  obtenerEmpleado(correo:string,password:string):Observable<Empleado>{
    let params = new HttpParams();
    if (correo) params = params.set('correo',correo);
    if (password) params = params.set('password',password);
    return this.http.get<Empleado>(`${this.apiUrl}`, { params });
  }
  setEmpleado(empleado:Empleado){
    this.empleado = empleado;
  }
  getidEmpleado(){
    return this.empleado.id_empleado;
  }
}

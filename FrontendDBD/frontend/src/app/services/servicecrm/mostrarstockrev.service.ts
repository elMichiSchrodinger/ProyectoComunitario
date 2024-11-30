import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MostrarStockRev} from '../../models/modelscrm/mostrarstockrev.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MostrarstockrevService {
  private apiUrl = 'http://localhost:8080/mostrarstock';

  constructor(private http: HttpClient) {}

  obtenerStockrev(): Observable<MostrarStockRev> {
    return this.http.get<MostrarStockRev>(this.apiUrl);
  }
}

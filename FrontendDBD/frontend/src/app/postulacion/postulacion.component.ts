import {Component, OnInit} from '@angular/core';
import {Postulaciones} from '../models/postulaciones.model';
import {PostulacionesService} from '../services/postulaciones.service';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-postulacion',
  standalone: true,
  imports: [
    FormsModule, CommonModule
  ],
  templateUrl: './postulacion.component.html',
  styleUrl: './postulacion.component.css'
})
export class PostulacionComponent implements OnInit {
  postulaciones: Postulaciones[]=[];
  filtro: string = '';
  constructor(private postulacionService: PostulacionesService, private router: Router) {

  }

  obtenerPostulaciones(area?:string, titulo?: string) {
    this.postulacionService.obtenerpostulaciones(area, titulo).subscribe({
      next: data =>{
        this.postulaciones = data;
      },
      error: error => {
        console.error('Error al obtener las postulaciones',error);
      }
    });
  }
  verPostulacion(postulacion: Postulaciones){
    this.router.navigate(['detallePostulacion']);
    this.postulacionService.setIdPostulacion(postulacion);
  }
  regresar(){
    this.router.navigate(['menu']);
  }
  ngOnInit() {
    this.postulacionService.obtenerpostulaciones().subscribe({
      next: data =>{
        this.postulaciones = data;
      },
      error: error => {
        console.error('Error al obtener las postulaciones',error);
      }
    });
  }
}

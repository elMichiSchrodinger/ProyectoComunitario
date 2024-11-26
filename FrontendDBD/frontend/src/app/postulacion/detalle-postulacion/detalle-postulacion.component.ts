import {Component, OnInit} from '@angular/core';
import {PostulacionesService} from '../../services/postulaciones.service';
import {DetallePostulacion} from '../../models/detallePostulacion.model';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {Postulaciones} from '../../models/postulaciones.model';

@Component({
  selector: 'app-detalle-postulacion',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './detalle-postulacion.component.html',
  styleUrl: './detalle-postulacion.component.css'
})
export class DetallePostulacionComponent implements OnInit {
  postulacion!:Postulaciones;
  detallePostulacion!: DetallePostulacion;
  contador:number = 0;
  constructor(private postulaService: PostulacionesService, private router: Router) {
    this.postulacion = this.postulaService.getIdPostulacion();
  }
  nextPage() {
    if (this.contador < 8) { // Asegúrate de que no exceda la última página
      this.contador++;
    } else {
      this.router.navigate(['postulacion']); // Navega a una nueva página si es el último paso
    }
  }

  backPage() {
    if (this.contador > 0) {
      this.contador--;
    } else {
      this.router.navigate(['postulacion']); // Regresa a la ruta principal si es la primera página
    }
  }
  aprobarPostulacion(){
    this.postulaService.aprobarPostulacion(this.postulacion).subscribe({
      next: response => {
        console.log('Postulación aprobada: ',response);
        alert('Postulación aprobada exitosamente')
      },
      error: error =>{
        console.error('Error al aprobar la postulación',error);
        alert('Error al aprobar la postulacion');
      }
    })
    this.router.navigate(['postulacion']);
  }

  desaprobarPostulacion(){
    this.postulaService.desaprobarPostulacion(this.postulacion).subscribe({
      next: response => {
        console.log('Postulacion rechazada: ',response);
        alert('Se ha rechazado exitosamente')
      },
      error: error =>{
        console.error('Error al rechazar la postulación',error);
        alert('Error al rechazar la postulacion');
      }
    })
    this.router.navigate(['postulacion']);
  }


  ngOnInit() {
    this.postulaService.obtenerPostulacion(this.postulaService.getIdPostulacion()).subscribe({
      next: data => {
        this.detallePostulacion = data;
      },
      error: error => {
        console.error('Error al obtener las postulaciones',error);
      }
      }
    )
  }
}

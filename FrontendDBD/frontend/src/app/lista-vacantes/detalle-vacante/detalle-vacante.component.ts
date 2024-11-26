import {Component, OnInit} from '@angular/core';
import {VacanteResumidaModel} from '../../models/vacanteResumida.model';
import {Vacante} from '../../models/vacante.model';
import {ListaVacantesService} from '../../services/lista-vacantes.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-detalle-vacante',
  standalone: true,
  imports: [],
  templateUrl: './detalle-vacante.component.html',
  styleUrl: './detalle-vacante.component.css'
})
export class DetalleVacanteComponent implements OnInit {
  detalleVacante!:Vacante;
  constructor(private vacanteService:ListaVacantesService, private router:Router) {
  }
  ngOnInit() {
    this.vacanteService.obtenerVacante(this.vacanteService.getVacante()).subscribe({
      next: data =>{
        this.detalleVacante = data;
      },
      error: error => {
        console.error('Error al obtener la vacante ', error);
      }
    })
  }
  redirigir(){
    this.router.navigate(['postulacionVacante']);
  }
  volver(){
    this.router.navigate(['listaVacantes']);
  }
}

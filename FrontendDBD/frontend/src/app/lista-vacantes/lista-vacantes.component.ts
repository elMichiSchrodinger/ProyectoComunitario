import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {VacanteResumidaModel} from '../models/vacanteResumida.model';
import {ListaVacantesService} from '../services/lista-vacantes.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-lista-vacantes',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf
    ],
  templateUrl: './lista-vacantes.component.html',
  styleUrl: './lista-vacantes.component.css'
})
export class ListaVacantesComponent implements OnInit {
  vacantes:VacanteResumidaModel[]=[];
  filtro:string='';
  constructor(private vacanteService:ListaVacantesService, private router:Router) {
  }
  obtenerVacantes(nombrePuesto?:string, area?:string){
    this.vacanteService.obtenerVacantes(nombrePuesto, area).subscribe({
      next: data =>{
        this.vacantes = data;
      },
      error: error => {
        console.error('Error al obtener las vacantes ', error);
      }
    })
  }
  verVacante(vacante:VacanteResumidaModel){
    this.vacanteService.setVacante(vacante);
    this.router.navigate(['detalleVacante']);
  }

  ngOnInit() {
    this.vacanteService.obtenerVacantes().subscribe({
      next: data =>{
        this.vacantes = data;
      },
      error: error => {
        console.error('Error al obtener las vacantes ', error);
      }
    })
  }
}

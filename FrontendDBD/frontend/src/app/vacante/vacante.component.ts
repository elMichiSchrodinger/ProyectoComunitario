import { Component } from '@angular/core';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {Vacante} from '../models/vacante.model';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {VacanteService} from '../services/vacante.service';
import {EmpleadoService} from '../services/empleado.service';

@Component({
  selector: 'app-vacante',
  standalone: true,
  imports: [
    NgIf,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './vacante.component.html',
  styleUrl: './vacante.component.css'
})
export class VacanteComponent {
  contador:number = 0;
  vacante: Vacante = {
    id_vacante: '',
    nombrepuesto: '',
    descripcionpuesto: '',
    area: '',
    ubicacion: '',
    tipocontrato: '',
    jornadalaboral: '',
    experiencia: 0,
    niveleducativo: '',
    idioma: '',
    habilidad: '',
    certificacion: '',
    salario: 0,
    bono: 0,
    beneficio: '',
    modalidad: '',
    fechainicio: new Date(),
    fechacierre: new Date()
  };
  constructor(private router: Router, private vacanteService:VacanteService,private empleadoService:EmpleadoService) {
  }
  nextPage(){
    if (this.contador <= 1){
      this.contador++;
    }
  }
  backPage(){
    if (this.contador > 0){
      this.contador--;
    }else {
      this.router.navigate(['menu']);
    }
  }
  crearVacante(vacante:Vacante){
    this.vacanteService.crearVacante(vacante,this.empleadoService.getidEmpleado()).subscribe({
      next: () => this.router.navigate(['menu']),
      error: error => {console.log('Error al crear vacante:',error);}
    });
  }
}

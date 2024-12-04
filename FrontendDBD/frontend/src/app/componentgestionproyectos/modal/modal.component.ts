import { Component, OnInit } from '@angular/core';
import { ServiceProyecto } from '../../services/servicegestionproyectos/ServiceProyecto';
import { ProyectosComponent } from '../proyectos/proyectos.component';
import {FormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { GestionProyecto } from './Proyecto';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent implements OnInit {
  disabled: boolean;
  receivedData: any;
  isUpdate: boolean;

  proyecto : GestionProyecto;
  constructor(private serviceProyecto: ServiceProyecto, private router: Router) {
    this.proyecto = new GestionProyecto("","","","",0);
    this.disabled = false;
    this.isUpdate = false;
  }
  crearProyecto(){
    try {
      this.disabled = true;
      if(this.isUpdate){
        this.serviceProyecto.actualizar(this.proyecto);
      }
      else{
        this.serviceProyecto.crear(this.proyecto);
      }
      this.router.navigate(['/Proyectos'], { state: { upsert: true } });
      this.disabled = false;
    } catch (error) {
      console.error('Error al obtener los proyectos:', error);
    }
  }
  ngOnInit(): void {
    this.receivedData = history.state.data;
    this.isUpdate = true;
    if(!this.receivedData){
      this.isUpdate = false;
      return;
    }
    console.log(this.receivedData);
    this.proyecto = new GestionProyecto(this.receivedData.idProyecto,
      this.receivedData.nombreProyecto, this.receivedData.fechaInicio, this.receivedData.fechaFin, this.receivedData.idEstado*1);
  }

}

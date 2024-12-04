import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {ListaclientesinvService} from '../services/servicecrm/listaclientesinv.service';
import {MostrarListaInv} from '../models/modelscrm/listaclientesinv.model';
import {MostrarinvitacionService} from '../services/servicecrm/mostrarinvitacion.service';
import {MostrarListaAdj} from '../models/modelscrm/listaclientesadj.model';
import {ListaclientesadjService} from '../services/servicecrm/listaclientesadj.service';
import {InsertarrevisionService} from '../services/servicecrm/insertarrevision.service';
import {EmpleadoService} from '../services/empleado.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  contadorModulo=0;
  mostrarListainv:MostrarListaInv[]=[];
  mostrarListaadj:MostrarListaAdj[]=[];
  mostrarListapro:MostrarListaInv[]=[];
  nombre!:string;
  irVistaProyecto(){
    this.contadorModulo = 2;
    this.router.navigate(['Proyectos']);
  }
  estadoReclutamiento(){
    this.contadorModulo=5;
  }
  constructor(private router: Router, private servicioCRM: ListaclientesinvService, private servicioCRM2: MostrarinvitacionService,
              private servicioCRM3: ListaclientesadjService, private servicioCRM4: InsertarrevisionService, private ser:EmpleadoService) {
  }
  crearVacante(){
    this.router.navigate(['vacante']);
  }
  revisarPostulacion(){
    this.router.navigate(['postulacion']);
  }
  enviarNotificacion(){
    this.router.navigate(['notificacion']);
  }
  volverInicio(){
    this.router.navigate(['']);
  }
  volvermenu(){
    this.contadorModulo=0;
  }
  estadoCRM(){
    this.contadorModulo=7;
    this.servicioCRM.mostrarlistainv().subscribe({
      next:data=>{
        this.mostrarListainv=data;
      },
      error:err => {
        console.log('Error al encontrar clientes',err)
      }
    })
    this.servicioCRM3.mostrarlistaadj().subscribe({
      next:data=>{
        this.mostrarListaadj=data;
      },
      error:err => {
        console.log('Error al encontrar clientes',err)
      }
    })
    this.servicioCRM.mostrarlistapro().subscribe({
      next:data=>{
        this.mostrarListapro=data;
      },
      error:err => {
        console.log('Error al encontrar clientes',err)
      }
    })
  }
  mostrarinvitacion(id_invitacion:string,estado:string,nombre:string,idcliente:string,idrevision:string){

    this.servicioCRM2.setInvitacion(id_invitacion);
    this.servicioCRM.setNombre(nombre);
    this.servicioCRM.setIdcliente(idcliente);
    this.servicioCRM4.setIdrevision(idrevision);
    if(estado=='No revisado'){
      this.router.navigate(['mostrarInvitacion']);
    }
    if(estado=='Aceptada'){
      this.router.navigate(['elaborarpropuesta']);
    }
  }
  mostraradjudicacion(estado:string,nombre:string){
    this.servicioCRM.setNombre(nombre);
    if (estado=='Aprobada'){
      this.router.navigate(['mostrarAdjA']);
    }
    if (estado=='Rechazada'){
      this.router.navigate(['mostrarAdjR']);
    }
  }
}

import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {ListaclientesinvService} from '../services/servicecrm/listaclientesinv.service';
import {MostrarListaInv} from '../models/modelscrm/listaclientesinv.model';
import {MostrarinvitacionService} from '../services/servicecrm/mostrarinvitacion.service';
import {MostrarListaAdj} from '../models/modelscrm/listaclientesadj.model';
import {ListaclientesadjService} from '../services/servicecrm/listaclientesadj.service';

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
  estadoReclutamiento(){
    this.contadorModulo=5;
  }
  constructor(private router: Router, private servicioCRM: ListaclientesinvService, private servicioCRM2: MostrarinvitacionService,
              private servicioCRM3: ListaclientesadjService) {
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
  }
  mostrarinvitacion(id_invitacion:string){
    this.servicioCRM2.setInvitacion(id_invitacion);
    this.router.navigate(['mostrarInvitacion']);
  }
  mostraradjudicacion(estado:string){
    if (estado=='Aprobada'){
      this.router.navigate(['mostrarAdjA']);
    }
    if (estado=='Rechazada'){
      this.router.navigate(['mostrarAdjR']);
    }
  }
}

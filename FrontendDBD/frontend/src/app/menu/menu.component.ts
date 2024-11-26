import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {ListaclientesService} from '../services/servicecrm/listaclientes.service';
import {MostrarLista} from '../models/modelscrm/listaclientes.model';
import {MostrarinvitacionService} from '../services/mostrarinvitacion.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  contadorModulo=0;
  mostrarLista:MostrarLista[]=[];
  estadoReclutamiento(){
    this.contadorModulo=5;
  }
  constructor(private router: Router, private servicioCRM: ListaclientesService, private servicioCRM2: MostrarinvitacionService) {
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
    this.servicioCRM.mostrarlista().subscribe({
      next:data=>{
        this.mostrarLista=data;
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
}

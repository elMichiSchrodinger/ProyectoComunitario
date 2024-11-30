import {Component, OnInit} from '@angular/core';
import {MostrarinvitacionService} from '../../services/servicecrm/mostrarinvitacion.service';
import {MostrarInvitacion} from '../../models/modelscrm/mostrarinvitacion.model';
import {MostrarrequerimientosService} from '../../services/servicecrm/mostrarrequerimientos.service';
import {MostrarRequerimientos} from '../../models/modelscrm/mostrarrequerimientos.model';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MostrarStockRev} from '../../models/modelscrm/mostrarstockrev.model';
import {MostrarstockrevService} from '../../services/servicecrm/mostrarstockrev.service';
import {ListaclientesinvService} from '../../services/servicecrm/listaclientesinv.service';
import {InsertarrevisionService} from '../../services/servicecrm/insertarrevision.service';
import {InsertarRevision} from '../../models/modelscrm/insertarrevision';
import {Router} from '@angular/router';

@Component({
  selector: 'app-mostrarinvitacion',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './mostrarinvitacion.component.html',
  styleUrl: './mostrarinvitacion.component.css'
})
export class MostrarinvitacionComponent implements OnInit {
  invitacion!:MostrarInvitacion;
  requerimiento!:MostrarRequerimientos[];
  stockrev!:MostrarStockRev;
  revision: InsertarRevision = {
    id_revision_tecnica:'',
    id_invitacion:'',
    id_informe_stock: ''
  };
  constructor(private servicio:MostrarinvitacionService,private servicio1:MostrarrequerimientosService,
              private servicio2:MostrarstockrevService,private servicio3:ListaclientesinvService, private servicio4:InsertarrevisionService,
              private router:Router) {
  }
  mostrarNombre(){
    return this.servicio3.getNombre();
  }

  ngOnInit() {
    this.revision.id_invitacion=this.servicio.getInvitacion();
    this.servicio.obtenerInvitacion(this.servicio.getInvitacion()).subscribe({
      next: data => {
        this.invitacion = data;
        this.servicio1.obtenerRequerimientos(this.invitacion.id_invitacion).subscribe({
          next: data=>{
            this.requerimiento=data;
          },
          error: error => {
            console.log(error);
          }
        })
      },
      error: error => {
        console.log(error);
      }
    });
    this.servicio2.obtenerStockrev().subscribe({
      next: data=> {
        this.stockrev = data;
        this.revision.id_informe_stock=data.id_informe_stock;
      },
      error: error => {
        console.log(error);
      }
    })
  }
  botonparticipar(){
    this.servicio4.aceptarRevision(this.revision).subscribe({
      next: data => {
        this.servicio4.setIdrevision(data.id_revision_tecnica);
        alert('Exito al aceptar la invitacion')
      },
      error: error => {
      console.log(error);
      }
    });
    this.servicio4.aceptarInvitacion(this.servicio.getInvitacion()).subscribe({
      next: ()=> {
        alert('Se reviso la invitacion')
      },
      error: error => {
        console.log(error);
      }
    });
    this.router.navigate(['menu']);
  }
  botonnoparticipar(){
    this.servicio4.rechazarRevision(this.revision).subscribe({
      next: data => {
        this.servicio4.setIdrevision(data.id_revision_tecnica);
        alert('Exito al rechazar la invitacion')
      },
      error: error => {
        console.log(error);
      }
    });
    this.servicio4.rechazarInvitacion(this.servicio.getInvitacion()).subscribe({
      next: ()=> {
        alert('Se reviso la invitacion')
      },
      error: error => {
        console.log(error);
      }
    });
    this.router.navigate(['menu']);
  }
  backPage(){
    this.router.navigate(['menu']);
  }
}

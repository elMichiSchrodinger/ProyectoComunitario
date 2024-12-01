import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {LogincliService} from '../../services/logincli.service';
import {SeguimientoinvService} from '../../services/servicecrm/seguimientoinv.service';
import {ElaborarinvitacionService} from '../../services/servicecrm/elaborarinvitacion.service';
import {Insertarinvitacion} from '../../models/modelscrm/insertarinvitacion.model';
import {Insertarrequerimientos} from '../../models/modelscrm/insertarrequerimientos.model';
import {Insertargarantias} from '../../models/modelscrm/insertargarantias.model';

@Component({
  selector: 'app-menucli',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menucli.component.html',
  styleUrl: './menucli.component.css'
})
export class MenucliComponent implements OnInit{
  contadorModulo=0;
  cteestadoinv!:string;
  id_invitacion!:string;
  invitacion: Insertarinvitacion = {
    id_invitacion: '',
    asunto_invitacion: '',
    tiempo_maximo: 0,
    direccion_proyecto: '',
    comentario: '',
    id_cliente: ''
  };
  requerimiento: Insertarrequerimientos = {
    descrip_requerimiento: '',
    id_invitacion: ''
  };
  requerimientos: string[] = [''];
  id_prueba='';
  constructor(private router: Router,private servicio:LogincliService,private servicio1:SeguimientoinvService, private servicio2: ElaborarinvitacionService) {
  }
  ngOnInit() {
    this.invitacion.id_cliente=this.servicio.getidLoginCli();
    this.id_prueba=this.invitacion.id_cliente;
    this.servicio1.obtenerSeguimientoinv(this.servicio.getidLoginCli()).subscribe({
      next: data=>{
        this.cteestadoinv=data.estado_invitacion;
        this.id_invitacion=data.id_invitacion;
      },
      error: error => {
        console.log(error);
      }
    });
  }
  agregarRequerimiento() {
    this.requerimientos.push('');
  }

  eliminarRequerimiento(index: number) {
    this.requerimientos.splice(index, 1);
  }

  enviarinvitacion(){
    this.servicio2.insertarInvitacion(this.invitacion).subscribe({
      next: (response) => {
        console.log('Respuesta del servidor:', response);
        this.requerimientos.forEach((descripcionRequerimiento) => {
          const requerimiento: Insertarrequerimientos = {
            descrip_requerimiento: descripcionRequerimiento,
            id_invitacion: response.id_invitacion
          };

          this.servicio2.insertRequerimiento(requerimiento).subscribe({
            next: () => {
              alert('Se inserto requerimiento');
            },
            error: (error) => {
              console.log(error);
            }
          });
        });
        alert('Se inserto invitacion');
      },
      error: (error) => {
        console.error('Error de generar invitacion:', error);
      }
    });
    this.contadorModulo=0;
  }
  trackByIndex(index: number, obj: any): any {
    return index;
  }
  volvermenu(){
    this.contadorModulo=0;
  }
  solicitarservicio(){
    this.contadorModulo=1;
  }
  estadoservicio(){
    if(this.cteestadoinv=='No revisado'){
      this.contadorModulo=2;
    }
    if(this.cteestadoinv=='Rechazada'){
      this.contadorModulo=3;
    }
    if(this.cteestadoinv=='Aceptada'){
      this.contadorModulo=4;
    }
    if(this.cteestadoinv=='Completada'){
      this.contadorModulo=5;
    }
  }
  volverInicio(){
    this.router.navigate(['']);
  }
  mostraridcliente(){
    return this.servicio.getidLoginCli();
  }
  mostrarnombrecliente(){
    return this.servicio.getnombreLoginCli();
  }

}

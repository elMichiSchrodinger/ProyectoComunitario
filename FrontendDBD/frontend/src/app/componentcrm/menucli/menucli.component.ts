import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {LogincliService} from '../../services/logincli.service';
import {SeguimientoinvService} from '../../services/servicecrm/seguimientoinv.service';
import {ElaborarinvitacionService} from '../../services/servicecrm/elaborarinvitacion.service';

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
  constructor(private router: Router,private servicio:LogincliService,private servicio1:SeguimientoinvService, private servicio2: ElaborarinvitacionService) {
  }
  ngOnInit() {
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

}

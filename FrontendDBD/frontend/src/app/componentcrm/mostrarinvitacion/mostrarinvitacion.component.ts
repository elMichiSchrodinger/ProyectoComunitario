import {Component, OnInit} from '@angular/core';
import {MostrarinvitacionService} from '../../services/servicecrm/mostrarinvitacion.service';
import {MostrarInvitacion} from '../../models/modelscrm/mostrarinvitacion.model';

@Component({
  selector: 'app-mostrarinvitacion',
  standalone: true,
  imports: [],
  templateUrl: './mostrarinvitacion.component.html',
  styleUrl: './mostrarinvitacion.component.css'
})
export class MostrarinvitacionComponent implements OnInit {
  invitacion!:MostrarInvitacion;
  constructor(private servicio:MostrarinvitacionService) {
  }
  ngOnInit() {
    this.servicio.obtenerInvitacion(this.servicio.getInvitacion()).subscribe({
      next: data => {
        this.invitacion = data;
      },
      error: error => {
        console.log(error);
      }
    })
  }
}

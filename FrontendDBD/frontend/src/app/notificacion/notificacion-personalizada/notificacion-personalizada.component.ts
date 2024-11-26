import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {NgIf} from '@angular/common';
import {DetalleCandidatoService} from '../../services/detalle-candidato.service';

@Component({
  selector: 'app-notificacion-personalizada',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './notificacion-personalizada.component.html',
  styleUrl: './notificacion-personalizada.component.css'
})
export class NotificacionPersonalizadaComponent {
  contador=0;
  constructor(private router:Router, private notificacionService:DetalleCandidatoService) {}
  backPage() {
    if (this.contador > 0) {
      this.contador--;
    } else {
      this.router.navigate(['notificacion']);
    }
  }
  getestado() {
    return this.notificacionService.getEstado();
  }

  gettelefono() {
    return this.notificacionService.getTelefono();
  }
}

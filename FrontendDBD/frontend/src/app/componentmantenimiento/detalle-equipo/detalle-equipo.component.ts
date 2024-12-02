import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { EquiposService } from '../../services/servicemantenimiento/equipos.service';
import { Equipo } from '../../models/modelsmantenimiento/equipo';
import { Falla } from '../../models/modelsmantenimiento/falla';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-detalle-equipo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './detalle-equipo.component.html',
  styleUrls: ['./detalle-equipo.component.css']
})
export class DetalleEquipoComponent implements OnInit {
  equipo!: Equipo;
  idEquipo!: string;
  falla: Falla = {
    idSolicitud: '',
    fechaSolicitud: null,
    urgencia: '',
    descripcion: '',
    idEmpleado: '',
    idEquipo: ''
  };
  modalAbierto: boolean = false;

  constructor(
    private equiposService: EquiposService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.idEquipo = this.route.snapshot.paramMap.get('id')!;
    this.cargarEquipo();
  }

  cargarEquipo(): void {
    this.equiposService.obtenerEquipoPorId(this.idEquipo).subscribe(data => {
      this.equipo = data;
    });
  }

  volver(): void {
    this.router.navigate(['/equipos']);
  }


  abrirModal(): void {
    this.modalAbierto = true; // Abrir el modal
  }

  cerrarModal(): void {
    this.modalAbierto = false; // Cerrar el modal
  }

  reportarFalla(): void {
    this.falla.idEquipo = this.idEquipo; // Asigna el ID del equipo
    this.equiposService.reportarFalla(this.falla, this.falla.idEquipo).subscribe({
      next: () => {
        alert('Falla reportada exitosamente.');
        this.cerrarModal(); // Cerrar el modal después de enviar el reporte
        this.falla = {
          idSolicitud: '',
          fechaSolicitud: null,
          urgencia: '',
          descripcion: '',
          idEmpleado: '',
          idEquipo: ''
        };
      },
      error: (err) => {
        alert('Error al reportar la falla: ' + err.message);
      }
    });
  }
}
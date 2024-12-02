import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { EquiposService } from '../../services/servicemantenimiento/equipos.service';
import { Equipo } from '../../models/modelsmantenimiento/equipo';
import { Falla } from '../../models/modelsmantenimiento/falla';
import { FormsModule } from '@angular/forms';
import { EmpleadoService } from '../../services/empleado.service';
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
    private router: Router,
    private empleadoService: EmpleadoService
  ) { }

  ngOnInit(): void {
    console.log('id: ');
    console.log(this.empleadoService.getidEmpleado());
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
    const idEmpleado = this.empleadoService.getidEmpleado();
    if (!idEmpleado) {
      console.error('No se puede reportar la falla, id_empleado no está disponible');
      alert('No se puede reportar la falla. Por favor, asegúrate de que has iniciado sesión correctamente.');
      return;
    }
    this.falla.idEmpleado = idEmpleado;
    this.falla.idEquipo = this.idEquipo;
    console.log(this.falla);
    console.log(this.falla.idEquipo);
    this.equiposService.reportarFalla(this.falla, this.idEquipo).subscribe({
      next: () => {
        alert('Falla reportada exitosamente.');
        this.cerrarModal();
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
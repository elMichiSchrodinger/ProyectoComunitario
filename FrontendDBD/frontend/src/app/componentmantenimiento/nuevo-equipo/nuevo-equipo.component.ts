import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EquiposService } from '../../services/servicemantenimiento/equipos.service';
import { Equipo } from '../../models/modelsmantenimiento/equipo';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-nuevo-equipo',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './nuevo-equipo.component.html',
  styleUrls: ['./nuevo-equipo.component.css']
})
export class NuevoEquipoComponent {
  nuevoEquipo: Equipo = {
    idEquipo: '',
    nombre: '',
    tipo: '',
    marca: '',
    modelo: '',
    serie: '',
    fechaAdquisicion: null,
    estado: '',
    ubicacion: '',
    frecuencia: 0,
    descripcion: '',
    ultimaRevision: null,
    proximaRevision: null
  };

  constructor(private equiposService: EquiposService, private router: Router) { }

  agregarEquipo(): void {
    this.equiposService.agregarNuevoEquipo(this.nuevoEquipo).subscribe({
      next: (data) => {
        alert('Equipo agregado exitosamente');
        this.router.navigate(['/equipos']);
      },
      error: (error) => {
        console.error('Error al agregar el equipo', error);
        alert('Hubo un error al agregar el equipo');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/equipos']);
  }
}
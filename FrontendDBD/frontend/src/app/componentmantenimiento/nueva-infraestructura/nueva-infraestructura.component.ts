import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { InfraestructurasService } from '../../services/servicemantenimiento/infraestructuras.service';
import { Infraestructura } from '../../models/modelsmantenimiento/infraestructura';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-nueva-infraestructura',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './nueva-infraestructura.component.html',
  styleUrl: './nueva-infraestructura.component.css'
})
export class NuevaInfraestructuraComponent {
  nuevaInfraestructura: Infraestructura = {
    idInfraestructura: '',
    nombre: '',
    fechaAdquisicion: null,
    ubicacion: '',
    estado: '',
    tipo: '',
    responsable: '',
    frecuenciaMantenimiento: 0,
    ultimoMantenimiento: null,
    proximoMantenimiento: null
  };

  constructor(private infraestructurasService: InfraestructurasService, private router: Router) { }

  agregarInfraestructura(): void {
    this.infraestructurasService.agregarNuevaInfraestructura(this.nuevaInfraestructura).subscribe({
      next: (data) => {
        alert('Infraestructura agregada exitosamente');
        this.router.navigate(['/infraestructuras']);
      },
      error: (error) => {
        console.error('Error al agregar la Infraestructura', error);
        alert('Hubo un error al agregar la Infraestructura');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/infraestructuras']);
  }

}

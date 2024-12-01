import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EquiposService } from '../../services/servicemantenimiento/equipos.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
@Component({
  selector: 'app-nuevo-equipo',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './nuevo-equipo.component.html',
  styleUrls: ['./nuevo-equipo.component.css']
})
export class NuevoEquipoComoponent {
  nuevoEquipoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private equiposService: EquiposService,
    private router: Router
  ) {
    this.nuevoEquipoForm = this.fb.group({
      nombre: ['', Validators.required],
      tipo: ['', Validators.required],
      marca: [''],
      modelo: [''],
      fechaAdquisicion: ['', Validators.required],
      estado: ['', Validators.required],
      ubicacion: ['']
    });
  }

  agregarEquipo(): void {
    if (this.nuevoEquipoForm.valid) {
      this.equiposService.agregarNuevoEquipo(this.nuevoEquipoForm.value).subscribe({
        next: () => {
          alert('Equipo agregado exitosamente');
          this.router.navigate(['/equipos']);
        },
        error: (error: string) => {
          console.error('Error al agregar el equipo:', error);
        }
      });
    } else {
      alert('Por favor, completa todos los campos requeridos.');
    }
  }
}
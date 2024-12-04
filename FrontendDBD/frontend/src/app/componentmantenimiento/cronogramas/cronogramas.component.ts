import { Component, OnInit } from '@angular/core';
import { CronogramasService } from '../../services/servicemantenimiento/cronogramas.service';
import { ListaCronogramas } from '../../models/modelsmantenimiento/lista-cronogramas';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-cronogramas',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './cronogramas.component.html',
  styleUrl: './cronogramas.component.css'
})
export class CronogramasComponent implements OnInit {
  cronogramas: ListaCronogramas[] = [];
  searchTerm: string = '';
  filteredCronogramas: ListaCronogramas[] = [];

  constructor(private cronogramasService: CronogramasService, private router: Router) { }

  ngOnInit(): void {
    this.cargarCronogramas();
  }

  cargarCronogramas(): void {
    this.cronogramasService.obtenerCronogramas().subscribe(data => {
      this.cronogramas = data;
      this.filteredCronogramas = data;
    });
  }

  buscarCronogramas(): void {
    this.filteredCronogramas = this.cronogramas.filter(cronograma =>
      cronograma.nombreEquipoInfraestructura.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  filtrarCompletados(): void {
    this.filteredCronogramas = this.cronogramas.filter(cronograma => cronograma.estado === 'C');
  }

  filtrarPendientes(): void {
    this.filteredCronogramas = this.cronogramas.filter(cronograma => cronograma.estado === 'P');
  }

  restablecerFiltro(): void {
    this.filteredCronogramas = this.cronogramas;
  }
  masInformacion(id: string): void {
    this.router.navigate(['/cronogramas', id]);
  }
  completarCronograma(id: string): void {
    this.cronogramasService.completarCronograma(id).subscribe({
      next: () => {
        // Si la solicitud fue exitosa, recargamos los cronogramas
        this.cargarCronogramas();
      },
      error: (err) => {
        // Manejo de errores
        console.error('Error al completar el cronograma:', err);
        // Aquí puedes mostrar un mensaje al usuario o realizar alguna acción adicional
      }
    });
  }
  regresarAlMenu() {
    this.router.navigate(['/menu']);
  }
}

import { Component, OnInit } from '@angular/core';
import { InfraestructurasService } from '../../services/servicemantenimiento/infraestructuras.service';
import { ListaInfraestructuras } from '../../models/modelsmantenimiento/lista-infraestructuras';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-infraestructuras',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './infraestructuras.component.html',
  styleUrl: './infraestructuras.component.css'
})

export class InfraestructurasComponent implements OnInit {
  infraestructuras: ListaInfraestructuras[] = [];
  searchTerm: string = '';
  filteredInfraestructuras: ListaInfraestructuras[] = [];

  constructor(private infraestructurasService: InfraestructurasService, private router: Router) { }

  ngOnInit(): void {
    this.cargarInfraestructuras();
  }

  cargarInfraestructuras(): void {
    this.infraestructurasService.obtenerInfraestructuras().subscribe(data => {
      this.infraestructuras = data;
      this.filteredInfraestructuras = data;
    });
  }

  buscarInfraestructuras(): void {
    this.filteredInfraestructuras = this.infraestructuras.filter(infraestructura =>
      infraestructura.nombre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  filtrarOperativos(): void {
    this.filteredInfraestructuras = this.infraestructuras.filter(infraestructura => infraestructura.estado === 'O');
  }

  filtrarMantenimiento(): void {
    this.filteredInfraestructuras = this.infraestructuras.filter(infraestructura => infraestructura.estado === 'M');
  }
  restablecerFiltro(): void {
    this.filteredInfraestructuras = this.infraestructuras;
  }
  masInformacion(id: string): void {
    this.router.navigate(['/infraestructuras', id]);
  }

  nuevaInfraestructura(): void {
    this.router.navigate(['/infraestructuras/nuevo']);
  }
}
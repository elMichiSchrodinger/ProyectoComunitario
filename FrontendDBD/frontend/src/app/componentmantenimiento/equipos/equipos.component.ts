import { Component, OnInit } from '@angular/core';
import { EquiposService } from '../../services/servicemantenimiento/equipos.service';
import { ListaEquipos } from '../../models/modelsmantenimiento/lista-equipos';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-equipos',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './equipos.component.html',
  styleUrl: './equipos.component.css'
})
export class EquiposComponent implements OnInit {
  equipos: ListaEquipos[] = [];
  searchTerm: string = '';
  filteredEquipos: ListaEquipos[] = [];

  constructor(private equiposService: EquiposService, private router: Router) { }

  ngOnInit(): void {
    this.cargarEquipos();
  }

  cargarEquipos(): void {
    this.equiposService.obtenerEquipos().subscribe(data => {
      this.equipos = data;
      this.filteredEquipos = data;
      console.log(this.equipos);
    });
  }

  buscarEquipos(): void {
    this.filteredEquipos = this.equipos.filter(equipo =>
      equipo.nombre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      equipo.tipo.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  masInformacion(id: string): void {
    this.router.navigate(['/equipos', id]);
  }

  nuevoEquipo(): void {
    this.router.navigate(['/equipos/nuevo']);
  }
}
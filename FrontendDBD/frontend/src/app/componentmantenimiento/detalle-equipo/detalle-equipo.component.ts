import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { EquiposService } from '../../services/servicemantenimiento/equipos.service';
import { Equipo } from '../../models/modelsmantenimiento/equipo';
@Component({
  selector: 'app-detalle-equipo',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalle-equipo.component.html',
  styleUrls: ['./detalle-equipo.component.css']
})
export class DetalleEquipoComponent implements OnInit {
  equipo!: Equipo;
  idEquipo!: string;

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
}
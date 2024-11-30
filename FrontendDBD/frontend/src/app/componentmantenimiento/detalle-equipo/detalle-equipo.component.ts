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
      this.equipo.fechaAdquisicion = new Date(this.equipo.fechaAdquisicion);
      this.equipo.ultimaRevision = new Date(this.equipo.ultimaRevision);
      this.equipo.proximaRevision = new Date(this.equipo.proximaRevision);
    });
  }

  volver(): void {
    this.router.navigate(['/equipos']);
  }
}
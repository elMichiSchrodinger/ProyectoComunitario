import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { InfraestructurasService } from '../../services/servicemantenimiento/infraestructuras.service';
import { Infraestructura } from '../../models/modelsmantenimiento/infraestructura';
@Component({
  selector: 'app-detalle-infraestructura',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalle-infraestructura.component.html',
  styleUrl: './detalle-infraestructura.component.css'
})
export class DetalleInfraestructuraComponent implements OnInit {
  infraestructura!: Infraestructura;
  idInfraestructura!: string;
  constructor(
    private infraestructurasService: InfraestructurasService,
    private route: ActivatedRoute,
    private router: Router
  ) { }
  ngOnInit(): void {
    this.idInfraestructura = this.route.snapshot.paramMap.get('id')!;
    this.cargarInfraestructura();
  }

  cargarInfraestructura(): void {
    this.infraestructurasService.obtenerInfraestructuraPorId(this.idInfraestructura).subscribe(data => {
      this.infraestructura = data;
    });
  }

  volver(): void {
    this.router.navigate(['/infraestructuras']);
  }
}

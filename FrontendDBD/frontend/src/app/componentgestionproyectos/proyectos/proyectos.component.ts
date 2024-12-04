import { Component, OnInit } from '@angular/core';
import { ServiceProyecto } from '../../services/servicegestionproyectos/ServiceProyecto';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-proyectos',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './proyectos.component.html',
  styleUrl: './proyectos.component.css'
})
export class ProyectosComponent {
  ListProyectos: any;
  receivedData: any;

  constructor(private serviceProyecto: ServiceProyecto, private router: Router, private cd: ChangeDetectorRef) {
    this.receivedData = null;
  }

  async ngOnInit() {
    /*this.receivedData = history.state.upsert;
    console.log('Verificar data :v :', this.receivedData);
    if (this.receivedData) {
      this.ListProyectos = await this.serviceProyecto.listar(); // Si listar() devuelve una promesa.
      console.log('ListProyectos:', this.ListProyectos);
    }*/

    console.log("hola");
    try {
      let list = await this.serviceProyecto.listar();
      this.ListProyectos = [... list]; // Si listar() devuelve una promesa.
      console.log('ListProyectos:', this.ListProyectos);
      this.cd.detectChanges();
    } catch (error) {
      console.error('Error al obtener los proyectos:', error);
    }
  }

  crearProyecto() {
    this.router.navigate(['/Proyectos/modal']);
  }

  actualizarProyecto(id: string) {
    const proyecto = this.ListProyectos.find((x:any)=>x.idProyecto == id);
    this.router.navigate(['/Proyectos/modal'], { state: { data: proyecto } });
    //this.ngOnInit();
  }

  eliminarProyecto(id: string) {
    console.log('Eliminar proyecto con ID:', id);
    // Confirmar antes de eliminar
    if (confirm('¿Estás seguro de que deseas eliminar este proyecto?')) {
      // Llamar al servicio para eliminar el proyecto
      this.serviceProyecto.eliminar(id).then(() => {
        console.log('Proyecto eliminado');
        this.ngOnInit(); // Recargar la lista de proyectos
      }).catch(error => {
        console.error('Error al eliminar el proyecto:', error);
      });
    }
  }

}

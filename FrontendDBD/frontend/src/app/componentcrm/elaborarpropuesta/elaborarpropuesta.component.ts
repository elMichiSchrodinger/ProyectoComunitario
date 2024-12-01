import {Component, OnInit} from '@angular/core';
import {InsertarpropuestaModel} from '../../models/modelscrm/insertarpropuesta.model';
import {ElaborarpropuestaService} from '../../services/servicecrm/elaborarpropuesta.service';
import {InsertargarantiasModel} from '../../models/modelscrm/insertargarantias.model';
import {InsertarbeneficiosModel} from '../../models/modelscrm/insertarbeneficios.model';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {InsertarrevisionService} from '../../services/servicecrm/insertarrevision.service';
import {ListaclientesinvService} from '../../services/servicecrm/listaclientesinv.service';
import {Router} from '@angular/router';
import {MostrarinvitacionService} from '../../services/servicecrm/mostrarinvitacion.service';

@Component({
  selector: 'app-elaborarpropuesta',
  standalone: true,
  imports: [FormsModule,CommonModule, ReactiveFormsModule],
  templateUrl: './elaborarpropuesta.component.html',
  styleUrl: './elaborarpropuesta.component.css'
})
export class ElaborarpropuestaComponent implements OnInit{
  propuesta: InsertarpropuestaModel = {
    id_presentacion_propuesta: '',
    precio_propuesto: 0,
    descripcion_tecnica: '',
    descripcion_economica: '',
    calidad_ofrecida: '',
    seguridad_ofrecida: '',
    condicion_pago: '',
    tiempo_ejecucion: '',
    observacion_propuesta: '',
    id_revision_tecnica: '',
    id_cliente: ''
  };
  garantia: InsertargarantiasModel = {
    descrip_garantia: '',
    id_presentacion_propuesta: '',
  };
  beneficio: InsertarbeneficiosModel = {
    descrip_beneficio: '',
    id_presentacion_propuesta: '',
  };
  id_pueba='';
  id_prueba2='';
  garantias: string[] = [''];
  beneficios: string[] = [''];
  constructor(private servicio: ElaborarpropuestaService,private servicio1:InsertarrevisionService,private servicio2:ListaclientesinvService,
              private router:Router, private servicio3:MostrarinvitacionService) {}

  mostrarNombre(){
    return this.servicio2.getNombre();
  }

  ngOnInit() {
    this.propuesta.id_revision_tecnica=this.servicio1.getIdrevision();
    this.propuesta.id_cliente=this.servicio2.getIdcliente();
    this.id_pueba=this.servicio1.getIdrevision();
    this.id_prueba2=this.propuesta.id_cliente;
  }

  agregarGarantia() {
    this.garantias.push('');
  }

  eliminarGarantia(index: number) {
    this.garantias.splice(index, 1);
  }

  agregarBeneficio() {
    this.beneficios.push('');
  }

  eliminarBeneficio(index: number) {
    this.beneficios.splice(index, 1);
  }

  enviarPropuesta(): void {
    this.servicio.insertarPropuesta(this.propuesta).subscribe({
      next: (response) => {
        console.log('Respuesta del servidor:', response);
        this.garantias.forEach((descripcionGarantia) => {
          const garantia: InsertargarantiasModel = {
            descrip_garantia: descripcionGarantia,
            id_presentacion_propuesta: response.id_presentacion_propuesta
          };

          this.servicio.insertarGarantia(garantia).subscribe({
            next: () => {
              alert('Se inserto garantia');
            },
            error: (error) => {
              console.log(error);
            }
          });
        });
        this.beneficios.forEach((descripcionBefenecio) => {
          const beneficio: InsertarbeneficiosModel = {
            descrip_beneficio: descripcionBefenecio,
            id_presentacion_propuesta: response.id_presentacion_propuesta
          };

          this.servicio.insertarBeneficio(beneficio).subscribe({
            next: () => {
              alert('Se inserto beneficio');
            },
            error: (error) => {
              console.log(error);
            }
          });
        });
        alert('Se inserto propuesta');
      },
      error: (error) => {
        console.error('Error del servidor:', error);
      }
    });
    this.servicio1.completarInvitacion(this.servicio3.getInvitacion()).subscribe({
      next: () => {
        alert('Exito al actualizar la invitacion')
      },
      error: error => {
        console.log(error);
      }
    });
    this.router.navigate(['menu']);
  }
  trackByIndex(index: number, obj: any): any {
    return index;
  }
  backPage(){
    this.router.navigate(['menu']);
  }
}

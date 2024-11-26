import { Component } from '@angular/core';
import {NgIf} from '@angular/common';
import {Vacante} from '../../../models/vacante.model';
import {Candidato} from '../../../models/candidato.model';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {CandidatoService} from '../../../services/candidato.service';
import {Postulacion} from '../../../models/postulacion.model';
import {ListaVacantesService} from '../../../services/lista-vacantes.service';
import {PostulacionesService} from '../../../services/postulaciones.service';

@Component({
  selector: 'app-postulacion-vacante',
  standalone: true,
  imports: [
    NgIf,
    FormsModule
  ],
  templateUrl: './postulacion-vacante.component.html',
  styleUrl: './postulacion-vacante.component.css'
})
export class PostulacionVacanteComponent {
  contado=0;
  vacante!:Vacante;
  candidato: Candidato = {
    id_candidato: '',
    nombre: '',
    correo: '',
    telefono: 0,
    fechanacimiento: new Date(), // Fecha por defecto, puedes ajustarla
    direccion: '',
    nacionalidad: '',
    documentoidentidad: '',
    redsocial: '',
    nivelestudio: '',
    titulo: '',
    institucioneducativa: '',
    certificacion: '',
    empresa: '',
    cargo: '',
    responsabilidad: '',
    referencialaboral: '',
    habilidadtecnica: '',
    idioma: '',
    habilidadblanda: '',
    nombreproyectoref: '',
    descripcionproyectoref: '',
    rolproyectoref: '',
  };
  postulacion: Postulacion = {
    id_postulacion: '',
    fechapostulacion: new Date(),
    estado: 1,
    id_vacante: '',
    id_candidato: '',
  };

  nextPage(){
    if (this.contado<5){
      this.contado++;
    }
  }
  backPage(){
    if (this.contado>0){
      this.contado--;
    }
  }
  postular() {

    this.candidatoService.crearCandidato(this.candidato).subscribe({
      next: candidatoCreado => {
        this.candidato = candidatoCreado;
        this.postulacion.id_candidato = this.candidato.id_candidato;
        this.postulacion.id_vacante = this.listaVacantes.getVacante().id_vacante;

        this.postulacionService.crearPostulacion(this.postulacion).subscribe({
          next: postulacionCreada => {
            console.log('Postulación creada:', postulacionCreada);
            alert('Postulación creada exitosamente');
          },
          error: error => {
            console.error('Error al crear la postulación:', error);
            alert('Hubo un error al crear la postulación.');
          }
        });
      },
      error: error => {
        console.error('Error al crear el candidato:', error);
        alert('Hubo un error al crear el candidato.');
      }
    });
    this.router.navigate(['listaVacantes']);
  }

  constructor(private router:Router, private candidatoService:CandidatoService, private listaVacantes:ListaVacantesService, private postulacionService:PostulacionesService) {
  }
}

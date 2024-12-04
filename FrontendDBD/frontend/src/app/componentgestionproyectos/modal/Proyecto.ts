export class GestionProyecto {
    idProyecto: string;
    nombreProyecto: string;
    fechaInicio: string;
    fechaFin: string;
    idEstado: number;
  
    constructor(
      idProyecto: string,
      nombreProyecto: string,
      fechaInicio: string,
      fechaFin: string,
      idEstado: number
    ) {
      this.idProyecto = idProyecto;
      this.nombreProyecto = nombreProyecto;
      this.fechaInicio = fechaInicio;
      this.fechaFin = fechaFin;
      this.idEstado = idEstado;
    }
  }
  
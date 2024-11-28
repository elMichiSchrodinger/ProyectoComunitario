export interface DetallePostulacion {
  id_postulacion: string;
  area: string;
  ubicacion: string;
  tipocontrato: string;
  jornadalaboral: string;
  experiencia: number;
  niveleducativo: string;
  vacante_habilidad: string;
  vacante_idioma: string;
  vacante_certificacion: string;
  salario: number;
  bono: number;
  beneficio: string;

  // Información personal
  nombre: string;
  fechanacimiento: Date;
  direccion: string;
  telefono: number;
  correo: string;
  nacionalidad: string;
  documentoidentidad: string;
  redsocial: string;
  nivelestudio: string;
  titulo: string;
  institucioneducativa: string;
  candidato_certificacion: string;
  empresa: string;
  cargo: string;
  responsabilidad: string;
  referencialaboral: string;
  habilidadtecnica: string;
  candidato_idioma: string;
  habilidadblanda: string;
  nombreproyectoref: string;
  descripcionproyectoref: string;
  rolproyectoref: string;
}


import { DatePipe } from "@angular/common";

export interface Equipo {
    idEquipo: string;
    nombre: string;
    tipo: string;
    marca?: string;
    modelo?: string;
    serie?: string;
    ubicacion?: string;
    fechaAdquisicion: Date;
    estado: string;
    frecuencia?: string;
    descripcion?: string;
    ultimaRevision: Date;
    proximaRevision: Date;

}

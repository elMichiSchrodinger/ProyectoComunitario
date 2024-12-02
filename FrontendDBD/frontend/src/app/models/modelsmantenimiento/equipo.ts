import { DatePipe } from "@angular/common";

export interface Equipo {
    idEquipo: string;
    nombre: string;
    tipo: string;
    marca?: string;
    modelo?: string;
    serie?: string;
    ubicacion?: string;
    fechaAdquisicion: Date | null;
    estado: string;
    frecuencia?: number;
    descripcion?: string;
    ultimaRevision: Date | null;
    proximaRevision: Date | null;

}

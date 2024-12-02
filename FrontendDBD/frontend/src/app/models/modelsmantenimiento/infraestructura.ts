import { Empleado } from "../empleado.model";

export interface Infraestructura {
    idInfraestructura: string;
    nombre: string;
    fechaAdquisicion: Date | null;
    ubicacion: string;
    estado: string;
    tipo: string;
    responsable: string;
    frecuenciaMantenimiento: number;
    ultimoMantenimiento: Date | null;
    proximoMantenimiento: Date | null;
}
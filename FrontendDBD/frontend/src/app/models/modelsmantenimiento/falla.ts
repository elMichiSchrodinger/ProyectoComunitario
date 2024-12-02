export interface Falla {
    idSolicitud: string;
    fechaSolicitud: Date | null;
    urgencia: string;
    descripcion: string;
    idEmpleado: string;
    idEquipo: string;
}
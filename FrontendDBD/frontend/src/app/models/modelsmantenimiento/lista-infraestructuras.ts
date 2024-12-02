export interface ListaInfraestructuras {
    idInfraestructura: string;
    nombre: string;
    fechaAdquisicion: Date | null;
    ubicacion: string;
    estado: string;
    tipo: string;
    ultimoMantenimiento: Date | null;
    proximoMantenimiento: Date | null;
}
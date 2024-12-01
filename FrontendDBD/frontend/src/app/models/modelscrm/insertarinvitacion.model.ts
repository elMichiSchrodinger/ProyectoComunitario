export interface InsertarinvitacionModel {
  id_invitacion: string;
  asunto_invitacion: string;
  tiempo_maximo: number;
  direccion_proyecto: string;
  comentario: string;
  estado_invitacion?: string;
  id_cliente: string;
}

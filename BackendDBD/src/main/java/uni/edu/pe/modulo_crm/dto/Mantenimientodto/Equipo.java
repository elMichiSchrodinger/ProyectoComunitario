package uni.edu.pe.modulo_crm.dto.Mantenimientodto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    private String idEquipo;
    private String nombre;
    private String tipo;
    private String marca;
    private String modelo;
    private String serie;
    private Date fechaAdquisicion;
    private String estado;
    private String ubicacion;
    private String frecuencia;
    private String descripcion;
    private String ultimaRevision;
    private String proximaRevision;
}
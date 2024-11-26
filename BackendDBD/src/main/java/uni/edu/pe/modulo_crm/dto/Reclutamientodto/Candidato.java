package uni.edu.pe.modulo_crm.dto.Reclutamientodto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor
public class Candidato {
    private String id_candidato;
    private String nombre;
    private String correo;
    private int telefono;
    private Date fechanacimiento;
    private String direccion;
    private String nacionalidad;
    private String documentoidentidad;
    private String redsocial;
    private String nivelestudio;
    private String titulo;
    private String institucioneducativa;
    private String certificacion;
    private String empresa;
    private String cargo;
    private String responsabilidad;
    private String referencialaboral;
    private String habilidadtecnica;
    private String idioma;
    private String habilidadblanda;
    private String nombreproyectoref;
    private String descripcionproyectoref;
    private String rolproyectoref;
}

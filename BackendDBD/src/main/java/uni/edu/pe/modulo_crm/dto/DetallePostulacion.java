package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class DetallePostulacion {
    private String id_postulacion;
    private String area;
    private String ubicacion;
    private String tipocontrato;
    private String jornadalaboral;
    private int experiencia;
    private String niveleducativo;
    private String vacante_habilidad;
    private String vacante_idioma;
    private String vacante_certificacion;
    private double salario;
    private double bono;
    private String beneficio;

    //Info personal
    private String nombre;
    private Date fechanacimiento;
    private String direccion;
    private int telefono;
    private String correo;
    private String nacionalidad;
    private String documentoidentidad;
    private String redsocial;
    private String nivelestudio;
    private String titulo;
    private String institucioneducativa;
    private String candidato_certificacion;
    private String empresa;
    private String cargo;
    private String responsabilidad;
    private String referencialaboral;
    private String habilidadtecnica;
    private String candidato_idioma;
    private String habilidadblanda;
    private String nombreproyectoref;
    private String descripcionproyectoref;
    private String rolproyectoref;
}

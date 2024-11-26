package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class Vacante {
    private String id_vacante;
    private String nombrepuesto;
    private String descripcionpuesto;
    private String area;
    private String ubicacion;
    private String tipocontrato;
    private String jornadalaboral;
    private int experiencia;
    private String niveleducativo;
    private String idioma;
    private String habilidad;
    private String certificacion;
    private double salario;
    private double bono;
    private String beneficio;
    private String modalidad;
    private Date fechainicio;
    private Date fechacierre;
}

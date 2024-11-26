package uni.edu.pe.modulo_crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class Postulacion {
    private String id_postulacion;
    private Date fechapostulacion;
    private int estado;
    private String id_vacante;
    private String id_candidato;
}

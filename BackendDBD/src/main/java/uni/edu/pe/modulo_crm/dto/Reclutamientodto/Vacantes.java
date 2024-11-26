package uni.edu.pe.modulo_crm.dto.Reclutamientodto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacantes {
    private String id_vacante;
    private String area;
    private String nombrepuesto;
    private double salario;
}

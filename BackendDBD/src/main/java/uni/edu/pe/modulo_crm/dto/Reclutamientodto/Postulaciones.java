package uni.edu.pe.modulo_crm.dto.Reclutamientodto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postulaciones {
    private String id_postulacion;
    private String nombre;
    private String area;
    private String titulo;
    private float salario;
}

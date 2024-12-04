package uni.edu.pe.modulo_crm.service.Inspeccionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Inspecciondto.MostrarLoteVerificacion;

import java.util.List;
import java.util.Map;

@Service
public class VerificacionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para obtener los lotes pendientes de verificación
    @Transactional(readOnly = true)
    public List<MostrarLoteVerificacion> obtenerLotesPendientesVerificacion() {
        String sql = "SELECT " +
                "    l.ID_Lote AS id_lote, " +
                "    l.Descripcion AS descripcion_del_lote, " +
                "    l.Cantidad_total AS cantidad_del_lote, " +
                "    l.Unidad_Medida AS unidad_de_medida, " +
                "    rve.Estado AS estado_verificacion " +
                "FROM " +
                "    Lote l " +
                "LEFT JOIN " +
                "    Registro_Verificacion rv ON l.ID_registro_verificacion = rv.ID_registro_verificacion " +
                "LEFT JOIN " +
                "    Registro_Verificacion_Estado rve ON rv.ID_registro_verificacion = rve.ID_registro_verificacion " +
                "WHERE " +
                "    rve.Estado = 'Pendiente';";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarLoteVerificacion.class));
    }

    // Método para obtener los detalles de un lote específico para verificación
    public List<Map<String, Object>> obtenerDetallesLoteVerificacion(String idLote) {
        String sql = "SELECT " +
                "    l.ID_Lote AS id_lote, " +
                "    l.Descripcion AS descripcion_del_lote, " +
                "    l.Cantidad_total AS cantidad_del_lote, " +
                "    l.Unidad_Medida AS unidad_de_medida, " +
                "    rve.Estado AS estado_verificacion " +
                "FROM " +
                "    Lote l " +
                "LEFT JOIN " +
                "    Registro_Verificacion rv ON l.ID_registro_verificacion = rv.ID_registro_verificacion " +
                "LEFT JOIN " +
                "    Registro_Verificacion_Estado rve ON rv.ID_registro_verificacion = rve.ID_registro_verificacion " +
                "WHERE " +
                "    rve.Estado = 'Pendiente' " +
                "    AND l.ID_Lote = ?;";

        // Ejecuta la consulta y devuelve los resultados como una lista de mapas
        return jdbcTemplate.queryForList(sql, idLote);
    }
}

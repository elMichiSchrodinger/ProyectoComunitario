package uni.edu.pe.modulo_crm.service.Inspeccionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Inspecciondto.MostrarResultadoInspeccion;

import java.util.List;
import java.util.Map;

@Service
public class ResultadoInspeccionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para obtener los lotes pendientes de inspección/verificación
    @Transactional(readOnly = true)
    public List<MostrarResultadoInspeccion> obtenerResultadosPendientes() {
        String sql = "SELECT " +
                "    l.ID_Lote AS lote, " +
                "    l.Descripcion AS descripcion_del_lote, " +
                "    rve.Estado AS estado_verificacion, " +
                "    ie.Estado AS estado_inspeccion, " +
                "    rife.Estado_Final AS estado_final " +
                "FROM " +
                "    Lote l " +
                "JOIN " +
                "    Recurso r ON r.ID_lote = l.ID_Lote " +
                "LEFT JOIN " +
                "    Registro_Verificacion rv ON l.ID_registro_verificacion = rv.ID_registro_verificacion " +
                "LEFT JOIN " +
                "    Registro_Verificacion_Estado rve ON rv.ID_registro_verificacion = rve.ID_registro_verificacion " +
                "LEFT JOIN " +
                "    Inspeccion i ON l.ID_inspeccion = i.ID_inspeccion " +
                "LEFT JOIN " +
                "    Inspeccion_Estado ie ON i.ID_inspeccion = ie.ID_inspeccion " +
                "LEFT JOIN " +
                "    Resultado_Inspeccion_Estado_Final rife ON i.ID_resultado_inspeccion = rife.ID_resultado_inspeccion " +
                "WHERE " +
                "    rife.Estado_Final = 'Pendiente';";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarResultadoInspeccion.class));
    }

    // Método para buscar un lote específico por ID
    public List<Map<String, Object>> buscarResultadoPorLote(String idLote) {
        String sql = "SELECT " +
                "    l.ID_Lote AS lote, " +
                "    l.Descripcion AS descripcion_del_lote, " +
                "    rve.Estado AS estado_verificacion, " +
                "    ie.Estado AS estado_inspeccion, " +
                "    rife.Estado_Final AS estado_final " +
                "FROM " +
                "    Lote l " +
                "JOIN " +
                "    Recurso r ON r.ID_lote = l.ID_Lote " +
                "LEFT JOIN " +
                "    Registro_Verificacion rv ON l.ID_registro_verificacion = rv.ID_registro_verificacion " +
                "LEFT JOIN " +
                "    Registro_Verificacion_Estado rve ON rv.ID_registro_verificacion = rve.ID_registro_verificacion " +
                "LEFT JOIN " +
                "    Inspeccion i ON l.ID_inspeccion = i.ID_inspeccion " +
                "LEFT JOIN " +
                "    Inspeccion_Estado ie ON i.ID_inspeccion = ie.ID_inspeccion " +
                "LEFT JOIN " +
                "    Resultado_Inspeccion_Estado_Final rife ON i.ID_resultado_inspeccion = rife.ID_resultado_inspeccion " +
                "WHERE " +
                "    rife.Estado_Final = 'Pendiente' " +
                "    AND l.ID_Lote = ?;";

        return jdbcTemplate.queryForList(sql, idLote);
    }
}

package uni.edu.pe.modulo_crm.service.Inspeccionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Inspecciondto.MostrarLoteInspeccion;

import java.util.List;

@Service
public class InspeccionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para obtener lotes con inspecciones pendientes
    @Transactional(readOnly = true)
    public List<MostrarLoteInspeccion> obtenerLotesPendientes() {
        String sql = "SELECT " +
                "    l.ID_lote AS lote, " +
                "    l.Descripcion AS descripcion_del_lote, " +
                "    l.Cantidad_total AS cantidad_del_lote, " +
                "    l.Unidad_Medida AS unidad_de_medida, " +
                "    i.Estado AS estado_inspeccion " +
                "FROM " +
                "    Lote l " +
                "LEFT JOIN " +
                "    Inspeccion i ON l.ID_inspeccion = i.ID_inspeccion " +
                "WHERE " +
                "    i.Estado = 'Pendiente';";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarLoteInspeccion.class));
    }
}

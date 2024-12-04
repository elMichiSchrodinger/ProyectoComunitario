package uni.edu.pe.modulo_crm.service.Mantenimientoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Cronograma;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaCronogramas;

import java.util.List;

@Service
public class CronogramasService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ListaCronogramas> obtenerCronogramas() {
        String sql = "SELECT cm.ID_cronograma AS idCronograma, COALESCE(e.Nombre, i.Nombre) AS nombreEquipoInfraestructura, cm.fecha_inicio AS fechaInicio, cm.fecha_fin AS fechaFin, "
                +
                "cm.estado AS estado, cm.descripcion AS descripcion FROM cronograma__de_mantenimiento cm LEFT JOIN equipo e ON cm.ID_equipo=e.ID_equipo "
                +
                "LEFT JOIN infraestructura i ON cm.ID_infraestructura=i.ID_infraestructura ORDER BY cm.fecha_inicio DESC;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListaCronogramas.class));
    }

    @Transactional
    public int actualizarEstado(String idCronograma) {
        String sql = "UPDATE cronograma__de_mantenimiento SET estado = 'C' WHERE id_cronograma = ?";
        return jdbcTemplate.update(sql, idCronograma);
    }
}
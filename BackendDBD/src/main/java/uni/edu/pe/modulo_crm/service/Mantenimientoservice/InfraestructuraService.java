package uni.edu.pe.modulo_crm.service.Mantenimientoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Infraestructura;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaInfraestructuras;

import java.util.List;

@Service
public class InfraestructuraService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ListaInfraestructuras> obtenerInfraestructuras() {
        String sql = "SELECT i.ID_infraestructura AS idInfraestructura, i.nombre AS nombre, i.fecha_adquisicion AS fechaAdquisicion, i.ubicacion AS ubicacion, "
                + "i.estado AS estado, i.tipo AS tipo, "
                + "COALESCE((SELECT cm.fecha_fin FROM cronograma__de_mantenimiento cm WHERE cm.ID_infraestructura = i.ID_infraestructura AND cm.fecha_fin IS NOT NULL ORDER BY cm.id_cronograma DESC LIMIT 1), i.fecha_adquisicion) AS ultimoMantenimiento,"
                + "(SELECT cm.fecha_inicio FROM cronograma__de_mantenimiento cm WHERE cm.ID_infraestructura = i.ID_infraestructura ORDER BY cm.id_cronograma DESC LIMIT 1) AS proximoMantenimiento "
                + "FROM infraestructura i;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListaInfraestructuras.class));
    }

    @Transactional(readOnly = true)
    public Infraestructura obtenerInfraestructuraPorId(String idInfraestructura) {
        String sql = "SELECT i.ID_infraestructura AS idInfraestructura, i.Nombre, i.fecha_adquisicion AS fechaAdquisicion, i.Ubicacion, i.Estado, i.Tipo, i.ID_empleado AS responsable, i.frecuencia_mantenimiento AS frecuenciaMantenimiento, "
                + "COALESCE((SELECT cm.fecha_fin FROM cronograma__de_mantenimiento cm WHERE cm.ID_infraestructura = i.ID_infraestructura AND cm.fecha_fin IS NOT NULL ORDER BY cm.id_cronograma DESC LIMIT 1), i.fecha_adquisicion) AS ultimoMantenimiento, "
                + "(SELECT cm.fecha_inicio FROM cronograma__de_mantenimiento cm WHERE cm.ID_infraestructura = i.ID_infraestructura ORDER BY cm.id_cronograma DESC LIMIT 1) AS proximoMantenimiento "
                + "FROM infraestructura i "
                + "WHERE i.ID_infraestructura = ?;";
        return jdbcTemplate.queryForObject(sql, new Object[] { idInfraestructura },
                new BeanPropertyRowMapper<>(Infraestructura.class));
    }
}
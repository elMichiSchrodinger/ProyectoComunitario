package uni.edu.pe.modulo_crm.service.GestionProyectosservice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uni.edu.pe.modulo_crm.dto.GestionProyectosdto.GestionProyectoDTO;

import java.util.List;

@Service
public class GestionProyectosService {
    private final JdbcTemplate jdbcTemplate;

    public GestionProyectosService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GestionProyectoDTO> mostrarProyectos() {
        String sql = "SELECT id_proyecto, nombre_proyecto, fecha_inicio, fecha_fin, id_estado FROM proyecto";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new GestionProyectoDTO(
                rs.getString("id_proyecto"),
                rs.getString("nombre_proyecto"),
                rs.getDate("fecha_inicio").toLocalDate(),
                rs.getDate("fecha_fin").toLocalDate(),
                rs.getInt("id_estado")
        ));
    }
}

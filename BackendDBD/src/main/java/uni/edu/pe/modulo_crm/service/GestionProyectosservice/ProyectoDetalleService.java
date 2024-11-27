package uni.edu.pe.modulo_crm.service.GestionProyectosservice;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uni.edu.pe.modulo_crm.dto.GestionProyectosdto.GestionProyectoDTO;

import java.time.LocalDate;

@Service
public class ProyectoDetalleService {

    private final JdbcTemplate jdbcTemplate;

    public ProyectoDetalleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Método para obtener un proyecto por su ID
    public GestionProyectoDTO obtenerProyectoPorId(String id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Proyecto WHERE id_proyecto = ?",
                    new Object[]{id},
                    (rs, rowNum) -> new GestionProyectoDTO(
                            rs.getString("id_proyecto"),
                            rs.getString("nombre_proyecto"),
                            rs.getDate("fecha_inicio").toLocalDate(),
                            rs.getDate("fecha_fin").toLocalDate(),
                            rs.getInt("id_estado")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("No se encontró el proyecto con ID: " + id, e);
        }
    }

    // Método para crear un nuevo proyecto
    public void crearProyecto(GestionProyectoDTO proyecto) {
        String sql = "INSERT INTO Proyecto (id_proyecto, nombre_proyecto, fecha_inicio, fecha_fin, id_estado) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                proyecto.getIdProyecto(),
                proyecto.getNombreProyecto(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                proyecto.getIdEstado()
        );
    }

    // Método para actualizar un proyecto existente
    public void actualizarProyecto(String id, GestionProyectoDTO proyecto) {
        String sql = "UPDATE Proyecto SET nombre_proyecto = ?, fecha_inicio = ?, fecha_fin = ?, id_estado = ? WHERE id_proyecto = ?";
        int updatedRows = jdbcTemplate.update(sql,
                proyecto.getNombreProyecto(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                proyecto.getIdEstado(),
                id
        );
        if (updatedRows == 0) {
            throw new RuntimeException("No se encontró el proyecto con ID: " + id);
        }
    }

    // Método para eliminar un proyecto
    public void eliminarProyecto(String id) {
        String sql = "DELETE FROM Proyecto WHERE id_proyecto = ?";
        int deletedRows = jdbcTemplate.update(sql, id);
        if (deletedRows == 0) {
            throw new RuntimeException("No se encontró el proyecto con ID: " + id);
        }
    }
}

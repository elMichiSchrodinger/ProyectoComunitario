package uni.edu.pe.modulo_crm.service.Mantenimientoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.Equipo;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaEquipos;
import uni.edu.pe.modulo_crm.dto.Mantenimientodto.ListaSolicitudMantenimiento;

import java.util.List;

@Service
public class EquipoService {
        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Transactional(readOnly = true)
        public List<ListaEquipos> obtenerEquipos() {
                String sql = "SELECT e.ID_equipo AS idEquipo, e.nombre AS nombre, e.tipo AS tipo, e.fecha_adquisicion AS adquisicion, "
                                +
                                "e.estado AS estado, " +
                                "COALESCE((SELECT cm.fecha_fin FROM Cronograma__de_mantenimiento cm WHERE cm.id_equipo = e.ID_equipo AND cm.fecha_fin IS NOT NULL ORDER BY cm.id_cronograma DESC LIMIT 1), e.fecha_adquisicion) AS ultimaRevision, "
                                +
                                "(SELECT cm.fecha_inicio FROM Cronograma__de_mantenimiento cm WHERE cm.id_equipo = e.ID_equipo ORDER BY cm.id_cronograma DESC LIMIT 1) AS proximaRevision "
                                +
                                "FROM Equipo e;";
                return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListaEquipos.class));
        }

        @Transactional(readOnly = true)
        public Equipo obtenerEquipoPorId(String idEquipo) {
                String sql = "SELECT e.ID_equipo AS idEquipo, e.nombre AS nombre, e.tipo AS tipo, e.marca AS marca, e.modelo AS modelo, "
                                + "e.numero_serie AS serie, e.fecha_adquisicion AS fechaAdquisicion, e.estado AS estado, "
                                + "e.ubicacion AS ubicacion, e.frecuencia_mantenimiento AS frecuencia, e.descripcion AS descripcion, "
                                + "COALESCE((SELECT cm.fecha_fin FROM Cronograma__de_mantenimiento cm WHERE cm.id_equipo = e.ID_equipo AND cm.fecha_fin IS NOT NULL ORDER BY cm.id_cronograma DESC LIMIT 1), e.fecha_adquisicion) AS ultimaRevision, "
                                + "(SELECT cm.fecha_inicio FROM Cronograma__de_mantenimiento cm WHERE cm.id_equipo = e.ID_equipo ORDER BY cm.id_cronograma DESC LIMIT 1) AS proximaRevision "
                                + "FROM Equipo e WHERE e.ID_equipo = ?;";
                return jdbcTemplate.queryForObject(sql, new Object[] { idEquipo },
                                new BeanPropertyRowMapper<>(Equipo.class));
        }

        @Transactional
        public void solicitarMantenimiento(ListaSolicitudMantenimiento solicitud) {
                String sqlSolicitud = "INSERT INTO Solicitud_de_mantenimiento (fecha_solicitud, urgencia, descripcion, ID_empleado, ID_equipo) "
                                +
                                "VALUES (CURRENT_DATE, ?, ?, ?, ?) RETURNING id_solicitud_mantenimiento;";
                String idSolicitud = jdbcTemplate.queryForObject(sqlSolicitud, new Object[] {
                                solicitud.getUrgencia(),
                                solicitud.getDescripcion(),
                                solicitud.getIdEmpleado(),
                                solicitud.getIdEquipo()
                }, String.class);

                String sqlCronograma = "UPDATE Cronograma__de_mantenimiento SET fecha_inicio = CURRENT_DATE, " +
                                "descripcion = 'Mantenimiento correctivo programado', ID_solicitud_mantenimiento = ? " +
                                "WHERE id_cronograma = (SELECT id_cronograma FROM Cronograma__de_mantenimiento WHERE id_equipo = ? ORDER BY id_cronograma DESC LIMIT 1);";
                jdbcTemplate.update(sqlCronograma, idSolicitud, solicitud.getIdSolicitud());
        }

        @Transactional
        public Equipo agregarNuevoEquipo(Equipo nuevoEquipo) {
                String sql = "INSERT INTO Equipo (nombre, tipo, marca, modelo, numero_serie, fecha_adquisicion, estado, ubicacion, frecuencia_mantenimiento, descripcion) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID_equipo;";
                String idEquipo = jdbcTemplate.queryForObject(sql, new Object[] {
                                nuevoEquipo.getNombre(),
                                nuevoEquipo.getTipo(),
                                nuevoEquipo.getMarca(),
                                nuevoEquipo.getModelo(),
                                nuevoEquipo.getSerie(),
                                nuevoEquipo.getFechaAdquisicion(),
                                nuevoEquipo.getEstado(),
                                nuevoEquipo.getUbicacion(),
                                nuevoEquipo.getFrecuencia(),
                                nuevoEquipo.getDescripcion()
                }, String.class);

                nuevoEquipo.setIdEquipo(idEquipo);
                return nuevoEquipo;
        }
}
package uni.edu.pe.modulo_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.DetallePostulacion;
import uni.edu.pe.modulo_crm.dto.Postulacion;
import uni.edu.pe.modulo_crm.dto.Postulaciones;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostulacionesService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public List<Postulaciones> listaPostulaciones(String area, String titulo) {
        StringBuilder sql = new StringBuilder("SELECT id_postulacion, nombre, area, titulo, salario " +
                "FROM postulacion " +
                "LEFT JOIN vacante ON vacante.id_vacante = postulacion.id_vacante " +
                "LEFT JOIN candidato ON candidato.id_candidato = postulacion.id_candidato " +
                "WHERE estado = 1");

        List<Object> params = new ArrayList<>();
        if ((area != null && !area.trim().isEmpty()) || (titulo != null && !titulo.trim().isEmpty())) {
            sql.append(" AND ");
            if (area != null && !area.trim().isEmpty()) {
                sql.append(" area LIKE ?");
                params.add("%" + area + "%");
            }
            if (titulo != null && !titulo.trim().isEmpty()) {
                if (!params.isEmpty()) {
                    sql.append(" OR ");
                }
                sql.append(" titulo LIKE ?");
                params.add("%" + titulo + "%");
            }
        }
        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(Postulaciones.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public DetallePostulacion buscarPostulacion(String id_postulacion) {
        String sql="SELECT \n" +
                "    id_postulacion,    \n" +
                "    area,\n" +
                "    ubicacion,\n" +
                "    tipocontrato,\n" +
                "    vacante.jornadalaboral,\n" +
                "    experiencia,\n" +
                "    niveleducativo,\n" +
                "    vacante.habilidad AS vacante_habilidad,\n" +
                "    vacante.idioma AS vacante_idioma,\n" +
                "    vacante.certificacion AS vacante_certificacion,\n" +
                "    salario,\n" +
                "    bono,\n" +
                "    beneficio,    \n" +
                "    nombre,\n" +
                "    fechanacimiento,\n" +
                "    direccion,\n" +
                "    telefono,\n" +
                "    correo,\n" +
                "    nacionalidad,\n" +
                "    documentoidentidad,\n" +
                "    redsocial,\n" +
                "    nivelestudio,\n" +
                "    titulo,\n" +
                "    institucioneducativa,\n" +
                "    candidato.certificacion AS candidato_certificacion,\n" +
                "    empresa,\n" +
                "    cargo,\n" +
                "    responsabilidad,\n" +
                "    referencialaboral,\n" +
                "    habilidadtecnica,\n" +
                "    candidato.idioma AS candidato_idioma,\n" +
                "    habilidadblanda,\n" +
                "    nombreproyectoref,\n" +
                "    descripcionproyectoref,\n" +
                "    rolproyectoref\n" +
                "FROM postulacion\n" +
                "LEFT JOIN vacante ON vacante.id_vacante = postulacion.id_vacante\n" +
                "LEFT JOIN candidato ON candidato.id_candidato = postulacion.id_candidato\n" +
                "WHERE id_postulacion=?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_postulacion}, new BeanPropertyRowMapper<>(DetallePostulacion.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public Postulacion insertarPostulacion(Postulacion postulacion){
        String sql = "INSERT INTO Postulacion(FechaPostulacion,Estado,ID_vacante,ID_candidato)VALUES(CURRENT_DATE,?,?,?) RETURNING id_postulacion;\n";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_postulacion"});
            ps.setInt(1,1 );
            ps.setString(2, postulacion.getId_vacante());
            ps.setString(3, postulacion.getId_candidato());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("id_postulacion").toString();
        postulacion.setEstado(1);
        postulacion.setFechapostulacion(new Date());
        postulacion.setId_postulacion(idGenerado);
        return postulacion;
    }

    public Postulacion aprobarPostulacion(Postulacion postulacion){
        String sqlUpdate = "UPDATE postulacion SET estado = 2 WHERE id_postulacion = ?";
        int rows = jdbcTemplate.update(sqlUpdate, postulacion.getId_postulacion());

        if (rows > 0) {
            // Consulta la postulación actualizada
            String sqlSelect = "SELECT id_postulacion, fechapostulacion, estado, id_vacante, id_candidato FROM postulacion WHERE id_postulacion = ?";

            return jdbcTemplate.queryForObject(sqlSelect, new Object[]{postulacion.getId_postulacion()}, (rs, rowNum) -> {
                Postulacion updatedPostulacion = new Postulacion();
                updatedPostulacion.setId_postulacion(rs.getString("id_postulacion"));
                updatedPostulacion.setFechapostulacion(rs.getDate("fechapostulacion"));
                updatedPostulacion.setEstado(rs.getInt("estado"));
                updatedPostulacion.setId_vacante(rs.getString("id_vacante"));
                updatedPostulacion.setId_candidato(rs.getString("id_candidato"));
                return updatedPostulacion;
            });
        } else {
            throw new RuntimeException("Postulacion no encontrada");
        }
    }
    public Postulacion desaprobarPostulacion(Postulacion postulacion){
        String sqlUpdate = "UPDATE postulacion SET estado = 3 WHERE id_postulacion = ?";
        int rows = jdbcTemplate.update(sqlUpdate, postulacion.getId_postulacion());

        if (rows > 0) {
            // Consulta la postulación actualizada
            String sqlSelect = "SELECT id_postulacion, fechapostulacion, estado, id_vacante, id_candidato FROM postulacion WHERE id_postulacion = ?";

            return jdbcTemplate.queryForObject(sqlSelect, new Object[]{postulacion.getId_postulacion()}, (rs, rowNum) -> {
                Postulacion updatedPostulacion = new Postulacion();
                updatedPostulacion.setId_postulacion(rs.getString("id_postulacion"));
                updatedPostulacion.setFechapostulacion(rs.getDate("fechapostulacion"));
                updatedPostulacion.setEstado(rs.getInt("estado"));
                updatedPostulacion.setId_vacante(rs.getString("id_vacante"));
                updatedPostulacion.setId_candidato(rs.getString("id_candidato"));
                return updatedPostulacion;
            });
        } else {
            throw new RuntimeException("Postulacion no encontrada");
        }
    }
}

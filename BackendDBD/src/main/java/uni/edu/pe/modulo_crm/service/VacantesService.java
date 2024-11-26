package uni.edu.pe.modulo_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Vacante;
import uni.edu.pe.modulo_crm.dto.Vacantes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacantesService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<Vacantes> buscarVacantes(String nombrePuesto, String area) {
        StringBuilder sql = new StringBuilder("SELECT id_vacante, area, nombrepuesto, salario FROM vacante");
        List<Object> params = new ArrayList<>();

        if ((nombrePuesto != null && !nombrePuesto.trim().isEmpty()) || (area != null && !area.trim().isEmpty())) {
            sql.append(" WHERE");

            if (nombrePuesto != null && !nombrePuesto.trim().isEmpty()) {
                sql.append(" nombrepuesto LIKE ?");
                params.add("%" + nombrePuesto + "%");
            }

            if (area != null && !area.trim().isEmpty()) {
                if (!params.isEmpty()) {
                    sql.append(" OR");
                }
                sql.append(" area LIKE ?");
                params.add("%" + area + "%");
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(Vacantes.class));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Vacante buscarVacante(String id_vacante) {
        String sql= "select id_vacante,nombrepuesto, descripcionpuesto, area, ubicacion, tipocontrato, jornadalaboral, experiencia, niveleducativo, idioma, habilidad, certificacion, salario, bono, beneficio, modalidad from vacante where id_vacante=?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_vacante}, new BeanPropertyRowMapper<>(Vacante.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public Vacante insertarVacante(Vacante vacante, String id_empleado) {
        String sql = "INSERT INTO Vacante(NombrePuesto,DescripcionPuesto,Area,Ubicacion,TipoContrato," +
                "JornadaLaboral,Experiencia,NivelEducativo,Habilidad,Idioma,Certificacion,Salario," +
                "Bono,Beneficio,FechaInicio,FechaCierre,Modalidad,ID_empleado) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id_vacante";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_vacante"});
            ps.setString(1, vacante.getNombrepuesto());
            ps.setString(2, vacante.getDescripcionpuesto());
            ps.setString(3, vacante.getArea());
            ps.setString(4, vacante.getUbicacion());
            ps.setString(5, vacante.getTipocontrato());
            ps.setString(6, vacante.getJornadalaboral());
            ps.setDouble(7, vacante.getExperiencia());
            ps.setString(8, vacante.getNiveleducativo());
            ps.setString(9, vacante.getHabilidad());
            ps.setString(10, vacante.getIdioma());
            ps.setString(11, vacante.getCertificacion());
            ps.setDouble(12, vacante.getSalario());
            ps.setDouble(13, vacante.getBono());
            ps.setString(14, vacante.getBeneficio());
            ps.setDate(15, new Date(vacante.getFechainicio().getTime()));
            ps.setDate(16, new Date(vacante.getFechacierre().getTime()));
            ps.setString(17, vacante.getModalidad());
            ps.setString(18, id_empleado);
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("id_vacante").toString();
        vacante.setId_vacante(idGenerado);
        return vacante;
    }
}

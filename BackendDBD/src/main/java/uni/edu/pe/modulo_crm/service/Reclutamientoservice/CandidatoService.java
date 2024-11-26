package uni.edu.pe.modulo_crm.service.Reclutamientoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Reclutamientodto.Candidato;
import uni.edu.pe.modulo_crm.dto.Reclutamientodto.DetalleCandidato;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<DetalleCandidato> listarCandidatoAprobado(String nombre) {
        StringBuilder sql = new StringBuilder("select id_postulacion, nombre, correo, telefono from postulacion\n" +
                "left join candidato on candidato.id_candidato=postulacion.id_candidato\n" +
                "where postulacion.estado = 2");
        List<Object> params = new ArrayList<>();
        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" and nombre like ?");
            params.add("%"+nombre+"%");
        }
        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(DetalleCandidato.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public List<DetalleCandidato> listarCandidatoDesaprobado(String nombre) {
        StringBuilder sql = new StringBuilder("select id_postulacion, nombre, correo from postulacion\n" +
                "left join candidato on candidato.id_candidato=postulacion.id_candidato\n" +
                "where postulacion.estado = 3");
        List<Object> params = new ArrayList<>();
        if (nombre != null && !nombre.trim().isEmpty()) {
            sql.append(" and nombre like ?");
            params.add("%"+nombre+"%");
        }
        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(DetalleCandidato.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public DetalleCandidato candidato(String id_postulacion) {
        String sql = "select id_postulacion,nombre , correo, telefono from postulacion\n" +
                "left join candidato on candidato.id_candidato=postulacion.id_candidato\n" +
                "where postulacion.id_postulacion = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id_postulacion},new BeanPropertyRowMapper<>(DetalleCandidato.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public Candidato insertarCandidato(Candidato candidato) {
        String sql = "INSERT INTO Candidato (Nombre, Correo, Telefono, FechaNacimiento, Direccion, Nacionalidad, DocumentoIdentidad, RedSocial, NivelEstudio, Titulo, InstitucionEducativa, Certificacion, Empresa, Cargo, Responsabilidad, ReferenciaLaboral, HabilidadTecnica, Idioma, HabilidadBlanda, NombreProyectoRef, DescripcionProyectoRef, RolProyectoRef) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_candidato;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_candidato"});
            ps.setString(1, candidato.getNombre());
            ps.setString(2, candidato.getCorreo());
            ps.setInt(3, candidato.getTelefono());
            ps.setDate(4, new Date(candidato.getFechanacimiento().getTime()));
            ps.setString(5, candidato.getDireccion());
            ps.setString(6, candidato.getNacionalidad());
            ps.setString(7, candidato.getDocumentoidentidad());
            ps.setString(8, candidato.getRedsocial());
            ps.setString(9, candidato.getNivelestudio());
            ps.setString(10, candidato.getTitulo());
            ps.setString(11, candidato.getInstitucioneducativa());
            ps.setString(12, candidato.getCertificacion());
            ps.setString(13, candidato.getEmpresa());
            ps.setString(14, candidato.getCargo());
            ps.setString(15, candidato.getResponsabilidad());
            ps.setString(16, candidato.getReferencialaboral());
            ps.setString(17, candidato.getHabilidadtecnica());
            ps.setString(18, candidato.getIdioma());
            ps.setString(19, candidato.getHabilidadblanda());
            ps.setString(20, candidato.getNombreproyectoref());
            ps.setString(21, candidato.getDescripcionproyectoref());
            ps.setString(22, candidato.getRolproyectoref());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("id_candidato").toString();
        candidato.setId_candidato(idGenerado);
        return candidato;
    }
}
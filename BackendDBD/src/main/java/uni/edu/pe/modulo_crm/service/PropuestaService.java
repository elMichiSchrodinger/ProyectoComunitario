package uni.edu.pe.modulo_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.*;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class PropuestaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarPropuesta> mostrarpropuesta(){
        String sql="SELECT pro.ID_presentacion_propuesta,\n" +
                "    pro.Precio_Propuesto, \n" +
                "    pro.Descripcion_Tecnica, \n" +
                "    pro.Descripcion_Economica, \n" +
                "    pro.Calidad_Ofrecida, \n" +
                "    pro.Seguridad_Ofrecida, \n" +
                "    pro.Condicion_Pago, \n" +
                "    pro.Tiempo_Ejecucion, \n" +
                "    pro.Observacion_Propuesta \n" +
                "FROM \n" +
                "    Presentacion_propuesta pro \n" +
                "LEFT JOIN Revision_tecnica rev ON pro.ID_revision_tecnica = rev.ID_revision_tecnica \n" +
                "WHERE \n" +
                "    rev.Estado_Participacion = 'Aceptado' and pro.estado_propuesta = 'No revisado';";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarPropuesta.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarGarantias> mostrargarantias(){
        String sql="select ga.ID_garantia, ga.Descrip_garantia from garantia ga left join presentacion_propuesta pro on ga.id_presentacion_propuesta = pro.id_presentacion_propuesta \n" +
                "LEFT JOIN Revision_tecnica rev ON pro.ID_revision_tecnica = rev.ID_revision_tecnica \n" +
                "WHERE \n" +
                "    rev.Estado_Participacion = 'Aceptado' and pro.estado_propuesta = 'No revisado';";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarGarantias.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarBeneficios> mostrarbeneficios(){
        String sql="select be.ID_beneficio, be.Descrip_beneficio from beneficio be left join presentacion_propuesta pro on be.id_presentacion_propuesta = pro.id_presentacion_propuesta \n" +
                "LEFT JOIN Revision_tecnica rev ON pro.ID_revision_tecnica = rev.ID_revision_tecnica \n" +
                "WHERE \n" +
                "    rev.Estado_Participacion = 'Aceptado' and pro.estado_propuesta = 'No revisado';";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarBeneficios.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarPropuesta insertarpropuesta(InsertarPropuesta propuesta) {
        String sql = "INSERT INTO Presentacion_propuesta (Fecha_Presentacion, Precio_Propuesto, Descripcion_Tecnica, Descripcion_Economica, Calidad_Ofrecida, Seguridad_Ofrecida, Condicion_Pago,Tiempo_Ejecucion, Observacion_Propuesta, Estado_Propuesta, ID_empleado, ID_revision_tecnica, ID_cliente) VALUES\n" +
                "(CURRENT_DATE, ?, ?,?,\n" +
                "?,?,\n" +
                "?,?,?\n" +
                ",'No revisado', (SELECT e.ID_empleado FROM empleado e WHERE e.tipo_empleado = 'Administrador Comercial'), ?, ?) RETURNING ID_presentacion_propuesta;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_presentacion_propuesta"});
            ps.setFloat(1, propuesta.getPrecio_Propuesto());
            ps.setString(2, propuesta.getDescripcion_Tecnica());
            ps.setString(3, propuesta.getDescripcion_Economica());
            ps.setString(4, propuesta.getCalidad_Ofrecida());
            ps.setString(5, propuesta.getSeguridad_Ofrecida());
            ps.setString(6, propuesta.getCondicion_Pago());
            ps.setString(7, propuesta.getTiempo_Ejecucion());
            ps.setString(8, propuesta.getObservacion_Propuesta());
            ps.setString(9, propuesta.getId_revision_tecnica());
            ps.setString(10, propuesta.getId_cliente());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_presentacion_propuesta").toString();
        propuesta.setId_presentacion_propuesta(idGenerado);
        propuesta.setEstado_Propuesta("No revisado");
        return propuesta;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarGarantias insertargarantia(InsertarGarantias garantia) {
        String sql = "INSERT INTO Garantia (Descrip_garantia, ID_presentacion_propuesta) VALUES \n" +
                "(?,?) RETURNING ID_garantia;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_garantia"});
            ps.setString(1, garantia.getDescrip_garantia());
            ps.setString(2, garantia.getId_presentacion_propuesta());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_garantia").toString();
        garantia.setId_garantia(idGenerado);
        return garantia;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarBeneficios insertarbeneficio(InsertarBeneficios beneficio) {
        String sql = "INSERT INTO Beneficio (Descrip_beneficio, ID_presentacion_propuesta) values \n" +
                "(?,?) RETURNING ID_beneficio;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_beneficio"});
            ps.setString(1, beneficio.getDescrip_beneficio());
            ps.setString(2, beneficio.getId_presentacion_propuesta());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_beneficio").toString();
        beneficio.setId_beneficio(idGenerado);
        return beneficio;
    }
    public ActualizarPropuesta actualizarpropuesta(ActualizarPropuesta propuesta){
        String sqlUpdate = "UPDATE Presentacion_Propuesta\n" +
                "SET Estado_Propuesta = 'Revisado'\n" +
                "WHERE ID_presentacion_propuesta = ?;";
        int rows = jdbcTemplate.update(sqlUpdate, propuesta.getId_presentacion_propuesta());
        return propuesta;
    }
}

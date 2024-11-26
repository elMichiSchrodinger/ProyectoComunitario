package uni.edu.pe.modulo_crm.service.CRMservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.CRMdto.*;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class InvitacionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarInvitacion> mostrarinvitacion(){
        String sql="select inv.ID_invitacion,\n" +
                "    cli.Nombre AS Nombre_Cliente,\n" +
                "    inv.Asunto_Invitacion,\n" +
                "    inv.Tiempo_Maximo,\n" +
                "    inv.Direccion_Proyecto,\n" +
                "    inv.Comentario\n" +
                "FROM \n" +
                "    Invitacion inv\n" +
                "LEFT JOIN \n" +
                "    Cliente cli ON inv.ID_cliente = cli.ID_cliente WHERE inv.estado_invitacion = 'No revisado';";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarInvitacion.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarRequerimientos> mostrarrequerimientos(){
        String sql="SELECT re.ID_requerimiento,re.Descrip_requerimiento from requerimiento re left join \n" +
                "invitacion inv on re.id_invitacion = inv.id_invitacion WHERE inv.estado_invitacion = 'No revisado';";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarRequerimientos.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarInvitacion insertarinvitacion(InsertarInvitacion invitacion) {
        String sql = "INSERT INTO Invitacion (Asunto_Invitacion, Fecha_Envio, Tiempo_Maximo, Direccion_Proyecto, Comentario, Estado_Invitacion, ID_cliente) values \n" +
                "(?, CURRENT_DATE, ?, ?, ?, 'No revisado', ?) RETURNING ID_invitacion;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_invitacion"});
            ps.setString(1, invitacion.getAsunto_Invitacion());
            ps.setInt(2, invitacion.getTiempo_Maximo());
            ps.setString(3, invitacion.getDireccion_Proyecto());
            ps.setString(4, invitacion.getComentario());
            ps.setString(5, invitacion.getId_Cliente());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_invitacion").toString();
        invitacion.setId_Invitacion(idGenerado);
        invitacion.setEstado_Invitacion("No revisado");
        return invitacion;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarRequerimientos insertarrequerimiento(InsertarRequerimientos requerimiento) {
        String sql = "INSERT INTO Requerimiento(Descrip_requerimiento, ID_invitacion) VALUES \n" +
                "(?,?) RETURNING ID_requerimiento;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_requerimiento"});
            ps.setString(1, requerimiento.getDescrip_requerimiento());
            ps.setString(2, requerimiento.getId_invitacion());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_requerimiento").toString();
        requerimiento.setId_requerimiento(idGenerado);
        return requerimiento;
    }
    public ActualizarInvitacion actualizarinvitacion(ActualizarInvitacion invitacion){
        String sqlUpdate = "UPDATE Invitacion\n" +
                "SET Estado_Invitacion = 'Revisado'\n" +
                "WHERE ID_Invitacion = ?;";
        int rows = jdbcTemplate.update(sqlUpdate, invitacion.getId_Invitacion());
        return invitacion;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarRequerimientos> requerimientosinvitacion(String id_invitacion){
        String sql="SELECT re.ID_requerimiento,re.Descrip_requerimiento from requerimiento re left join \n" +
                "invitacion inv on re.id_invitacion = inv.id_invitacion WHERE inv.estado_invitacion = 'No revisado' and inv.id_invitacion=?";
        return jdbcTemplate.query(sql, new Object[]{id_invitacion}, (rs, rowNum) -> {
            MostrarRequerimientos requerimiento = new MostrarRequerimientos();
            requerimiento.setId_requerimiento(rs.getString("id_requerimiento"));
            requerimiento.setDescrip_requerimiento(rs.getString("descrip_requerimiento"));
            return requerimiento;
        });
    }

    public MostrarInvitacion obtenerinvitacion(String id_invitacion){
        String sql="select inv.ID_invitacion,\n" +
                "    cli.Nombre AS Nombre_Cliente,\n" +
                "    inv.Asunto_Invitacion,\n" +
                "    inv.Tiempo_Maximo,\n" +
                "    inv.Direccion_Proyecto,\n" +
                "    inv.Comentario\n" +
                "FROM \n" +
                "    Invitacion inv\n" +
                "LEFT JOIN \n" +
                "    Cliente cli ON inv.ID_cliente = cli.ID_cliente WHERE inv.estado_invitacion = 'No revisado' and inv.ID_invitacion=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id_invitacion}, new BeanPropertyRowMapper<>(MostrarInvitacion.class));
    }


}

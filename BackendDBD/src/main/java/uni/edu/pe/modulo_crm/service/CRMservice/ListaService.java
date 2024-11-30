package uni.edu.pe.modulo_crm.service.CRMservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.CRMdto.MostrarListaAdj;
import uni.edu.pe.modulo_crm.dto.CRMdto.MostrarListaInv;

import java.util.List;

@Service
public class ListaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarListaInv> mostrarlistainv(){
        String sql="SELECT \n" +
                "    cli.ID_cliente,\n" +
                "    inv.id_invitacion,\n" +
                "    cli.Nombre AS Nombre_Cliente,\n" +
                "\tinv.Estado_Invitacion\n" +
                "FROM \n" +
                "    Cliente cli\n" +
                "LEFT JOIN Invitacion inv ON cli.ID_cliente = inv.ID_cliente\n" +
                "WHERE \n" +
                "    inv.Estado_Invitacion IN ('No revisado');";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarListaInv.class));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarListaAdj> mostrarlistaadj(){
        String sql="SELECT \n" +
                "    cli.ID_cliente,\n" +
                "\tadj.id_adjudicacion,\n" +
                "    cli.Nombre AS Nombre_Cliente,\n" +
                "\tadj.Estado_Adjudicacion\n" +
                "FROM \n" +
                "    Cliente cli\n" +
                "LEFT JOIN Adjudicacion adj ON cli.ID_cliente = adj.ID_cliente\n" +
                "WHERE \n" +
                "    adj.Estado_Adjudicacion IN ('Aprobada', 'Rechazada');";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarListaAdj.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarListaInv> mostrarlistapro(){
        String sql="SELECT \n" +
                "    cli.ID_cliente,\n" +
                "    inv.id_invitacion,\n" +
                "    cli.Nombre AS Nombre_Cliente,\n" +
                "\tinv.Estado_Invitacion\n" +
                "FROM \n" +
                "    Cliente cli\n" +
                "LEFT JOIN Invitacion inv ON cli.ID_cliente = inv.ID_cliente\n" +
                "WHERE \n" +
                "    inv.Estado_Invitacion IN ('Aceptada');";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarListaInv.class));
    }
}

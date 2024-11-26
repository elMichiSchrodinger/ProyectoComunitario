package uni.edu.pe.modulo_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.MostrarLista;

import java.util.List;

@Service
public class ListaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<MostrarLista> mostrarlista(){
        String sql="SELECT cli.ID_cliente,\n" +
                "\tinv.id_invitacion,\n" +
                "    cli.Nombre AS Nombre_Cliente\n" +
                "FROM \n" +
                "    Cliente cli \n" +
                "LEFT JOIN Adjudicacion adj ON cli.ID_cliente = adj.ID_cliente \n" +
                "LEFT JOIN Invitacion inv ON cli.ID_cliente = inv.ID_cliente  \n" +
                "WHERE \n" +
                "    adj.Estado_Adjudicacion IN ('Aceptado', 'No aceptado') \n" +
                "    OR inv.Estado_Invitacion IN ('Revisado', 'No revisado');\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MostrarLista.class));
    }
}

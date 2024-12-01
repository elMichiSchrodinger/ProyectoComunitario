package uni.edu.pe.modulo_crm.service.CRMservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.CRMdto.SeguimientoInv;

@Service
public class SeguimientoInvService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public SeguimientoInv seguimientoinv(String id_cliente){
        String sql="SELECT inv.id_invitacion, inv.estado_invitacion\n" +
                "FROM cliente cli\n" +
                "LEFT JOIN invitacion inv \n" +
                "ON cli.id_cliente = inv.id_cliente\n" +
                "WHERE CAST(SUBSTRING(inv.id_invitacion FROM 3) AS INTEGER) = (\n" +
                "    SELECT MAX(CAST(SUBSTRING(i.id_invitacion FROM 3) AS INTEGER))\n" +
                "    FROM invitacion i\n" +
                "    WHERE i.id_cliente = cli.id_cliente\n" +
                ") and cli.id_cliente = ?;";
        return jdbcTemplate.queryForObject(sql,new Object[]{id_cliente}, new BeanPropertyRowMapper<>(SeguimientoInv.class));
    }
}

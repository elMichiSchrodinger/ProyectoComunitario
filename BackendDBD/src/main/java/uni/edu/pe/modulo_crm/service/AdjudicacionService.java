package uni.edu.pe.modulo_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.InsertarAdjudicacion;
import uni.edu.pe.modulo_crm.dto.InsertarRevision;

import java.sql.PreparedStatement;

@Service
public class AdjudicacionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarAdjudicacion insertaradjudicaciona(InsertarAdjudicacion adjudicacion) {
        String sql = "INSERT INTO Adjudicacion (Fecha_Adjudicacion, Estado_Adjudicacion, ID_presentacion_propuesta, ID_cliente)\n" +
                "VALUES (CURRENT_DATE, 'Aceptado', ?, ?) RETURNING ID_adjudicacion;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_adjudicacion"});
            ps.setString(1, adjudicacion.getId_presentacion_propuesta());
            ps.setString(2, adjudicacion.getId_cliente());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_adjudicacion").toString();
        adjudicacion.setId_adjudicacion(idGenerado);
        adjudicacion.setEstado_Adjudicacion("Aceptado");
        return adjudicacion;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarAdjudicacion insertaradjudicacionr(InsertarAdjudicacion adjudicacion) {
        String sql = "INSERT INTO Adjudicacion (Fecha_Adjudicacion, Estado_Adjudicacion, ID_presentacion_propuesta, ID_cliente)\n" +
                "VALUES (CURRENT_DATE, 'Rechazado', ?, ?) RETURNING ID_adjudicacion;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_adjudicacion"});
            ps.setString(1, adjudicacion.getId_presentacion_propuesta());
            ps.setString(2, adjudicacion.getId_cliente());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_adjudicacion").toString();
        adjudicacion.setId_adjudicacion(idGenerado);
        adjudicacion.setEstado_Adjudicacion("Rechazado");
        return adjudicacion;
    }
}

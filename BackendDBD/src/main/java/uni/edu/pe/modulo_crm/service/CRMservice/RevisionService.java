package uni.edu.pe.modulo_crm.service.CRMservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.CRMdto.InsertarRevision;
import uni.edu.pe.modulo_crm.dto.CRMdto.MostrarStock;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class RevisionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public MostrarStock mostrarstock(){
        String sql="SELECT inf.ID_informe_stock,\n" +
                "    inf.stock_real AS Stock_Disponible \n" +
                "FROM \n" +
                "    Informe_stock inf\n" +
                "WHERE \n" +
                "    inf.Fecha_generacion = (\n" +
                "        SELECT MAX(Fecha_generacion) \n" +
                "        FROM Informe_stock\n" +
                "    );";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(MostrarStock.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarRevision insertarrevisiona(InsertarRevision revision) {
        String sql = "INSERT INTO Revision_tecnica (Estado_Participacion, ID_empleado, ID_invitacion, ID_informe_stock) VALUES\n" +
                "('Aceptado', (SELECT e.ID_empleado FROM empleado e WHERE e.tipo_empleado = 'Administrador Comercial'),\n" +
                "?, ?) RETURNING ID_revision_tecnica;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_revision_tecnica"});
            ps.setString(1, revision.getId_invitacion());
            ps.setString(2, revision.getId_informe_stock());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_revision_tecnica").toString();
        revision.setId_revision_tecnica(idGenerado);
        revision.setEstado_participacion("Aceptado");
        return revision;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public InsertarRevision insertarrevisionr(InsertarRevision revision) {
        String sql = "INSERT INTO Revision_tecnica (Estado_Participacion, ID_empleado, ID_invitacion, ID_informe_stock) VALUES\n" +
                "('Rechazado', (SELECT e.ID_empleado FROM empleado e WHERE e.tipo_empleado = 'Administrador Comercial'),\n" +
                "?, ?) RETURNING ID_revision_tecnica;";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID_revision_tecnica"});
            ps.setString(1, revision.getId_invitacion());
            ps.setString(2, revision.getId_informe_stock());
            return ps;
        }, keyHolder);

        String idGenerado = keyHolder.getKeys().get("ID_revision_tecnica").toString();
        revision.setId_revision_tecnica(idGenerado);
        revision.setEstado_participacion("Rechazado");
        return revision;
    }
}

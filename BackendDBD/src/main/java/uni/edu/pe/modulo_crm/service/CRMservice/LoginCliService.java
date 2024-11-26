package uni.edu.pe.modulo_crm.service.CRMservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.CRMdto.LoginCli;

import java.util.List;

@Service
public class LoginCliService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<LoginCli> listarlogincli() {
        String sql = "select * from cliente;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<LoginCli>(LoginCli.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public LoginCli buscarlogincli(String ruc_dni, String correo) {
        String sql = "select * from cliente where ruc_dni like ? and correo like ?;";
        return jdbcTemplate.queryForObject(sql,new Object[]{ruc_dni,correo},new BeanPropertyRowMapper<>(LoginCli.class));
    }
}

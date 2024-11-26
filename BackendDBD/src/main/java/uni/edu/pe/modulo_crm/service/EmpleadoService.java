package uni.edu.pe.modulo_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uni.edu.pe.modulo_crm.dto.Empleado;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public List<Empleado> listarEmpleados() {
        String sql = "select * from empleado";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Empleado>(Empleado.class));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Empleado buscarEmpleado(String correo, String password) {
        String sql = "select * from empleado where correo like ? and password like ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{correo,password},new BeanPropertyRowMapper<>(Empleado.class));
    }
}

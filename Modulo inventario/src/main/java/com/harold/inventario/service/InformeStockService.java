package com.harold.inventario.service;

import com.harold.inventario.model.InformeStock;
import com.harold.inventario.model.DetalleOrden;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InformeStockService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<InformeStock> ObtenerInformeStock() {
        String sql = "SELECT " +
                "id_informe_stock AS \"ID de Informe\", " +
                "mes_informe AS \"Mes\", " +
                "año_informe AS \"Año\", " +
                "fecha_generacion AS \"Fecha de Generación\", " +
                "CASE " +
                "WHEN estado_informe = 'A' THEN 'Finalizado' " +
                "WHEN estado_informe = 'B' THEN 'En Proceso' " +
                "WHEN estado_informe = 'C' THEN 'Generado' " +
                "ELSE 'Desconocido' " +
                "END AS \"Estado\" " +
                "FROM informe_stock " +
                "ORDER BY fecha_generacion;";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<InformeStock>>() {
            @Override
            public List<InformeStock> extractData(ResultSet rs) throws SQLException {
                List<InformeStock> informes = new ArrayList<>();
                while (rs.next()) {
                    InformeStock informe = new InformeStock();
                    informe.setIdInformeStock(rs.getString("ID de Informe"));
                    informe.setMesInforme(rs.getInt("Mes"));
                    informe.setAñoInforme(rs.getInt("Año"));
                    informe.setFechaGeneracion(rs.getDate("Fecha de Generación").toLocalDate());
                    informe.setEstadoInforme(rs.getString("Estado"));
                    informes.add(informe);
                }
                return informes;
            }
        });
    }

    public InformeStock obtenerDetalleInformeStock(String idInformeStock) {
        String sql = "SELECT informe.id_informe_stock AS \"ID del Informe\", informe.mes_informe AS \"Mes\", " +
                "informe.año_informe AS \"Año\", informe.fecha_generacion AS \"Fecha de Generación\", " +
                "informe.estado_informe AS \"Estado del Informe\" " +
                "FROM informe_stock AS informe " +
                "WHERE informe.id_informe_stock = ?;";

        return jdbcTemplate.queryForObject(sql, new Object[]{idInformeStock}, new RowMapper<InformeStock>() {
            @Override
            public InformeStock mapRow(ResultSet rs, int rowNum) throws SQLException {
                InformeStock informe = new InformeStock();
                informe.setIdInformeStock(rs.getString("ID del Informe"));
                informe.setMesInforme(rs.getInt("Mes"));
                informe.setAñoInforme(rs.getInt("Año"));
                informe.setFechaGeneracion(rs.getDate("Fecha de Generación").toLocalDate());
                informe.setEstadoInforme(rs.getString("Estado del Informe"));
                return informe;
            }
        });
    }

    public List<Map<String, Object>> obtenerDetalleOrdenPorInformeStock(String idInformeStock) {
        String sql = "SELECT DO_.ID_detalle_orden AS \"ID Detalle Orden\", R.Nombre AS \"Nombre Recurso\", " +
                "DO_.Cantidad_recurso AS \"Cantidad Recurso\", P.Nombre_producto AS \"Nombre Producto\", " +
                "DO_.Cantidad_producto AS \"Cantidad Producto\", OP.Fecha_creacion AS \"Fecha Creación\" " +
                "FROM Detalle_orden DO_ " +
                "JOIN Recurso R ON DO_.ID_recurso = R.ID_recurso " +
                "JOIN Producto P ON DO_.ID_producto = P.ID_producto " +
                "JOIN Orden_produccion OP ON DO_.ID_orden_produccion = OP.ID_orden_produccion " +
                "WHERE DO_.ID_Informe_Stock = ?;";

        return jdbcTemplate.queryForList(sql, idInformeStock);
    }

    @Transactional
    public void actualizarInformeStock(String idInformeStock, InformeStock informe) {
        String sql = "UPDATE informe_stock SET mes_informe = ?, año_informe = ?, fecha_generacion = ?, estado_informe = ? " +
                "WHERE id_informe_stock = ?;";

        jdbcTemplate.update(sql, informe.getMesInforme(), informe.getAñoInforme(),
                informe.getFechaGeneracion(), informe.getEstadoInforme(), idInformeStock);
    }


}

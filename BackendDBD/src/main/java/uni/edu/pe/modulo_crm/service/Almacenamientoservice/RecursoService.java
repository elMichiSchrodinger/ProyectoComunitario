package com.harold.inventario.service;

import com.harold.inventario.model.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Service
public class RecursoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Recurso> ObtenerRecursos() {
        String sql = "SELECT " +
                "    r.id_recurso AS \"ID\", " +
                "    r.nombre AS \"Producto\", " +
                "    r.stock AS \"Stock\", " +
                "    r.disponible AS \"Disponible\", " +
                "    r.minimo AS \"Mínimo\", " +
                "    r.maximo AS \"Máximo\", " +
                "    r.pedido AS \"A pedir\" " +
                "FROM " +
                "    recurso r " +
                "WHERE " +
                "    r.cod_estado_recurso = 'A';";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Recurso>>() {
            @Override
            public List<Recurso> extractData(ResultSet rs) throws SQLException {
                List<Recurso> recursos = new ArrayList<>();
                while (rs.next()) {
                    Recurso recurso = new Recurso();
                    recurso.setIdRecurso(rs.getString("ID")); // Mapear el ID
                    recurso.setNombre(rs.getString("Producto"));
                    recurso.setStock(rs.getInt("Stock"));
                    recurso.setDisponible(rs.getInt("Disponible"));
                    recurso.setMinimo(rs.getInt("Mínimo"));
                    recurso.setMaximo(rs.getInt("Máximo"));
                    recurso.setPedido(rs.getInt("A pedir"));
                    recursos.add(recurso);
                }
                return recursos;
            }
        });
    }

    public Recurso obtenerRecursoPorId(String idRecurso) {
        String sql = "SELECT " +
                "    R.id_recurso AS \"ID del Recurso\", " +
                "    R.nombre AS \"Nombre\", " +
                "    R.descripcion AS \"Descripción\", " +
                "    R.costo_unitario AS \"Costo Unitario\", " +
                "    R.peso AS \"Peso\", " +
                "    R.volumen AS \"Volumen\", " +
                "    R.plazo_entrega AS \"Plazo de Entrega\", " +
                "    R.cod_estado_recurso AS \"Estado del Recurso\", " +
                "    R.fecha_registro AS \"Fecha de Registro\", " +
                "    R.stock AS \"Stock\", " +
                "    R.disponible AS \"Disponible\", " +
                "    R.minimo AS \"Minimo\", " +
                "    R.maximo AS \"Maximo\", " +
                "    R.pedido AS \"Pedido\" " + // Ahora obtenemos el valor de 'Pedido' correctamente
                "FROM recurso R " +
                "WHERE R.id_recurso = ?";  // Usamos un parámetro para evitar inyección SQL

        return jdbcTemplate.query(sql, new Object[]{idRecurso}, new ResultSetExtractor<Recurso>() {
            @Override
            public Recurso extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    Recurso recurso = new Recurso();
                    recurso.setIdRecurso(rs.getString("ID del Recurso"));
                    recurso.setNombre(rs.getString("Nombre"));
                    recurso.setDescripcion(rs.getString("Descripción"));
                    recurso.setCostoUnitario(rs.getBigDecimal("Costo Unitario"));
                    recurso.setPeso(rs.getBigDecimal("Peso"));
                    recurso.setVolumen(rs.getBigDecimal("Volumen"));
                    recurso.setPlazoEntrega(rs.getInt("Plazo de Entrega"));
                    recurso.setCodEstadoRecurso(rs.getString("Estado del Recurso"));
                    recurso.setFechaRegistro(rs.getDate("Fecha de Registro").toLocalDate());
                    recurso.setStock(rs.getInt("Stock"));
                    recurso.setDisponible(rs.getInt("Disponible"));
                    recurso.setMinimo(rs.getInt("Minimo"));
                    recurso.setMaximo(rs.getInt("Maximo"));
                    recurso.setPedido(rs.getInt("Pedido")); // Ahora se obtiene el valor correcto de 'Pedido'
                    return recurso;
                }
                return null;
            }
        });
    }

    public List<String> obtenerGuiasPorRecurso(String idRecurso) {
        // Consulta SQL para obtener las guías de remisión relacionadas con un recurso
        String sql = "SELECT DISTINCT " +
                "    dg.id_guia_remision AS \"Documento\", " +
                "    dg.cantidad_recurso AS \"Cantidad\", " +
                "    g.fecha_efectiva AS \"Fecha\" " +
                "FROM detalle_guia dg " +
                "JOIN guia_remision g ON dg.id_guia_remision = g.id_guia_remision " +
                "WHERE dg.id_recurso = ?";

        // Ejecuta la consulta y mapea los resultados a una lista
        return jdbcTemplate.query(sql, new Object[]{idRecurso}, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException {
                List<String> guias = new ArrayList<>();
                while (rs.next()) {
                    String guia = String.format("Documento: %s, Cantidad: %d, Fecha: %s",
                            rs.getString("Documento"),
                            rs.getInt("Cantidad"),
                            rs.getDate("Fecha").toLocalDate().toString());
                    guias.add(guia);
                }
                return guias;
            }
        });
    }


    @Transactional
    public boolean actualizarRecurso(String idRecurso, Recurso recurso) {
        // Consulta SQL para actualizar los datos del recurso
        String sql = "UPDATE recurso SET " +
                "nombre = ?, descripcion = ?, costo_unitario = ?, peso = ?, volumen = ?, " +
                "plazo_entrega = ?, cod_estado_recurso = ?, stock = ?, disponible = ?, minimo = ?, " +
                "maximo = ?, pedido = ? WHERE id_recurso = ?"; // Utilizamos el idRecurso para la actualización

        // Iniciar log para ver los parámetros que estamos pasando
        System.out.println("Iniciando actualización del recurso...");
        System.out.println("ID del recurso a actualizar: " + idRecurso);
        System.out.println("Nuevo nombre: " + recurso.getNombre());
        System.out.println("Nueva descripción: " + recurso.getDescripcion());
        System.out.println("Nuevo costo unitario: " + recurso.getCostoUnitario());
        System.out.println("Nuevo peso: " + recurso.getPeso());
        System.out.println("Nuevo volumen: " + recurso.getVolumen());
        System.out.println("Nuevo plazo de entrega: " + recurso.getPlazoEntrega());
        System.out.println("Nuevo estado del recurso: " + recurso.getCodEstadoRecurso());
        System.out.println("Nuevo stock: " + recurso.getStock());
        System.out.println("Nuevo disponible: " + recurso.getDisponible());
        System.out.println("Nuevo mínimo: " + recurso.getMinimo());
        System.out.println("Nuevo máximo: " + recurso.getMaximo());
        System.out.println("Nuevo a pedir: " + recurso.getPedido());

        try {
            // Ejecutamos la actualización en la base de datos
            int rowsAffected = jdbcTemplate.update(sql,
                    recurso.getNombre(),
                    recurso.getDescripcion(),
                    recurso.getCostoUnitario(),
                    recurso.getPeso(),
                    recurso.getVolumen(),
                    recurso.getPlazoEntrega(),
                    recurso.getCodEstadoRecurso(),
                    recurso.getStock(),
                    recurso.getDisponible(),
                    recurso.getMinimo(),
                    recurso.getMaximo(),
                    recurso.getPedido(),
                    idRecurso // Aquí pasamos el idRecurso que recibimos como primer parámetro
            );

            // Verificar si alguna fila fue actualizada
            if (rowsAffected > 0) {
                System.out.println("Actualización exitosa. Filas afectadas: " + rowsAffected);
                return true; // Si se actualizó al menos una fila, la actualización fue exitosa
            } else {
                System.out.println("No se encontraron filas para actualizar.");
                return false;
            }
        } catch (DataAccessException e) {
            // Capturamos cualquier error de acceso a datos y lo mostramos en el log
            System.err.println("Error al ejecutar el update: " + e.getMessage());
            e.printStackTrace();
            return false; // Retornamos falso si hubo algún error
        }
    }
}
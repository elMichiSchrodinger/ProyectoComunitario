package com.harold.inventario.service;

import com.harold.inventario.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class GuiaRemisionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GuiaRemision> ObtenerGuiaRemisionRecepcion() {
        String sql = "SELECT " +
                "id_guia_remision AS \"ID\", " +
                "entidad_origendestino AS \"Contacto\", " +
                "fecha_programada AS \"Fecha Programada\", " +
                "documento_origen AS \"Documento de Origen\", " +
                "CASE " +
                "WHEN cod_estado_guia = '1' THEN 'Listo' " +
                "WHEN cod_estado_guia = '2' THEN 'Proceso' " +
                "ELSE 'Pendiente' END AS \"Estado\" " +
                "FROM guia_remision " +
                "WHERE cod_tipo_guia = '1' " +
                "ORDER BY fecha_programada;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            GuiaRemision guia = new GuiaRemision();
            guia.setIdGuiaRemision(rs.getString("ID"));
            guia.setEntidadOrigendestino(rs.getString("Contacto"));
            guia.setFechaProgramada(rs.getDate("Fecha Programada").toLocalDate());
            guia.setDocumentoOrigen(rs.getString("Documento de Origen"));
            guia.setCodEstadoGuia(rs.getString("Estado"));
            return guia;
        });
    }

    public List<GuiaRemision> ObtenerGuiaRemisionEntrega() {
        String sql = "SELECT " +
                "id_guia_remision AS \"ID\", " +
                "entidad_origendestino AS \"Contacto\", " +
                "fecha_programada AS \"Fecha Programada\", " +
                "documento_origen AS \"Documento de Origen\", " +
                "CASE " +
                "WHEN cod_estado_guia = '1' THEN 'Listo' " +
                "WHEN cod_estado_guia = '2' THEN 'Proceso' " +
                "ELSE 'Pendiente' END AS \"Estado\" " +
                "FROM guia_remision " +
                "WHERE cod_tipo_guia = '2' " +
                "ORDER BY fecha_programada;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            GuiaRemision guia = new GuiaRemision();
            guia.setIdGuiaRemision(rs.getString("ID"));
            guia.setEntidadOrigendestino(rs.getString("Contacto"));
            guia.setFechaProgramada(rs.getDate("Fecha Programada").toLocalDate());
            guia.setDocumentoOrigen(rs.getString("Documento de Origen"));
            guia.setCodEstadoGuia(rs.getString("Estado"));
            return guia;
        });
    }

    public GuiaRemision ObtenerGuiaRemisionCompleta(String idGuiaRemision) {
        String sqlGuia = "SELECT " +
                "id_guia_remision AS \"ID\", " +
                "entidad_origendestino AS \"Recibir de\", " +
                "cod_tipo_guia AS \"Tipo Guía\", " +
                "cod_estado_guia AS \"Estado\", " +
                "fecha_programada AS \"Fecha Programada\", " +
                "fecha_efectiva AS \"Fecha Efectiva\", " +
                "documento_origen AS \"Documento Origen\", " +
                "id_empleado AS \"ID Empleado\" " +
                "FROM guia_remision " +
                "WHERE id_guia_remision = ?";

        GuiaRemision guiaRemision = jdbcTemplate.query(sqlGuia, new Object[]{idGuiaRemision}, rs -> {
            if (rs.next()) {
                GuiaRemision guia = new GuiaRemision();
                guia.setIdGuiaRemision(rs.getString("ID"));
                guia.setEntidadOrigendestino(rs.getString("Recibir de"));
                guia.setCodTipoGuia(rs.getString("Tipo Guía"));
                guia.setCodEstadoGuia(rs.getString("Estado"));
                guia.setFechaProgramada(rs.getDate("Fecha Programada").toLocalDate());
                guia.setFechaEfectiva(rs.getDate("Fecha Efectiva").toLocalDate());
                guia.setDocumentoOrigen(rs.getString("Documento Origen"));

                // Mapeo de empleado
                Almacenero empleado = new Almacenero();
                empleado.setIdEmpleado(rs.getString("ID Empleado"));
                guia.setIdEmpleado(empleado);

                return guia;
            }
            return null;
        });

        if (guiaRemision != null) {
            guiaRemision.setDetalleGuias(obtenerDetallesPorGuia(idGuiaRemision));
        }
        return guiaRemision;
    }

    private LinkedHashSet<DetalleGuia> obtenerDetallesPorGuia(String idGuiaRemision) {
        String sqlDetalles = "SELECT " +
                "dg.id_detalle_guia AS \"ID Detalle Guía\", " +
                "dg.cantidad_producto AS \"Cantidad Producto\", " +
                "dg.cantidad_recurso AS \"Cantidad Recurso\", " +
                "dg.demanda AS \"Demanda\", " +
                "p.nombre_producto AS \"Nombre Producto\", " +
                "r.nombre AS \"Nombre Recurso\" " +
                "FROM detalle_guia dg " +
                "LEFT JOIN producto p ON dg.id_producto = p.id_producto " +
                "LEFT JOIN recurso r ON dg.id_recurso = r.id_recurso " +
                "WHERE dg.id_guia_remision = ?";

        List<DetalleGuia> detalles = jdbcTemplate.query(sqlDetalles, new Object[]{idGuiaRemision}, (rs, rowNum) -> {
            DetalleGuia detalle = new DetalleGuia();
            detalle.setIdDetalleGuia(rs.getString("ID Detalle Guía"));
            detalle.setCantidadProducto(rs.getInt("Cantidad Producto"));
            detalle.setCantidadRecurso(rs.getInt("Cantidad Recurso"));
            detalle.setDemanda(rs.getInt("Demanda"));

            // Relacionar el producto
            if (rs.getString("Nombre Producto") != null) {
                Producto producto = new Producto();
                producto.setNombreProducto(rs.getString("Nombre Producto"));
                detalle.getProductos().add(producto);
            }

            // Relacionar el recurso
            if (rs.getString("Nombre Recurso") != null) {
                Recurso recurso = new Recurso();
                recurso.setNombre(rs.getString("Nombre Recurso"));
                detalle.getRecursos().add(recurso);
            }

            return detalle;
        });

        return new LinkedHashSet<>(detalles);
    }

    @Transactional
    public void actualizarGuiaRemision(GuiaRemision guiaRemision) {
        String sql = "UPDATE guia_remision SET " +
                "entidad_origendestino = ?, " +
                "cod_tipo_guia = ?, " +
                "cod_estado_guia = ?, " +
                "fecha_programada = ?, " +
                "fecha_efectiva = ?, " +
                "documento_origen = ? " +
                "WHERE id_guia_remision = ?";
        jdbcTemplate.update(sql,
                guiaRemision.getEntidadOrigendestino(),
                guiaRemision.getCodTipoGuia(),
                guiaRemision.getCodEstadoGuia(),
                guiaRemision.getFechaProgramada(),
                guiaRemision.getFechaEfectiva(),
                guiaRemision.getDocumentoOrigen(),
                guiaRemision.getIdGuiaRemision());
    }



    @Transactional
    public void guardarDetalles(List<DetalleGuia> detalles, String guiaId) {
        try {
            for (DetalleGuia detalle : detalles) {
                // Si el detalle tiene un id_detalle_guia (ya existe en la base de datos), lo actualizamos
                if (detalle.getIdDetalleGuia() != null && !detalle.getIdDetalleGuia().isEmpty()) {
                    // Si el id_detalle_guia ya existe, lo actualizamos
                    actualizarDetalleGuia(detalle, guiaId);
                } else {
                    // Si no tiene id_detalle_guia (es nuevo), lo insertamos
                    detalle.setIdDetalleGuia(generarIdDetalle()); // Generamos un nuevo id
                    insertarDetalleGuia(detalle, guiaId);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar los detalles: " + e.getMessage(), e);
        }
    }




    private void insertarDetalle(DetalleGuia detalle, String guiaId) {
        // Generar el ID si es necesario
        if (detalle.getIdDetalleGuia() == null || detalle.getIdDetalleGuia().isEmpty()) {
            detalle.setIdDetalleGuia(generarIdDetalle());  // Generar el ID si es necesario
        }

        // Insertar el detalle
        String sql = "INSERT INTO detalle_guia (id_detalle_guia, cantidad_producto, cantidad_recurso, demanda, id_guia_remision, id_producto, id_recurso) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                detalle.getIdDetalleGuia(),
                detalle.getCantidadProducto() != null ? detalle.getCantidadProducto() : 0,
                detalle.getCantidadRecurso() != null ? detalle.getCantidadRecurso() : 0,
                detalle.getDemanda() != null ? detalle.getDemanda() : 0,
                guiaId,
                obtenerIdProducto(detalle.getProductos().iterator().next().getNombreProducto()),
                obtenerIdRecurso(detalle.getRecursos().iterator().next().getNombre())
        );
    }





    @Transactional
    public void actualizarDetalleGuia(DetalleGuia detalle, String guiaId) {
        String sql = "UPDATE detalle_guia SET " +
                "cantidad_producto = ?, " +
                "cantidad_recurso = ?, " +
                "demanda = ?, " +
                "id_producto = ?, " +
                "id_recurso = ? " +
                "WHERE id_detalle_guia = ? AND id_guia_remision = ?";

        // Obtener el ID del Producto
        String idProducto = obtenerIdProducto(detalle.getProductos().iterator().next().getNombreProducto());
        String idRecurso = obtenerIdRecurso(detalle.getRecursos().iterator().next().getNombre());

        // Verificar si los IDs de Producto y Recurso fueron encontrados
        if (idProducto == null || idRecurso == null) {
            throw new RuntimeException("Producto o Recurso no encontrados.");
        }

        // Actualizar el detalle de la guía
        jdbcTemplate.update(sql,
                detalle.getCantidadProducto(),
                detalle.getCantidadRecurso(),
                detalle.getDemanda(),
                idProducto,
                idRecurso,
                detalle.getIdDetalleGuia(),
                guiaId); // Usamos guiaId para asegurarnos de que estamos actualizando el detalle correspondiente a la guía
    }



    @Transactional
    public void eliminarDetalles(List<String> idsDetalle) {
        if (idsDetalle != null && !idsDetalle.isEmpty()) {
            String sql = "DELETE FROM detalle_guia WHERE id_detalle_guia = ?";
            for (String idDetalle : idsDetalle) {
                // Eliminar el detalle si tiene un id válido
                jdbcTemplate.update(sql, idDetalle);
            }
        }
    }


    @Transactional
    public void eliminarGuias(List<String> idsGuiaRemision) {
        try {
            // Eliminar los detalles relacionados con las guías
            String sqlEliminarDetalles = "DELETE FROM detalle_guia WHERE id_guia_remision = ?";
            for (String idGuia : idsGuiaRemision) {
                jdbcTemplate.update(sqlEliminarDetalles, idGuia);
            }

            // Eliminar las guías principales
            String sqlEliminarGuia = "DELETE FROM guia_remision WHERE id_guia_remision = ?";
            for (String idGuia : idsGuiaRemision) {
                jdbcTemplate.update(sqlEliminarGuia, idGuia);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al eliminar las guías o sus detalles: " + e.getMessage(), e);
        }
    }




    private String obtenerIdProducto(String nombreProducto) {
        String sql = "SELECT id_producto FROM producto WHERE nombre_producto = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{nombreProducto}, String.class);
        } catch (DataAccessException e) {
            return null;
        }
    }

    private String obtenerIdRecurso(String nombreRecurso) {
        String sql = "SELECT id_recurso FROM recurso WHERE nombre = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{nombreRecurso}, String.class);
        } catch (DataAccessException e) {
            return null;
        }
    }

    private String generarIdDetalle() {
        // Obtener el último ID generado en la base de datos (en formato DGXXXX)
        String sql = "SELECT id_detalle_guia FROM detalle_guia ORDER BY id_detalle_guia DESC LIMIT 1";
        String ultimoId = jdbcTemplate.queryForObject(sql, String.class);

        // Si no existe un ID (es el primer ID), generar el ID inicial
        if (ultimoId == null) {
            return "DG0001";
        }

        // Extraer el número del ID (sin "DG")
        String numeroStr = ultimoId.substring(2);
        int numero = Integer.parseInt(numeroStr);

        // Incrementar el número
        numero++;

        // Formatear el número para que tenga 4 dígitos (con ceros a la izquierda)
        String nuevoNumero = String.format("%04d", numero);

        // Devolver el nuevo ID en formato "DGXXXX"
        return "DG" + nuevoNumero;
    }




    public String generarNuevoIdGuia() {
        String sql = "SELECT id_guia_remision FROM guia_remision ORDER BY id_guia_remision DESC LIMIT 1";
        String ultimoId = jdbcTemplate.queryForObject(sql, String.class);

        // Asegurémonos de que estamos obteniendo un ID válido y que el formato es correcto
        System.out.println("Último ID de guía: " + ultimoId);

        if (ultimoId != null && !ultimoId.isEmpty()) {
            int ultimoNumero = Integer.parseInt(ultimoId.substring(2));  // Tomamos los últimos 4 dígitos
            int siguienteNumero = ultimoNumero + 1;
            return String.format("GR%04d", siguienteNumero);  // Generamos el siguiente ID en formato GRxxxx
        } else {
            return "GR0001";  // Si no hay registros, iniciamos con "GR0001"
        }
    }


    @Transactional
    public void insertarDetalleGuia(DetalleGuia detalle, String guiaId) {
        // Generar un nuevo ID para el detalle de la guía
        String nuevoIdDetalleGuia = generarIdDetalle();

        // Obtener el ID del Producto
        String idProducto = obtenerIdProducto(detalle.getProductos().iterator().next().getNombreProducto());
        String idRecurso = obtenerIdRecurso(detalle.getRecursos().iterator().next().getNombre());

        // Verificar si los IDs de Producto y Recurso fueron encontrados
        if (idProducto == null || idRecurso == null) {
            throw new RuntimeException("Producto o Recurso no encontrados.");
        }

        // Sentencia SQL para insertar el nuevo detalle de la guía
        String sql = "INSERT INTO detalle_guia (id_detalle_guia, cantidad_producto, cantidad_recurso, demanda, id_guia_remision, id_producto, id_recurso) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Insertar el detalle de la guía
        jdbcTemplate.update(sql,
                nuevoIdDetalleGuia,
                detalle.getCantidadProducto(),
                detalle.getCantidadRecurso(),
                detalle.getDemanda(),
                guiaId,
                idProducto,
                idRecurso);
    }



    // Método para crear una nueva guía de remisión utilizando SQL
    @Transactional
    public GuiaRemision crearGuiaConDetalles(GuiaRemision guiaRemision) {
        // Generamos el ID de la nueva guía
        String idGuiaRemision = generarNuevoIdGuia();

        // Imprimimos el ID generado
        System.out.println("Generando nueva Guía de Remisión con ID: " + idGuiaRemision);

        // Imprimir todos los datos recibidos de la guía de remisión
        System.out.println("Datos recibidos para la nueva guía de remisión:");
        System.out.println("ID Guía: " + idGuiaRemision);
        System.out.println("Entidad Origen/Destino: " + guiaRemision.getEntidadOrigendestino());
        System.out.println("Tipo de Guía: " + guiaRemision.getCodTipoGuia());
        System.out.println("Estado de la Guía: " + guiaRemision.getCodEstadoGuia());
        System.out.println("Fecha Programada: " + guiaRemision.getFechaProgramada());
        System.out.println("Fecha Efectiva: " + guiaRemision.getFechaEfectiva());
        System.out.println("Documento de Origen: " + guiaRemision.getDocumentoOrigen());
        System.out.println("ID Empleado: " + guiaRemision.getIdEmpleado().getIdEmpleado());

        // Sentencia SQL para insertar la nueva guía de remisión
        String sql = "INSERT INTO guia_remision (id_guia_remision, entidad_origendestino, cod_tipo_guia, cod_estado_guia, fecha_programada, fecha_efectiva, documento_origen, id_empleado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Ejecución del SQL para insertar la guía de remisión
            jdbcTemplate.update(sql, idGuiaRemision, guiaRemision.getEntidadOrigendestino(),
                    guiaRemision.getCodTipoGuia(), guiaRemision.getCodEstadoGuia(),
                    guiaRemision.getFechaProgramada(), guiaRemision.getFechaEfectiva(),
                    guiaRemision.getDocumentoOrigen(), guiaRemision.getIdEmpleado().getIdEmpleado());

            // Confirmación de la inserción de la guía
            System.out.println("Guía de remisión insertada correctamente con ID: " + idGuiaRemision);
        } catch (Exception e) {
            System.err.println("Error al insertar la guía de remisión: " + e.getMessage());
            throw new RuntimeException("Error al insertar la guía: " + e.getMessage(), e);
        }

        // Si hay detalles, los insertamos
        if (guiaRemision.getDetalleGuias() != null && !guiaRemision.getDetalleGuias().isEmpty()) {
            for (DetalleGuia detalle : guiaRemision.getDetalleGuias()) {
                // Llamamos a la función insertarDetalle para cada detalle
                insertarDetalle(detalle, idGuiaRemision);  // Insertamos el detalle asociado a la guía
            }
        }

        // Establecer el ID de la nueva guía
        guiaRemision.setIdGuiaRemision(idGuiaRemision);

        // Retornamos la guía de remisión con los detalles insertados
        return guiaRemision;
    }

    // Función para obtener detalles de la guía de remisión para el submódulo de entregas









}





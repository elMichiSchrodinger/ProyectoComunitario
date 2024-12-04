package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @Column(name = "id_producto", nullable = false, length = 6)
    private String idProducto;

    @Column(name = "nombre_producto", nullable = false, length = 50)
    private String nombreProducto;

    @Column(name = "descripcion_producto", nullable = false, length = 200)
    private String descripcionProducto;

    @Column(name = "cod_tipo_producto", nullable = false, length = Integer.MAX_VALUE)
    private String codTipoProducto;

    @Column(name = "rastreo_inventario", nullable = false)
    private Boolean rastreoInventario = false;

    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(name = "costo_fabricacion", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoFabricacion;

    @Column(name = "peso", nullable = false, precision = 7, scale = 2)
    private BigDecimal peso;

    @Column(name = "volumen", nullable = false, precision = 7, scale = 2)
    private BigDecimal volumen;

    @Column(name = "plazo_entrega", nullable = false)
    private Integer plazoEntrega;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_detalle_orden", nullable = false)
    private DetalleOrden idDetalleOrden;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_detalle_guia", nullable = false)
    private DetalleGuia idDetalleGuia;

}
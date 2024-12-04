package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "recurso")
public class Recurso {
    @Id
    @Column(name = "id_recurso", nullable = false, length = 6)
    private String idRecurso;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "cod_tipo_recurso", nullable = false, length = Integer.MAX_VALUE)
    private String codTipoRecurso;

    @Column(name = "rastreo_inventario", nullable = false)
    private Boolean rastreoInventario = false;

    @Column(name = "costo_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(name = "peso", nullable = false, precision = 7, scale = 2)
    private BigDecimal peso;

    @Column(name = "volumen", nullable = false, precision = 7, scale = 2)
    private BigDecimal volumen;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "disponible", nullable = false)
    private Integer disponible;

    @Column(name = "minimo", nullable = false)
    private Integer minimo;

    @Column(name = "maximo", nullable = false)
    private Integer maximo;

    @Column(name = "pedido", nullable = false)
    private Integer pedido;

    @Column(name = "plazo_entrega", nullable = false)
    private Integer plazoEntrega;

    @Column(name = "cod_estado_recurso", nullable = false, length = Integer.MAX_VALUE)
    private String codEstadoRecurso;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_informe_stock", nullable = false)
    private InformeStock idInformeStock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_detalle_orden", nullable = false)
    private DetalleOrden idDetalleOrden;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_detalle_guia", nullable = false)
    private DetalleGuia idDetalleGuia;

}
package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "detalle_orden")
public class DetalleOrden {
    @Id
    @Column(name = "id_detalle_orden", nullable = false, length = 6)
    private String idDetalleOrden;

    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidadProducto;

    @Column(name = "cantidad_recurso", nullable = false)
    private Integer cantidadRecurso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_orden_produccion", nullable = false)
    private com.harold.inventario.model.OrdenProduccion idOrdenProduccion;

    @OneToMany(mappedBy = "idDetalleOrden")
    private Set<com.harold.inventario.model.Producto> productos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idDetalleOrden")
    private Set<com.harold.inventario.model.Recurso> recursos = new LinkedHashSet<>();

}
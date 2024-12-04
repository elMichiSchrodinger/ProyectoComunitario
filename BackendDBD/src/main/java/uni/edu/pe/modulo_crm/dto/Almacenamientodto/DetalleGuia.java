package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "detalle_guia")
public class DetalleGuia {
    @Id
    @Column(name = "id_detalle_guia", nullable = false, length = 6)
    private String idDetalleGuia;

    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidadProducto;

    @Column(name = "cantidad_recurso", nullable = false)
    private Integer cantidadRecurso;

    @Column(name = "demanda", nullable = false)
    private Integer demanda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_guia_remision", nullable = false)
    private com.harold.inventario.model.GuiaRemision idGuiaRemision;

    @OneToMany(mappedBy = "idDetalleGuia")
    private Set<com.harold.inventario.model.Producto> productos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idDetalleGuia")
    private Set<com.harold.inventario.model.Recurso> recursos = new LinkedHashSet<>();

}
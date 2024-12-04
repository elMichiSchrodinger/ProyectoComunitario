package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orden_produccion")
public class OrdenProduccion {
    @Id
    @Column(name = "id_orden_produccion", nullable = false, length = 6)
    private String idOrdenProduccion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_termino", nullable = false)
    private LocalDate fechaTermino;

    @Column(name = "cod_estado_orden", nullable = false, length = Integer.MAX_VALUE)
    private String codEstadoOrden;

    @Column(name = "cod_prioridad_orden", nullable = false, length = Integer.MAX_VALUE)
    private String codPrioridadOrden;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empleado", nullable = false)
    private JefeDeAlmacen idEmpleado;

    @OneToMany(mappedBy = "idOrdenProduccion")
    private Set<DetalleOrden> detalleOrdens = new LinkedHashSet<>();

}
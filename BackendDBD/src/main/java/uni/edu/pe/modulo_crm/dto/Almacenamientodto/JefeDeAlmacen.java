package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "jefe_de_almacen")
public class JefeDeAlmacen {
    @Id
    @Column(name = "id_empleado", nullable = false, length = 6)
    private String idEmpleado;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "id_jefe_almacen", nullable = false, length = 6)
    private String idJefeAlmacen;

    @Column(name = "almacen_asignado", nullable = false)
    private Integer almacenAsignado;

    @OneToMany(mappedBy = "idEmpleado")
    private Set<InformeStock> informeStocks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEmpleado")
    private Set<com.harold.inventario.model.OrdenProduccion> ordenProduccions = new LinkedHashSet<>();

}
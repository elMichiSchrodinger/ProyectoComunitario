package com.harold.inventario.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "almacenero")
public class Almacenero {
    @Id
    @Column(name = "id_empleado", nullable = false, length = 6)
    private String idEmpleado;

    @Column(name = "id_almacenero", nullable = false, length = 6)
    private String idAlmacenero;

    @Column(name = "almacen_asignado", nullable = false)
    private Integer almacenAsignado;

    @OneToMany(mappedBy = "idEmpleado")
    private Set<com.harold.inventario.model.GuiaRemision> guiaRemisions = new LinkedHashSet<>();

}
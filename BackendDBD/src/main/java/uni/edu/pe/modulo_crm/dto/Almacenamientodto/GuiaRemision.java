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
@Table(name = "guia_remision")
public class GuiaRemision {
    @Id
    @Column(name = "id_guia_remision", nullable = false, length = 6)
    private String idGuiaRemision;

    @Column(name = "cod_tipo_guia", nullable = false, length = Integer.MAX_VALUE)
    private String codTipoGuia;

    @Column(name = "entidad_origendestino", nullable = false, length = 50)
    private String entidadOrigendestino;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDate fechaProgramada;

    @Column(name = "fecha_efectiva", nullable = false)
    private LocalDate fechaEfectiva;

    @Column(name = "cod_tipo_operacion", nullable = false, length = Integer.MAX_VALUE)
    private String codTipoOperacion;

    @Column(name = "cod_estado_guia", nullable = false, length = Integer.MAX_VALUE)
    private String codEstadoGuia;

    @Column(name = "documento_origen", nullable = false, length = 50)
    private String documentoOrigen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Almacenero idEmpleado;

    @OneToMany(mappedBy = "idGuiaRemision")
    private Set<DetalleGuia> detalleGuias = new LinkedHashSet<>();

}
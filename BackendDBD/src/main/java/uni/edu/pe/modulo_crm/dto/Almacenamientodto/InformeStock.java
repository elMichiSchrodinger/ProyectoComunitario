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
@Table(name = "informe_stock")
public class InformeStock {
    @Id
    @Column(name = "id_informe_stock", nullable = false, length = 6)
    private String idInformeStock;

    @Column(name = "mes_informe", nullable = false)
    private Integer mesInforme;

    @Column(name = "\"año_informe\"", nullable = false)
    private Integer añoInforme;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDate fechaGeneracion;

    @Column(name = "cantidad_actual_recurso", nullable = false)
    private Integer cantidadActualRecurso;

    @Column(name = "cantidad_actual_producto", nullable = false)
    private Integer cantidadActualProducto;

    @Column(name = "stock_inicial", nullable = false)
    private Integer stockInicial;

    @Column(name = "stock_final", nullable = false)
    private Integer stockFinal;

    @Column(name = "stock_real", nullable = false)
    private Integer stockReal;

    @Column(name = "stock_teorico", nullable = false)
    private Integer stockTeorico;

    @Column(name = "observaciones", nullable = false, length = 200)
    private String observaciones;

    @Column(name = "estado_informe", nullable = false, length = Integer.MAX_VALUE)
    private String estadoInforme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empleado", nullable = false)
    private com.harold.inventario.model.JefeDeAlmacen idEmpleado;

    @OneToMany(mappedBy = "idInformeStock")
    private Set<com.harold.inventario.model.Recurso> recursos = new LinkedHashSet<>();

}
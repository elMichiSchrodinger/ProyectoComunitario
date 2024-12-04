package com.harold.inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "id_empleado", nullable = false, length = 6)
    private String idEmpleado;

    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 70)
    private String apellido;

    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;

    @Column(name = "tipo_empleado", nullable = false, length = 50)
    private String tipoEmpleado;

    @Column(name = "salario", nullable = false, precision = 8, scale = 2)
    private BigDecimal salario;

    @Column(name = "departamento", nullable = false, length = 50)
    private String departamento;

    @Column(name = "turno", nullable = false, length = 30)
    private String turno;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

}
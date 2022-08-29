package com.aucsoft.serviceclientes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Data
@Table(name="tbl_personas")
@AllArgsConstructor@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty (message = "El campo no puede ser vacio")
    @Size(min=10,max = 13, message = "La longitud debe ser mayo o igual a 10 y menor o igual a 13")
    @Column(unique = true,nullable = false)
    private  String identificacion;
    @NotEmpty (message = "El campo no puede ser vacio")
    private String nombre;
    @NotEmpty (message = "El campo no puede ser vacio")
    private String genero;
    private int edad;
    private String direccion;
    @Size(min=7,max = 10, message = "La longitud debe ser mayo o igual a 7 y menor o igual a 10")
    private  String telefono;

}

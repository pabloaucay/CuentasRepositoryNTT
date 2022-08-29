package com.aucsoft.servicemovimientos.Model;

import lombok.Data;

@Data
public class Persona {
    private Long id;
    private  String identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private  String telefono;
}
package com.aucsoft.serviceclientes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name="tbl_clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona{
    @NotEmpty(message = "El campo no puede ser vacio")
    @Column(unique = true,nullable = false)
    private String clienteid;
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty (message = "El campo no puede ser vacio")
    private  String contrasenia;
    @Column(columnDefinition = "boolean default true")
    private boolean estado;
    @Builder
    public Cliente(Long id,String identificacion, String nombre, String genero, int edad, String direccion, String telefono, String clienteId,String contrasenia, boolean estado) {
        super(id,identificacion, nombre, genero, edad, direccion, telefono);
        this.clienteid = clienteId;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }
}

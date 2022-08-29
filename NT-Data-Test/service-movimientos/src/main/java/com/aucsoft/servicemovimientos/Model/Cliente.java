package com.aucsoft.servicemovimientos.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cliente extends Persona{
    private String clienteid;
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
    private  String contrasenia;
    private boolean estado;

}

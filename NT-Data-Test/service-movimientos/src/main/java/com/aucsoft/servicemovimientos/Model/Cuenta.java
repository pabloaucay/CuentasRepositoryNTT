package com.aucsoft.servicemovimientos.Model;

import lombok.Data;

@Data
public class Cuenta {
    private String numeroCuenta;
    private Long idCliente;
    private TipoCuenta tipoCuenta;
    private Long saldoInicial;
    private boolean estado;
    private Cliente cliente;

}
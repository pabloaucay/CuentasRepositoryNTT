package com.aucsoft.servicecuentas;

import com.aucsoft.servicecuentas.entity.Cuenta;
import com.aucsoft.servicecuentas.entity.TipoCuenta;
import com.aucsoft.servicecuentas.repository.CuentaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CuentaRespositoryTest {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    public void whenFindCuentaByNumeroCuenta_thenReturnCuenta(){
        Cuenta cuenta= Cuenta.builder()
            .estado(true)
            .numeroCuenta("000005")
            .idCliente(1L)
            .saldoInicial(105L)
            .tipoCuenta(TipoCuenta.builder().id(1).build())
            .build();
        cuentaRepository.save(cuenta);
        Cuenta founds= cuentaRepository.findBynumeroCuenta(cuenta.getNumeroCuenta());
        Assertions.assertEquals(founds.getIdCliente(),cuenta.getIdCliente());

    }
}

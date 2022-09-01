package com.aucsoft.servicecuentas;

import com.aucsoft.servicecuentas.entity.Cuenta;
import com.aucsoft.servicecuentas.entity.TipoCuenta;
import com.aucsoft.servicecuentas.repository.CuentaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CuentaRespositoryTest {
    @Autowired
    private CuentaRepository cuentaRepository;

    public void whenFindCuentaByNumeroCuenta_thenReturnCuenta(){
        Cuenta cuenta= Cuenta.builder()
            .estado(true)
            .numeroCuenta("000005")
            .idCliente(1L)
            .saldoInicial(105L)
            .tipoCuenta(TipoCuenta.builder().id(1).build())
            .build();
        cuentaRepository.save(cuenta);
        List<Cuenta> founds= cuentaRepository.findByidCliente(1L);
        Assertions.assertEquals(founds.size(),1 );

    }
}

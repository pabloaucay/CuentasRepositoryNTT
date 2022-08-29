package com.aucsoft.servicemovimientos.client;

import com.aucsoft.servicemovimientos.Model.Cuenta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-cuentas",path = "/cuentas")

public interface CuentaClient {
    @GetMapping
    public ResponseEntity<List<Cuenta>> getCuentaByIdentificacion(@RequestParam(name = "idcliente",required = true) Long idCliente);
    @GetMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Cuenta> getCuentaByNumeroCuenta(@PathVariable("numeroCuenta") String numeroCuenta);
}

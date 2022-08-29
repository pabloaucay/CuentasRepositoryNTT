package com.aucsoft.servicecuentas.client;

import com.aucsoft.servicecuentas.Model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-clientes",path = "/clientes")
public interface ClienteClient {
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id);

}

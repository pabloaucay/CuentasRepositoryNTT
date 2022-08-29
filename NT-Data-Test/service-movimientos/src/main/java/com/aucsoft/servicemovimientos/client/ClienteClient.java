package com.aucsoft.servicemovimientos.client;

import com.aucsoft.servicemovimientos.Model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-clientes",path = "/clientes")
public interface ClienteClient {
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id);
    @GetMapping
    public ResponseEntity<Cliente> getClienteByIdentificacion(@RequestParam(name = "identificacion",required = true) String identificacion);

}
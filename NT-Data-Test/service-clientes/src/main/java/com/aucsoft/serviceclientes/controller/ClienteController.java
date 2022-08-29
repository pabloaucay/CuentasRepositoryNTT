package com.aucsoft.serviceclientes.controller;

import com.aucsoft.serviceclientes.entity.Cliente;
import com.aucsoft.serviceclientes.service.ClienteService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping (value = "/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @JsonIgnore
    @GetMapping
    public ResponseEntity<Cliente> getClienteByIdentificacion(@RequestParam(name = "identificacion",required = true) String identificacion){
        Cliente cliente= clienteService.getCliente(identificacion);
        if(cliente==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id){
        Cliente cliente= clienteService.getCliente(id);
        if(cliente==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }
    @PostMapping
    public  ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,formatMessage(result));
        }
        Cliente clienteCreate= clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreate);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id,@RequestBody Cliente cliente){
        cliente.setId(id);
        Cliente clienteDB= clienteService.updateCliente(cliente);
        if(clienteDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") Long id){
        Cliente cliente= clienteService.deleteCliente(id);
        if(cliente==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }
    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}

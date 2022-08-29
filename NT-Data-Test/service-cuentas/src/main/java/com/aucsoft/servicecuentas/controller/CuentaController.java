package com.aucsoft.servicecuentas.controller;

import com.aucsoft.servicecuentas.entity.Cuenta;
import com.aucsoft.servicecuentas.service.CuentaService;
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
@RequestMapping (value = "/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaServicee;
    @GetMapping
    public ResponseEntity<List<Cuenta>> getCuentaByIdentificacion(@RequestParam(name = "idcliente",required = true) Long idCliente){
        List<Cuenta> cuentas= cuentaServicee.getCuentasByIdCliente(idCliente);
        if(cuentas==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuentas);
    }
    @GetMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Cuenta> getCuentaByNumeroCuenta(@PathVariable("numeroCuenta") String numeroCuenta){
        Cuenta cuenta= cuentaServicee.getCuenta(numeroCuenta);
        if(cuenta==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuenta);
    }
    @PostMapping
    public  ResponseEntity<Cuenta> createCuenta(@Valid @RequestBody Cuenta cuenta, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,formatMessage(result));
        }
        Cuenta cuentaCreate= cuentaServicee.createCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaCreate);
    }
    @PutMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Cuenta> updateCliente(@PathVariable("numeroCuenta") String numeroCuenta,@RequestBody Cuenta cuenta){
        cuenta.setNumeroCuenta(numeroCuenta);
        Cuenta cuentaDB= cuentaServicee.updateCuenta(cuenta);
        if(cuentaDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cuentaDB);
    }

    @DeleteMapping(value = "/{numeroCuenta}")
    public ResponseEntity<Cuenta> deleteCliente(@PathVariable("numeroCuenta") String numeroCuenta){
        Cuenta cuenta= cuentaServicee.deleteCuenta(numeroCuenta);
        if(cuenta==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuenta);
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

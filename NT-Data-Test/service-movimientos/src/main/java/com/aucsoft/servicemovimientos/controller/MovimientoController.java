package com.aucsoft.servicemovimientos.controller;

import com.aucsoft.servicemovimientos.client.CuentaClient;
import com.aucsoft.servicemovimientos.entity.Movimiento;
import com.aucsoft.servicemovimientos.exception.SaldoInsuficienteException;
import com.aucsoft.servicemovimientos.service.MovimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;
    @GetMapping
    public ResponseEntity<List<Movimiento>> getMovimientosByNumeroCuenta(@RequestParam(name = "numeroCuenta",required = true) String numeroCuenta){
        List<Movimiento> movimientos= movimientoService.getMovimientosByNumeroCuenta(numeroCuenta);
        if(movimientos==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
    }
    @GetMapping(value = "/{numeroCuenta}/reportecuenta")
    public ResponseEntity<List<Movimiento>> getMovimientosByNumeroCuentaAndFecha(@RequestParam(name = "inicio",required = true) @DateTimeFormat(pattern="yyyy-MM-dd")Date inicio, @RequestParam(name = "fin",required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date fin, @PathVariable("numeroCuenta") String numeroCuenta){
        inicio.setHours(0);
        inicio.setMinutes(0);
        inicio.setSeconds(0);
        fin.setHours(23);
        fin.setMinutes(59);
        fin.setSeconds(59);
        List<Movimiento> movimientos= movimientoService.getMovimientosByNumeroCuenta(numeroCuenta,inicio,fin);
        if(movimientos==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
    }
    @GetMapping(value = "/{idCliente}/reportecliente")
    public ResponseEntity<List<Movimiento>> getMovimientosByIdClienteAndFecha(@RequestParam(name = "inicio",required = true) @DateTimeFormat(pattern="yyyy-MM-dd")Date inicio, @RequestParam(name = "fin",required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date fin, @PathVariable("idCliente") Long idCliente){
        inicio.setHours(0);
        inicio.setMinutes(0);
        inicio.setSeconds(0);
        fin.setHours(23);
        fin.setMinutes(59);
        fin.setSeconds(59);
        List<Movimiento> movimientos= movimientoService.getMovimientoByIdCliente(idCliente,inicio,fin);
        if(movimientos==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
    }
    @PostMapping
    public  ResponseEntity<Movimiento> creatMovimiento(@RequestBody Movimiento movimiento){
        Movimiento movimientoCreate;
        try {
            movimientoCreate= movimientoService.createMovimiento(movimiento);
        }catch (SaldoInsuficienteException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El debito debe ser menor o igual al saldo disponible");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoCreate);
    }
}

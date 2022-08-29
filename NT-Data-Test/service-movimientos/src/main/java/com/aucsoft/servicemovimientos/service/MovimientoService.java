package com.aucsoft.servicemovimientos.service;

import com.aucsoft.servicemovimientos.entity.Movimiento;
import com.aucsoft.servicemovimientos.exception.SaldoInsuficienteException;

import java.util.Date;
import java.util.List;

public interface MovimientoService {
    public List<Movimiento> getMovimientosByNumeroCuenta(String numeroCuenta, Date inicio, Date fin);
    public List<Movimiento> getMovimientosByNumeroCuenta(String numeroCuenta);
    public  Movimiento getlastMovimientoByNumeroCuenta(String numeroCuenta);
    public  List<Movimiento> getMovimientoByIdCliente(Long idCliente , Date inicio, Date fin);
    public  Movimiento createMovimiento(Movimiento movimiento) throws SaldoInsuficienteException;
}

package com.aucsoft.servicecuentas.service;

import com.aucsoft.servicecuentas.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    public List<Cuenta> listAllCuenta();
    public Cuenta getCuenta(String numeroCuenta);
    public  List<Cuenta> getCuentasByIdCliente(Long idCliente );

    public Cuenta updateCuenta(Cuenta cuenta);
    public  Cuenta deleteCuenta(String numeroCuenta);
    public  Cuenta createCuenta(Cuenta cuenta);
}

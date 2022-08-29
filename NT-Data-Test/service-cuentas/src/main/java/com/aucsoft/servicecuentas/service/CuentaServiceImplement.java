package com.aucsoft.servicecuentas.service;

import com.aucsoft.servicecuentas.Model.Cliente;
import com.aucsoft.servicecuentas.client.ClienteClient;
import com.aucsoft.servicecuentas.entity.Cuenta;
import com.aucsoft.servicecuentas.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuentaServiceImplement implements CuentaService{
    @Autowired
    private final ClienteClient clienteClient;
    private final CuentaRepository cuentaRepository;
    @Override
    public List<Cuenta> listAllCuenta() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta getCuenta(String numeroCuenta) {
        Cuenta cuenta=cuentaRepository.findBynumeroCuenta(numeroCuenta);
        if (cuenta==null|| !cuenta.isEstado()){
            return null;
        }
        cuenta.setCliente(clienteClient.getClienteById(cuenta.getIdCliente()).getBody());
        return cuenta;
    }
    @Override
    public List<Cuenta> getCuentasByIdCliente(Long idCliente) {
        List<Cuenta> cuentas=cuentaRepository.findByidCliente(idCliente);
        if (cuentas.isEmpty()){
            return null;
        }
        Cliente cliente= clienteClient.getClienteById(idCliente).getBody();
        List<Cuenta>rCuentas= cuentas.stream().map(cuentaItem-> {
            cuentaItem.setCliente(cliente);
            return cuentaItem;
        }).collect(Collectors.toList());
        return rCuentas;
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta) {
        Cuenta cuentaDB=getCuenta(cuenta.getNumeroCuenta());
        if(cuentaDB==null){
            return null;
        }
        cuentaDB.setEstado(cuenta.isEstado());
        cuentaDB.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaRepository.save(cuentaDB);
        return cuentaDB;
    }

    @Override
    public Cuenta deleteCuenta(String numeroCuenta) {
        Cuenta cuentaDB=getCuenta(numeroCuenta);
        if(cuentaDB==null){
            return null;
        }
        cuentaDB.setEstado(false);
        cuentaRepository.save(cuentaDB);
        return cuentaDB;
    }

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        Cuenta cuentaDB=getCuenta(cuenta.getNumeroCuenta());
        if(cuentaDB!=null){
            cuentaDB.setEstado(true);
            cuentaRepository.save(cuentaDB);
            return cuentaDB;
        }
        return cuentaRepository.save(cuenta);
    }
}

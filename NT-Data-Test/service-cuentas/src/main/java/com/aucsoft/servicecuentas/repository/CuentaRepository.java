package com.aucsoft.servicecuentas.repository;


import com.aucsoft.servicecuentas.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository  extends JpaRepository<Cuenta,String> {
    public List<Cuenta> findByidCliente(Long idCliente);
    public Cuenta findBynumeroCuenta(String numeroCuenta);
}

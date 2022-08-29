package com.aucsoft.serviceclientes.repository;

import com.aucsoft.serviceclientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    public  Cliente findByclienteid(String clienteid);
    public  Cliente findByidentificacion(String identificacion);
}

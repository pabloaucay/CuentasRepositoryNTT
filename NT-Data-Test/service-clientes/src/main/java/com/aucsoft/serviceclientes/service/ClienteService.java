package com.aucsoft.serviceclientes.service;

import com.aucsoft.serviceclientes.entity.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> listAllCliente();
    public  Cliente getCliente(Long id);

    public Cliente getCliente (String identificacion);
    public Cliente updateCliente(Cliente cliente);
    public  Cliente deleteCliente(Long id);
    public  Cliente createCliente(Cliente cliente);
}

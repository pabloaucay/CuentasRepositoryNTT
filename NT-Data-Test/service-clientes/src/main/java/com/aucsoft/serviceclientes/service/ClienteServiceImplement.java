package com.aucsoft.serviceclientes.service;

import com.aucsoft.serviceclientes.entity.Cliente;
import com.aucsoft.serviceclientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImplement implements  ClienteService{
    private final ClienteRepository clienteRepository;
    @Override
    public List<Cliente> listAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getCliente(Long id) {
        Cliente cliente=clienteRepository.findById(id).orElse(
                null);
        if (cliente==null|| !cliente.isEstado()){
            return null;
        }
        return cliente;

    }

    @Override
    public Cliente getCliente(String identificacion) {
        Cliente cliente=clienteRepository.findByidentificacion(identificacion);
        return cliente;
    }

    @Override
    public Cliente updateCliente(Cliente cliente) {
        EncryptServiceImplement encryptServiceImplement= new EncryptServiceImplement();
        Cliente clienteDB=getCliente(cliente.getId());
        if(clienteDB==null){
            return null;
        }
        clienteDB.setDireccion(cliente.getDireccion());
        clienteDB.setEdad(cliente.getEdad());
        if(cliente.getContrasenia()!=null&&!cliente.getContrasenia().isEmpty()){
            cliente.setContrasenia(encryptServiceImplement.encryptValue(cliente.getContrasenia()));
        }
        clienteDB.setTelefono(cliente.getTelefono());
        clienteRepository.save(clienteDB);
        return clienteDB;

    }

    @Override
    public Cliente deleteCliente(Long id) {
        Cliente clienteDB=getCliente(id);
        if(clienteDB==null){
            return null;
        }
        clienteDB.setEstado(false);
        clienteRepository.save(clienteDB);
        return clienteDB;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        Cliente clienteDB=getCliente(cliente.getIdentificacion());
        if(clienteDB!=null){
            clienteDB.setEstado(true);
            clienteRepository.save(clienteDB);
            return clienteDB;
        }
        EncryptServiceImplement encryptServiceImplement= new EncryptServiceImplement();
        cliente.setEstado(true);
        cliente.setContrasenia(encryptServiceImplement.encryptValue(cliente.getContrasenia()));
        return clienteRepository.save(cliente);
    }
}

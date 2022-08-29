package com.aucsoft.serviceclientes;

import com.aucsoft.serviceclientes.entity.Cliente;
import com.aucsoft.serviceclientes.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ClienteRepositoryTest {
    @Autowired
    private  ClienteRepository clienteRepository;
    @Test
    public void whenFindClienteById_thenReturnListCliente(){
        Cliente cliente= Cliente.builder()
                .clienteId("010234569")
                .identificacion("0101806289")
                .contrasenia("Prueba Insert")
                .estado(true)
                .nombre("Dolores Barros")
                .direccion("La Paz")
                .edad(68)
                .genero("f")
                .telefono("074139858")
                .build();
        clienteRepository.save(cliente);
        Optional<Cliente> founds= clienteRepository.findById(cliente.getId());
        Assertions.assertEquals(founds.get().getClienteid(),cliente.getClienteid());

    }
}

package com.aucsoft.serviceclientes;

import com.aucsoft.serviceclientes.entity.Cliente;
import com.aucsoft.serviceclientes.repository.ClienteRepository;
import com.aucsoft.serviceclientes.service.ClienteService;
import com.aucsoft.serviceclientes.service.ClienteServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    private ClienteService clienteService;
    @BeforeEach
    public void loadData(){
        MockitoAnnotations.initMocks(this);
        clienteService= new ClienteServiceImplement(clienteRepository);
        Cliente cliente= Cliente.builder()
                .id(9L)
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
        Mockito.when(clienteRepository.findById(9L)).thenReturn(Optional.of(cliente));
    }
    @Test
    public void whenValidGetId_ThenReturnCliente(){
        Cliente found= clienteService.getCliente(9L);
        Assertions.assertEquals(found.getClienteid(),"010234569");
    }
}

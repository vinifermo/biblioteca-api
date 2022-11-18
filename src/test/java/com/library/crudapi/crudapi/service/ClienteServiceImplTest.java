package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class ClienteServiceImplTest {
    @Mock
    private ClienteRepository clienteRepositoryMock;

    private Cliente cliente;
    private ClienteRequestDTO clienteRequestDTO;

    private Optional<Cliente> optionalCliente;
    private ClienteServiceImpl service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new ClienteServiceImpl(clienteRepositoryMock);
        cliente = new Cliente();
        optionalCliente = Optional.of(cliente);
        clienteRequestDTO = new ClienteRequestDTO();
    }

    @Test
    void WhenFindAllThenReturnListOfClient() {
        when(clienteRepositoryMock.findAll()).thenReturn(List.of(cliente));
        List<Cliente> response = service.findAll();

        assertNotNull(response);
    }

    @Test
    void whenUpdateReturnSucess() {
        when(clienteRepositoryMock.findById(any(UUID.class))).thenReturn(optionalCliente);
        when(clienteRepositoryMock.save(any())).thenReturn(cliente);

        service.update(UUID.randomUUID(),clienteRequestDTO);

        verify(clienteRepositoryMock, times(1)).save(any(Cliente.class));
        verify(clienteRepositoryMock, times(0)).findById(UUID.randomUUID());
    }

    @Test
    void whenFindByIdThenReturnCliente() {
        when(clienteRepositoryMock.findById(any(UUID.class))).thenReturn(optionalCliente);
        Cliente response = service.findById(UUID.randomUUID());

        assertNotNull(response);
        assertEquals(Cliente.class,response.getClass());
    }

    @Test
    void whenDeleteClienteByIdReturNoContent() {
        lenient().when(clienteRepositoryMock.findById(any(UUID.class))).thenReturn(optionalCliente);
        doNothing().when(clienteRepositoryMock).deleteById(any(UUID.class));

        service.delete(UUID.randomUUID());

        verify(clienteRepositoryMock, times(0)).deleteById(UUID.randomUUID());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        lenient().when(clienteRepositoryMock.findById(any(UUID.class))).thenReturn(optionalCliente);
        when(clienteRepositoryMock.save(any())).thenReturn(cliente);

        Cliente response = service.create(clienteRequestDTO);

        assertEquals(Cliente.class, response.getClass());
    }

    @Test
    void findByClienteinfoCpfContaining() {
    }
}
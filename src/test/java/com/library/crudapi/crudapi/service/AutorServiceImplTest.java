package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class AutorServiceImplTest {

    @Mock
    private AutorRepository autorRepositoryMock;

    private Autor autor;
    private AutorRequestDTO autorRequestDTO;

    private Optional<Autor> optionalAutor;
    private AutorServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new AutorServiceImpl(autorRepositoryMock);
        autor = new Autor();
        optionalAutor = Optional.of(autor);
        autorRequestDTO = new AutorRequestDTO();
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(autorRepositoryMock.findById(any(UUID.class))).thenReturn(optionalAutor);
        when(autorRepositoryMock.save(any())).thenReturn(autor);

        service.update(UUID.randomUUID(), autorRequestDTO);

        verify(autorRepositoryMock, times(1)).save(any(Autor.class));
        verify(autorRepositoryMock, times(0)).findById(UUID.randomUUID());
    }

    @Test
    void findByPage() {
    }

    @Test
    void whenFindByIdThenReturnAutor() {
        when(autorRepositoryMock.findById(any(UUID.class))).thenReturn(optionalAutor);
        Autor response = service.findById(UUID.randomUUID());

        assertNotNull(response);
        assertEquals(Autor.class, response.getClass());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        lenient().when(autorRepositoryMock.findById(any(UUID.class))).thenReturn(optionalAutor);
        when(autorRepositoryMock.save(any())).thenReturn(autor);

        Autor response = service.create(autorRequestDTO);

        assertEquals(Autor.class,response.getClass());
    }

    @Test
    void whenDeleteAutorByIdReturnNoContent() {
        lenient().when(autorRepositoryMock.findById(any(UUID.class))).thenReturn(optionalAutor);
        doNothing().when(autorRepositoryMock).deleteById(any(UUID.class));

        service.delete(UUID.randomUUID());

        verify(autorRepositoryMock, times(0)).deleteById(UUID.randomUUID());
    }
}
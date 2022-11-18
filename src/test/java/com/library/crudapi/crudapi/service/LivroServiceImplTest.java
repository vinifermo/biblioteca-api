package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class LivroServiceImplTest {

    @Mock
    private LivroRepository livroRepositoryMock;

    private Livro livro;
    private LivroRequestDTO livroRequestDTO;
    private Optional<Livro> optionalLivro;
    private LivroServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new LivroServiceImpl(livroRepositoryMock);
        livro = new Livro(UUID.randomUUID(),"Olavo no pais das maravilhas","Terror",new Autor(),new Editora());
        optionalLivro = Optional.of(livro);
        livroRequestDTO = new LivroRequestDTO();
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(livroRepositoryMock.findById(any(UUID.class))).thenReturn(optionalLivro);
        when(livroRepositoryMock.save(any())).thenReturn(livro);

        service.update(UUID.randomUUID(), livroRequestDTO);

        verify(livroRepositoryMock, times(1)).save(any(Livro.class));
        verify(livroRepositoryMock, times(0)).findById(UUID.randomUUID());
    }

    @Test
    void WhenFindByIdThenReturnLivro() {
        when(livroRepositoryMock.findById(any(UUID.class))).thenReturn(optionalLivro);
        Livro response = service.findById(UUID.randomUUID());

        assertNotNull(response);
        assertEquals(Livro.class, response.getClass());
        assertEquals(livro.getNome(),response.getNome());
        assertEquals(livro.getId(),response.getId());
        assertEquals(livro.getAutor(), response.getAutor());
        assertEquals(livro.getEditora(), response.getEditora());
        assertEquals(livro.getGenero(),response.getGenero());
    }

    @Test
    void WhenFindAllThenReturnListOfEditora() {
        when(livroRepositoryMock.findAll()).thenReturn(List.of());
        List<Livro> response = service.findAll();

        assertNotNull(response);
        assertNotNull(response.getClass());

        assertEquals(0, response.size());
    }

    @Test
    void findByEditoraId() {
        when(livroRepositoryMock.findByEditoraId(any(UUID.class))).thenReturn(anyList());
        List<Livro> response = service.findByEditoraId(UUID.randomUUID());

        assertNotNull(response);
    }

    @Test
    void whenDeleteLivroByIdReturnNoContent() {
        lenient().when(livroRepositoryMock.findById(any(UUID.class))).thenReturn(optionalLivro);
        doNothing().when(livroRepositoryMock).deleteById(any(UUID.class));

        service.delete(UUID.randomUUID());

        verify(livroRepositoryMock, times(0)).deleteById(UUID.randomUUID());
    }

    @Test
    void whenCreateAnLivroThenReturnSuccess() {
        lenient().when(livroRepositoryMock.findById(any(UUID.class))).thenReturn(optionalLivro);
        when(livroRepositoryMock.save(any())).thenReturn(livro);

        Livro response = service.create(livroRequestDTO);

        assertEquals(Livro.class, response.getClass());
    }
}
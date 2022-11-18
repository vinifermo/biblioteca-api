package com.library.crudapi.crudapi.service;

import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.entity.Endereco;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.EditoraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class EditoraServiceImplTest {

    @Mock
    private EditoraRepository editoraRepositoryMock;

    private Editora editora;
    private EditoraRequestDTO editoraRequestDTO;

    private Optional<Editora> optionalEditora;
    private EditoraServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new EditoraServiceImpl(editoraRepositoryMock);
        editora = new Editora();
        optionalEditora = Optional.of(editora);
        editoraRequestDTO = new EditoraRequestDTO();
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(editoraRepositoryMock.findById(any(UUID.class))).thenReturn(optionalEditora);
        when(editoraRepositoryMock.save(any())).thenReturn(editora);

        service.update(UUID.randomUUID(),editoraRequestDTO);

        verify(editoraRepositoryMock, times(1)).save(any(Editora.class));
        verify(editoraRepositoryMock, times(0)).findById(UUID.randomUUID());
    }

    @Test
    void WhenFindByIdThenReturnEditora() {
        when(editoraRepositoryMock.findById(any(UUID.class))).thenReturn(optionalEditora);
        Editora response = service.findById(UUID.randomUUID());

        assertNotNull(response);
        assertEquals(Editora.class, response.getClass());
    }

    @Test
    void WhenFindAllThenReturnListOfEditora() {
        when(editoraRepositoryMock.findAll()).thenReturn(List.of(editora));
        List<Editora> response = service.findAll();

        assertNotNull(response);
    }

    @Test
    void whenDeleteEditoraByIdReturnNoContent() {
        lenient().when(editoraRepositoryMock.findById(any(UUID.class))).thenReturn(optionalEditora);
        doNothing().when(editoraRepositoryMock).deleteById(any(UUID.class));

        service.delete(UUID.randomUUID());

        verify(editoraRepositoryMock, times(0)).deleteById(UUID.randomUUID());
    }

    @Test
    void whenCreateThenReturnSuccess(){
        lenient().when(editoraRepositoryMock.findById(any(UUID.class))).thenReturn(optionalEditora);
        when(editoraRepositoryMock.save(any())).thenReturn(editora);

        Editora response = service.create(editoraRequestDTO);

        assertEquals(Editora.class, response.getClass());
    }
}
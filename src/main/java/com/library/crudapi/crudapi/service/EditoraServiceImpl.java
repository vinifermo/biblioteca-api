package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.EditoraRequestDTO;
import com.library.crudapi.crudapi.entity.Editora;
import com.library.crudapi.crudapi.repository.EditoraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EditoraServiceImpl implements EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Editora atualizar(UUID id, EditoraRequestDTO editoraRequestDTO) {
        Editora editoraSalva = buscarEditoraPeloId(id);
        BeanUtils.copyProperties(editoraRequestDTO, editoraSalva, "id");
        Editora save = editoraRepository.save(editoraSalva);
        return save;

    }

    private Editora buscarEditoraPeloId(UUID id) {
        Editora editoraSalva = editoraRepository.findById(id).orElse(null);
        if (editoraSalva == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return editoraSalva;
    }

    public Optional<Editora> buscarPeloId(@PathVariable UUID id) {
        Optional<Editora> editora = editoraRepository.findById(id);
        return editora;

    }

    public List<Editora> listar() {
        return editoraRepository.findAll();
    }

    public Editora remover(@PathVariable UUID id) {
        editoraRepository.deleteById(id);
        return null;
    }

    public Editora criar(EditoraRequestDTO editoraRequestDTO) {
        Editora editora = new Editora(editoraRequestDTO);
        Editora editoraSalva = editoraRepository.save(editora);
        return editoraSalva;
    }
}
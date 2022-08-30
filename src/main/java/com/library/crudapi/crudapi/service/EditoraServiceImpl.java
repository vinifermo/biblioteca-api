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

@Service
@Transactional
public class EditoraServiceImpl implements EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Editora atualizar(Long codigo, EditoraRequestDTO editoraRequestDTO) {
        Editora editoraSalva = buscarEditoraPeloCodigo(codigo);
        BeanUtils.copyProperties(editoraRequestDTO, editoraSalva, "codigo");
        Editora save = editoraRepository.save(editoraSalva);
        return save;

    }

    private Editora buscarEditoraPeloCodigo(Long codigo) {
        Editora editoraSalva = editoraRepository.findById(codigo).orElse(null);
        if (editoraSalva == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return editoraSalva;
    }

    public Optional<Editora> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Editora> editora = editoraRepository.findById(codigo);
        return editora;

    }

    public List<Editora> listar() {
        return editoraRepository.findAll();
    }

    public Editora remover(@PathVariable Long codigo) {
        editoraRepository.deleteById(codigo);
        return null;
    }

    public Editora criar(EditoraRequestDTO editoraRequestDTO) {
        Editora editora = new Editora(editoraRequestDTO);
        Editora editoraSalva = editoraRepository.save(editora);
        return editoraSalva;
    }
}
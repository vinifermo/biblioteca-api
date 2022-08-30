package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.AutorRequestDTO;
import com.library.crudapi.crudapi.entity.Autor;
import com.library.crudapi.crudapi.repository.AutorRepository;
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
public class AutorServiceImpl implements AutorService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Autor atualizar(Long codigo, AutorRequestDTO autorRequestDTO) {
        Autor autorSalvo = buscarAutorPeloCodigo(codigo);
        BeanUtils.copyProperties(autorRequestDTO, autorSalvo, "codigo");
        Autor save = autorRepository.save(autorSalvo);
        return save;

    }
    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    public Autor buscarAutorPeloCodigo(Long codigo) {
        Autor autorSalvo = autorRepository.findById(codigo).orElse(null);
        if (autorSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return autorSalvo;
    }
    public Optional<Autor> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Autor> autor = autorRepository.findById(codigo);
        return autor;

    }
    @Override
    public Autor criar(AutorRequestDTO autorRequestDTO) {
        Autor autor = new Autor(autorRequestDTO);
        Autor autorSalvo = autorRepository.save(autor);
        return autorSalvo;
    }

    public Autor remover(@PathVariable Long codigo) {
        autorRepository.deleteById(codigo);
        return null;
    }
}
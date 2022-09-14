package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.LivroRequestDTO;
import com.library.crudapi.crudapi.entity.Livro;
import com.library.crudapi.crudapi.repository.LivroRepository;
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
public class LivroServiceImpl implements LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Livro atualizar(UUID id, LivroRequestDTO livroRequestDTO) {
        Livro livroSalvo = buscarlivroPeloId(id);
        BeanUtils.copyProperties(livroRequestDTO, livroSalvo, "id");
        Livro save = livroRepository.save(livroSalvo);
        return save;

    }

    private Livro buscarlivroPeloId(UUID id) {
        Livro livroSalvo = livroRepository.findById(id).orElse(null);
        if (livroSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return livroSalvo;
    }

    public List<Livro> listar() {
        return livroRepository.findAll();
    }


    public Livro remover(UUID id) {
        livroRepository.deleteById(id);
        return null;
    }

    public Optional<Livro> buscarPeloId(@PathVariable UUID id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro;

    }
    @Override
    public Livro criar(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroSalvo;
    }
}
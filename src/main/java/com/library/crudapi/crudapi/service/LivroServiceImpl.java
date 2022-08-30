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

@Service
@Transactional
public class LivroServiceImpl implements LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Livro atualizar(Long codigo, LivroRequestDTO livroRequestDTO) {
        Livro livroSalvo = buscarlivroPeloCodigo(codigo);
        BeanUtils.copyProperties(livroRequestDTO, livroSalvo, "codigo");
        Livro save = livroRepository.save(livroSalvo);
        return save;

    }

    private Livro buscarlivroPeloCodigo(Long codigo) {
        Livro livroSalvo = livroRepository.findById(codigo).orElse(null);
        if (livroSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return livroSalvo;
    }

    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    @Override
    public Livro remover(Long codigo) {
        livroRepository.deleteById(codigo);
        return null;
    }

    public Optional<Livro> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Livro> livro = livroRepository.findById(codigo);
        return livro;

    }

    public Livro criar(LivroRequestDTO livroRequestDTO) {
        Livro livro = new Livro(livroRequestDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroSalvo;
    }
}
package com.library.crudapi.crudapi.service;
import com.library.crudapi.crudapi.dto.request.ClienteRequestDTO;
import com.library.crudapi.crudapi.entity.Cliente;
import com.library.crudapi.crudapi.repository.ClienteRepository;
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
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ApplicationEventPublisher publisher;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente atualizar(Long codigo, ClienteRequestDTO clienteDTO) {
        Cliente clienteSalvo = buscarClientePeloCodigo(codigo);
        BeanUtils.copyProperties(clienteDTO, clienteSalvo, "codigo");
        Cliente save = clienteRepository.save(clienteSalvo);
        return save;
    }
    private Cliente buscarClientePeloCodigo(Long codigo) {
        Cliente clienteSalvo = clienteRepository.findById(codigo).orElse(null);
        if (clienteSalvo == null) {
            throw new EmptyResultDataAccessException(1);

        }
        return clienteSalvo;
    }

    public Cliente remover(@PathVariable Long codigo) {
        clienteRepository.deleteById(codigo);
        return null;
    }
    public Optional<Cliente> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Cliente> cliente = clienteRepository.findById(codigo);
        return cliente;
    }
    @Override
    public Cliente criar(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente(clienteRequestDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }
}
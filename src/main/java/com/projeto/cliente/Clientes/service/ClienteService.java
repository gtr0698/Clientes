package com.projeto.cliente.Clientes.service;

import com.projeto.cliente.Clientes.dto.CreateClienteRequestDto;
import com.projeto.cliente.Clientes.dto.UpdateClienteRequestDto;
import com.projeto.cliente.Clientes.exception.RegraException;
import com.projeto.cliente.Clientes.model.Cliente;
import com.projeto.cliente.Clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente salvar(CreateClienteRequestDto cliente){

        Cliente clienteNovo = cliente.convertToModel();

        return clienteRepository.save(clienteNovo);

    }

    public Page<Cliente> listar(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscar(Long clienteId){
        Cliente cliente = verificaExistencia(clienteId);

        return cliente;
    }

    public Cliente buscarPorNome(String clienteNome){
        Cliente cliente = verificaExistenciaNome(clienteNome);

        return cliente;
    }

    public Cliente buscarPorDtNascimento(LocalDate dtNascimento){
        Cliente cliente = verificaExistenciaDataN(dtNascimento);

        return cliente;
    }

    public Cliente buscarPorDtCriacao(LocalDate dtCriacao){
        Cliente cliente = verificaExistenciaDataC(dtCriacao);

        return cliente;
    }

    public Cliente atualizar(Long clienteId, UpdateClienteRequestDto clienteRequest){
        Cliente cliente = verificaExistencia(clienteId);
        Cliente clienteAtualizado = cliente.atualizaCliente(clienteRequest.getNome(),
                clienteRequest.getEmail(),clienteRequest.getTelefone(),clienteRequest.getDocumento(),
                clienteRequest.getDtNascimento(),clienteRequest.getDtCriacao(),clienteRequest.getDtUpdate());

        return clienteRepository.save(clienteAtualizado);
    }

    public void excluir(Long clienteId){

        verificaExistencia(clienteId);

        clienteRepository.deleteById(clienteId);
    }

    public Cliente verificaExistencia(Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isEmpty()){

            throw new RegraException("Cliente não encontrado");
        }

        return cliente.get();
    }

    public Cliente verificaExistenciaNome(String clienteNome){
        Optional<Cliente> cliente = clienteRepository.findByNome(clienteNome);

        if(cliente.isEmpty()){

            throw new RegraException("Cliente não encontrado");
        }

        return cliente.get();
    }

    public Cliente verificaExistenciaDataN(LocalDate clienteDataN){
        Optional<Cliente> cliente = clienteRepository.findByDataNascimento(clienteDataN);

        if(cliente.isEmpty()){

            throw new RegraException("Cliente não encontrado");
        }

        return cliente.get();
    }

    public Cliente verificaExistenciaDataC(LocalDate clienteDataC){
        Optional<Cliente> cliente = clienteRepository.findByDataCriacao(clienteDataC);

        if(cliente.isEmpty()){

            throw new RegraException("Cliente não encontrado");
        }

        return cliente.get();
    }
}

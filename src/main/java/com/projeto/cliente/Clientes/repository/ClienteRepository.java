package com.projeto.cliente.Clientes.repository;

import com.projeto.cliente.Clientes.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    Optional<Cliente> findByNome(String clienteNome);
    Optional<Cliente> findByDataNascimento(LocalDate clienteDataN);
    Optional<Cliente> findByDataCriacao(LocalDate clienteDataC);
}

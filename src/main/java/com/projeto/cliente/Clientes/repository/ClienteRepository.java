package com.projeto.cliente.Clientes.repository;

import com.projeto.cliente.Clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    List<Cliente> findByNomeContaining(String cliNome);
}

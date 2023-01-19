package com.projeto.cliente.Clientes.dto;

import com.projeto.cliente.Clientes.model.Cliente;

import java.time.LocalDate;

public class ClienteResponseDto {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String documento;
    private LocalDate dtNascimento;
    private LocalDate dtCriacao;
    private LocalDate dtUpdate;

    public ClienteResponseDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.documento = cliente.getDocumento();
        this.dtNascimento = cliente.getDtNascimento();
        this.dtCriacao = cliente.getDtCriacao();
        this.dtUpdate = cliente.getDtUpdate();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDocumento() {
        return documento;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public LocalDate getDtUpdate() {
        return dtUpdate;
    }
}

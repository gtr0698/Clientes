package com.projeto.cliente.Clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UpdateClienteRequestDto {

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 50)
    private String nome;

    @Email
    @NotBlank
    @NotEmpty
    private String email;

    @NotBlank
    @NotEmpty
    private String telefone;

    @NotBlank
    @NotEmpty
    private String documento;

    private LocalDate dtNascimento;

    private LocalDate dtCriacao;

    private LocalDate dtUpdate;

    public UpdateClienteRequestDto(String nome, String email, String telefone, String documento, LocalDate dtNascimento, LocalDate dtCriacao, LocalDate dtupdate) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.documento = documento;
        this.dtNascimento = dtNascimento;
        this.dtCriacao = dtCriacao;
        this.dtUpdate = dtupdate;
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

    public UpdateClienteRequestDto() {
        super();
    }
}

package com.projeto.cliente.Clientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    //Formato "yyyy/MM/dd" no post informar como "yyyy-MM-dd"
    private LocalDate dtNascimento;

    private LocalDate dtCriacao;

    private LocalDate dtUpdate;

    public Cliente(String nome, String email, String telefone, String documento, LocalDate dtNascimento, LocalDate dtCriacao, LocalDate dtUpdate) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.documento = documento;
        this.dtNascimento = dtNascimento;
        this.dtCriacao = dtCriacao;
        this.dtUpdate = dtUpdate;
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

    public Cliente atualizaCliente(String nome, String email, String telefone, String documento, LocalDate dtNascimento,
                                   LocalDate dtCriacao, LocalDate dtUpdate){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.documento = documento;
        this.dtNascimento = dtNascimento;
        this.dtCriacao = dtCriacao;
        this.dtUpdate = dtUpdate;

        return this;
    }
    public Cliente() {
        super();
    }
}

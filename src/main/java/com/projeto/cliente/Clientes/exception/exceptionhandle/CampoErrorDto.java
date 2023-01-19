package com.projeto.cliente.Clientes.exception.exceptionhandle;

public class CampoErrorDto {

    private String nome;
    private String mensagem;

    public CampoErrorDto(String nome, String mensagem) {
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public String getMensagem() {
        return mensagem;
    }
}

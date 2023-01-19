package com.projeto.cliente.Clientes.exception;

public class RegraException extends RuntimeException{

    private static final long serialVersion = 1L;
    private String campo;

    public RegraException(String message){
        super(message);
    }

    public RegraException(String message, String campo) {
        super(message);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}

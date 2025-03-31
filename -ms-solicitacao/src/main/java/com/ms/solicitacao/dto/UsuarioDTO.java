package com.ms.solicitacao.dto;

public class UsuarioDTO {
    private Long usuario_cod;
    private String usuario_nome;
    private String usuario_cpf;
    private String usuario_email;
    private String usuario_cargo;

    // Getters and Setters
    public Long getUsuario_cod() {
        return usuario_cod;
    }

    public void setUsuario_cod(Long usuario_cod) {
        this.usuario_cod = usuario_cod;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public String getUsuario_cpf() {
        return usuario_cpf;
    }

    public void setUsuario_cpf(String usuario_cpf) {
        this.usuario_cpf = usuario_cpf;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String usuario_email) {
        this.usuario_email = usuario_email;
    }

    public String getUsuario_cargo() {
        return usuario_cargo;
    }

    public void setUsuario_cargo(String usuario_cargo) {
        this.usuario_cargo = usuario_cargo;
    }
}

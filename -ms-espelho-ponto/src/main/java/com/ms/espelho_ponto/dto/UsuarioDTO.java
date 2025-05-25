package com.ms.espelho_ponto.dto;

public class UsuarioDTO {
    private Long usuario_cod;
    private String usuario_nome;
    private String usuario_cpf;
    private String usuario_nrRegistro;
    private String usuario_email;
    private String usuario_cargo;
    private int nivelAcesso_cod;
    private int empCod;
    private SetorDTO setor;

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

    public SetorDTO getSetor() {
        return setor;
    }

    public void setSetor(SetorDTO setor) {
        this.setor = setor;
    }

    public String getUsuario_nrRegistro() {
        return usuario_nrRegistro;
    }

    public void setUsuario_nrRegistro(String usuario_nrRegistro) {
        this.usuario_nrRegistro = usuario_nrRegistro;
    }

	public int getNivelAcesso_cod() {
		return nivelAcesso_cod;
	}

	public void setNivelAcesso_cod(int nivelAcesso_cod) {
		this.nivelAcesso_cod = nivelAcesso_cod;
	}

    public int getEmpCod() {
		return empCod;
	}

	public void setEmpCod(int empCod) {
		this.empCod = empCod;
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

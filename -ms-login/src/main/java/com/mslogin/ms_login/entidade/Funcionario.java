package com.mslogin.ms_login.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long func_id;

    @Column(name = "func_email")
    private String funcEmail;

    @Column(name = "func_senha")
    private String funcSenha;

	//
	//
	//
	@Column(name = "func_acesso")
	private long funcAcesso;

    //Getters and setters

	public long getFunc_id() {
		return func_id;
	}

	public void setFunc_id(long func_id) {
		this.func_id = func_id;
	}

	public String getFuncEmail() {
		return funcEmail;
	}

	public void setFuncEmail(String func_email) {
		this.funcEmail = func_email;
	}

	public String getFuncSenha() {
		return funcSenha;
	}

	public void setFuncSenha(String func_senha) {
		this.funcSenha = func_senha;
	}


	//
	//
	//
	public long getFuncAcesso() {
		return funcAcesso;
	}

	public void setFunc_acesso(long func_acesso) {
		this.funcAcesso = func_acesso;
	}
}

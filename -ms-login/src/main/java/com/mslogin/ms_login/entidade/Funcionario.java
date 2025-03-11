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

    @Column
    private String func_email;

    @Column
    private String func_senha;

    //Getters and setters

	public long getFunc_id() {
		return func_id;
	}

	public void setFunc_id(long func_id) {
		this.func_id = func_id;
	}

	public String getFunc_email() {
		return func_email;
	}

	public void setFunc_email(String func_email) {
		this.func_email = func_email;
	}

	public String getFunc_senha() {
		return func_senha;
	}

	public void setFunc_senha(String func_senha) {
		this.func_senha = func_senha;
	}

}

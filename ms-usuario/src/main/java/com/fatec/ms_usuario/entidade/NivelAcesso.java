package com.fatec.ms_usuario.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class NivelAcesso {
    @Id
	@Column(name = "nivelacesso_cod")
	private int nivelAcesso_cod;
	
	@Column(name = "nivelacesso_nome", length=50)
	private String nivelAcesso_nome;   

	public long getNivelAcesso_cod() {
		return nivelAcesso_cod;
	}
	/*
	public void setNivelAcesso_cod(int nivelAcesso_cod){
		this.nivelAcesso_cod = nivelAcesso_cod;
	}
 	*/
	public String getNivelAcesso_nome() {
		return nivelAcesso_nome;
	}

	public void setNivelAcesso_nome(String nivelAcesso_nome) {
		this.nivelAcesso_nome = nivelAcesso_nome;
	}
		
}

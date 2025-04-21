package com.fatec.ms_usuario.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FolgaTipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long folTipoCod;
	
	@Column
	private String folNome;

	public long getFolTipoCod() {
		return folTipoCod;
	}

	public void setFolTipoCod(long folTipoCod) {
		this.folTipoCod = folTipoCod;
	}

	public String getFolNome() {
		return folNome;
	}

	public void setFolNome(String folNome) {
		this.folNome = folNome;
	}

	
	
}

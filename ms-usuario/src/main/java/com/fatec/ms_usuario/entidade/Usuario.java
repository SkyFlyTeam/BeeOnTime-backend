package com.fatec.ms_usuario.entidade;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usuario_cod;

	@Column
	private String usuario_nome;

	@Column
	private String usuario_cpf;

	@Column
	private Double usuario_nRegistro;

	@Column
	private Integer usuario_cargaHoraria;

	@Column
	private String usuario_contratacao;

	@Column
	private Date usuario_dataContratacao;

	@Column
	private String usuario_senha;

	@Column
	private String usuario_email;

	@Column
	private String usuario_dataNascimento;

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

	public Double getUsuario_nRegistro() {
		return usuario_nRegistro;
	}

	public void setUsuario_nRegistro(Double usuario_nRegistro) {
		this.usuario_nRegistro = usuario_nRegistro;
	}

	public Integer getUsuario_cargaHoraria() {
		return usuario_cargaHoraria;
	}

	public void setUsuario_cargaHoraria(Integer usuario_cargaHoraria) {
		this.usuario_cargaHoraria = usuario_cargaHoraria;
	}

	public String getUsuario_contratacao() {
		return usuario_contratacao;
	}

	public void setUsuario_contratacao(String usuario_contratacao) {
		this.usuario_contratacao = usuario_contratacao;
	}

	public Date getUsuario_dataContratacao() {
		return usuario_dataContratacao;
	}

	public void setUsuario_dataContratacao(Date usuario_dataContratacao) {
		this.usuario_dataContratacao = usuario_dataContratacao;
	}

	public String getUsuario_senha() {
		return usuario_senha;
	}

	public void setUsuario_senha(String usuario_senha) {
		this.usuario_senha = usuario_senha;
	}

	public String getUsuario_email() {
		return usuario_email;
	}

	public void setUsuario_email(String usuario_email) {
		this.usuario_email = usuario_email;
	}

	public String getUsuario_dataNascimento() {
		return usuario_dataNascimento;
	}

	public void setUsuario_dataNascimento(String usuario_dataNascimento) {
		this.usuario_dataNascimento = usuario_dataNascimento;
	}

}

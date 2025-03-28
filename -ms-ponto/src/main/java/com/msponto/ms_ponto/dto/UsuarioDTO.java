package com.msponto.ms_ponto.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioDTO {

    private Long usuario_cod;

	private String usuario_nome;

	private String usuario_cpf;

	private Double usuario_nrRegistro;

	private Integer usuario_cargaHoraria;

	private String usuarioTipoContratacao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate usuario_dataContratacao;

	private String usuario_senha;

	private String usuario_email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate usuario_DataNascimento;

	private String usuario_cargo;
    
	private JornadaDTO jornada;

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
		return usuario_nrRegistro;
	}

	public void setUsuario_nRegistro(Double usuario_nRegistro) {
		this.usuario_nrRegistro = usuario_nRegistro;
	}
	
	public Integer getUsuario_cargaHoraria() {
		return usuario_cargaHoraria;
	}

	public void setUsuario_cargaHoraria(Integer usuario_cargaHoraria) {
		this.usuario_cargaHoraria = usuario_cargaHoraria;
	}
	
	
	public String getUsuarioTipoContratacao() {
		return usuarioTipoContratacao;
	}
	
	public void setUsuarioTipoContratacao(String usuarioTipoContratacao) {
		this.usuarioTipoContratacao = usuarioTipoContratacao;
	}
	
	public LocalDate getUsuario_dataContratacao() {
		return usuario_dataContratacao;
	}

	public void setUsuario_dataContratacao(LocalDate usuario_dataContratacao) {
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

	
	public LocalDate getUsuario_DataNascimento() {
		return usuario_DataNascimento;
	}
	
	public void setUsuario_DataNascimento(LocalDate usuario_DataNascimento) {
		this.usuario_DataNascimento = usuario_DataNascimento;
	}

	public String getUsuario_cargo() {
		return usuario_cargo;
	}
	
	public void setUsuario_cargo(String usuario_cargo) {
		this.usuario_cargo = usuario_cargo;
	}

	public JornadaDTO getJornada() {
		return jornada;
	}
	
	public void setJornada(JornadaDTO jornada) {
		this.jornada = jornada;
	}
}

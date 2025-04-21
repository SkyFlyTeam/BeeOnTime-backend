package com.fatec.ms_usuario.entidade;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import com.fatec.ms_usuario.entidade.Empresa;

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
	private Double usuario_nrRegistro;
	
	@Column
	private Integer usuario_cargaHoraria;
	
	@Column
	private String usuarioTipoContratacao;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate usuario_dataContratacao;
	
	@Column
	private String usuario_senha;
	
	@Column
	private String usuarioEmail;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate usuario_DataNascimento;
	
	@Column
	private String usuario_cargo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empCod", referencedColumnName = "empCod", insertable = false, updatable = false)
	@JsonIgnore
	private Empresa empresa;

	private Long empCod; // Foreign key field for Empresa	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "setorCod", referencedColumnName = "setorCod", insertable = false, updatable = false)
	//@JsonIgnore
	private Setor setor;

	private Long setorCod;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_cod", referencedColumnName = "usuario_cod", insertable = false, updatable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Jornada jornadas;

	@ManyToOne
	@JoinColumn(name = "nivelacesso_cod", referencedColumnName = "nivelacesso_cod", insertable = false, updatable = false)
	private NivelAcesso nivelAcesso;
	@Column(name = "nivelacesso_cod")
	private Long nivelAcesso_cod;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Folga> folgas;
	@Column(name = "folCod")
	private Long folCod;
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getEmpCod() {
		return empCod;
	}

	public void setEmpCod(Long empCod) {
		this.empCod = empCod;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Long getSetorCod() {
		return setorCod;
	}

	public void setSetorCod(Long setorCod) {
		this.setorCod = setorCod;
	}

	public Jornada getJornadas() {
		return jornadas;
	}

	public void setJornadas(Jornada jornadas) {
		this.jornadas = jornadas;
	}

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
	

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
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

	public NivelAcesso getNivelAcesso(){
		return nivelAcesso;
	}
	public void setNivelAcesso(NivelAcesso nivelAcesso){
		this.nivelAcesso = nivelAcesso;
	}

	public Long getNivelAcesso_cod() {
		return nivelAcesso_cod;
	}
	public void setNivelAcesso_cod(Long nivelAcesso_cod){
		this.nivelAcesso_cod = nivelAcesso_cod;
	}

	public List<Folga> getFolgas() {
		return folgas;
	}

	public void setFolgas(List<Folga> folgas) {
		this.folgas = folgas;
	}

	public Long getFolCod() {
		return folCod;
	}

	public void setFolCod(Long folCod) {
		this.folCod = folCod;
	}

}
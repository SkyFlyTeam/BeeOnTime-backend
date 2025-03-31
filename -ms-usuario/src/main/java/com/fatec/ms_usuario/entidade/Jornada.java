package com.fatec.ms_usuario.entidade;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
public class Jornada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jornada_cod;
	
	@Column
	private Boolean jornada_horarioFlexivel;
	
 	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "jornada_dias_semana", joinColumns = @JoinColumn(name = "jornada_cod"))
    @Column(name = "jornada_diasSemana")
	private List<Boolean> jornada_diasSemana;
	
    @Column
    private LocalTime jornada_horarioEntrada;  // Alterando para LocalTime
    
    @Column
    private LocalTime jornada_horarioSaida;    // Alterando para LocalTime
    
    @Column
    private LocalTime jornada_horarioAlmoco;   // Alterando para LocalTime
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_cod", referencedColumnName = "usuario_cod", insertable = false, updatable = false)
	@JsonIgnore
	private Usuario usuario;


    // @Column(name = "usuario_cod")
    private Long usuario_cod;


	public Long getJornada_cod() {
		return jornada_cod;
	}


	public void setJornada_cod(Long jornada_cod) {
		this.jornada_cod = jornada_cod;
	}


	public Boolean getJornada_horarioFlexivel() {
		return jornada_horarioFlexivel;
	}


	public void setJornada_horarioFlexivel(Boolean jornada_horarioFlexivel) {
		this.jornada_horarioFlexivel = jornada_horarioFlexivel;
	}


	public List<Boolean> getJornada_diasSemana() {
		return jornada_diasSemana;
	}


	public void setJornada_diasSemana(List<Boolean> jornada_diasSemana) {
		this.jornada_diasSemana = jornada_diasSemana;
	}


	public LocalTime getJornada_horarioEntrada() {
		return jornada_horarioEntrada;
	}


	public void setJornada_horarioEntrada(LocalTime jornada_horarioEntrada) {
		this.jornada_horarioEntrada = jornada_horarioEntrada;
	}


	public LocalTime getJornada_horarioSaida() {
		return jornada_horarioSaida;
	}


	public void setJornada_horarioSaida(LocalTime jornada_horarioSaida) {
		this.jornada_horarioSaida = jornada_horarioSaida;
	}


	public LocalTime getJornada_horarioAlmoco() {
		return jornada_horarioAlmoco;
	}


	public void setJornada_horarioAlmoco(LocalTime jornada_horarioAlmoco) {
		this.jornada_horarioAlmoco = jornada_horarioAlmoco;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Long getUsuario_cod() {
		return usuario_cod;
	}


	public void setUsuario_cod(Long usuario_cod) {
		this.usuario_cod = usuario_cod;
	}
	


}
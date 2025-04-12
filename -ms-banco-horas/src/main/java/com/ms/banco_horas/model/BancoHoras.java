package com.ms.banco_horas.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "bancoHorasCod"
)
@Entity
public class BancoHoras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bancoHoras_cod")
	private long bancoHorasCod;
	
	@Column(name = "bancoHoras_saldoAtual", nullable=false)
	private Double bancoHorasSaldoAtual;
	
	@Column(name = "bancoHoras_data")
	private LocalDate bancoHorasData;

	@Column(name = "usuario_cod", nullable=false)
    private Long usuarioCod;
	
	@OneToMany(mappedBy = "bancoHorasCod", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<HistoricoCompensacao> historicoCompensacoes;
	
	@Transient
    private String usuarioNome;
	
	@Transient
    private String usuarioCargo;
    
    @Transient
    private int setorCod;
    
    @Transient
    private int nivelAcesso_cod;
    
	public long getBancoHorasCod() {
		return bancoHorasCod;
	}

	public void setBancoHorasCod(long bancoHorasCod) {
		this.bancoHorasCod = bancoHorasCod;
	}
	
	public Long getUsuarioCod() {
		return usuarioCod;
	}

	public void setUsuarioCod(Long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}
    
    public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public String getUsuarioCargo() {
		return usuarioCargo;
	}

	public void setUsuarioCargo(String usuarioCargo) {
		this.usuarioCargo = usuarioCargo;
	}

	public int getSetorCod() {
		return setorCod;
	}

	public void setSetorCod(int setorCod) {
		this.setorCod = setorCod;
	}

	public int getNivelAcesso_cod() {
		return nivelAcesso_cod;
	}

	public void setNivelAcesso_cod(int nivelAcesso_cod) {
		this.nivelAcesso_cod = nivelAcesso_cod;
	}

	public Double getBancoHorasSaldoAtual() {
		return bancoHorasSaldoAtual;
	}

	public void setBancoHorasSaldoAtual(Double bancoHorasSaldoAtual) {
		this.bancoHorasSaldoAtual = bancoHorasSaldoAtual;
	}

	public LocalDate getBancoHorasData() {
		return bancoHorasData;
	}

	public void setBancoHorasData(LocalDate bancoHorasData) {
		this.bancoHorasData = bancoHorasData;
	}

	public List<HistoricoCompensacao> getHistoricoCompensacoes() {
		return historicoCompensacoes;
	}

	public void setHistoricoCompensacoes(List<HistoricoCompensacao> historicoCompensacoes) {
		this.historicoCompensacoes = historicoCompensacoes;
	}
	
	
}

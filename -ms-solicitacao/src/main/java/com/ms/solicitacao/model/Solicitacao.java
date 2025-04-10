package com.ms.solicitacao.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "solicitacao")
@NoArgsConstructor
@AllArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitacao_cod")
    private long solicitacaoCod;

    @Column(name = "solicitacao_mensagem", length = 120, nullable = false)
    private String solicitacaoMensagem;

    @Lob
    @Column(name = "solicitacao_anexo", columnDefinition = "LONGBLOB")
    private byte[] solicitacaoAnexo;
    
    @Column(name = "solicitacao_anexo_nome")
    private String solicitacaoAnexoNome;


    @Column(name = "solicitacao_devolutiva", length = 120)
    private String solicitacaoDevolutiva;

    @Column(name = "solicitacao_dataPeriodo", nullable = false, updatable = false)
    private LocalDate solicitacaoDataPeriodo = LocalDate.now();
    
    @Column(name = "usuario_cod")
    private long usuarioCod;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "solicitacao_status")
    private SolicitacaoStatus solicitacaoStatus = SolicitacaoStatus.PENDENTE;
    
    @Transient
    private String usuarioNome;
    
    @Transient
    private String usuarioCargo;
    
    @Transient
    private int setorCod;
    
    @Transient
    private int nivelAcesso_cod;
    
    @ManyToOne
    @JoinColumn(name = "tipoSolicitacaoCod", nullable = false)
    private SolicitacaoTipo tipoSolicitacaoCod;

	public long getSolicitacaoCod() { 
		return solicitacaoCod;
	}

	public void setSolicitacaoCod(long solicitacaoCod) {
		this.solicitacaoCod = solicitacaoCod;
	}

	public String getSolicitacaoMensagem() {
		return solicitacaoMensagem;
	}

	public void setSolicitacaoMensagem(String solicitacaoMensagem) {
		this.solicitacaoMensagem = solicitacaoMensagem;
	}

	public byte[] getSolicitacaoAnexo() {
		return solicitacaoAnexo;
	}

	public void setSolicitacaoAnexo(byte[] solicitacaoAnexo) {
		this.solicitacaoAnexo = solicitacaoAnexo;
	}

	public String getSolicitacaoDevolutiva() {
		return solicitacaoDevolutiva;
	}

	public void setSolicitacaoDevolutiva(String solicitacaoDevolutiva) {
		this.solicitacaoDevolutiva = solicitacaoDevolutiva;
	}

	public LocalDate getSolicitacaoDataPeriodo() {
		return solicitacaoDataPeriodo;
	}

	public void setSolicitacaoDataPeriodo(LocalDate solicitacaoDataPeriodo) {
		this.solicitacaoDataPeriodo = solicitacaoDataPeriodo;
	}

	public SolicitacaoStatus getSolicitacaoStatus() {
		return solicitacaoStatus;
	}

	public void setSolicitacaoStatus(SolicitacaoStatus solicitacaoStatus) {
		this.solicitacaoStatus = solicitacaoStatus;
	}

	public SolicitacaoTipo getTipoSolicitacaoCod() {
		return tipoSolicitacaoCod;
	}

	public void setTipoSolicitacaoCod(SolicitacaoTipo tipoSolicitacaoCod) {
		this.tipoSolicitacaoCod = tipoSolicitacaoCod;
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

	public long getUsuarioCod() {
		return usuarioCod;
	}

	public void setUsuarioCod(long usuarioCod) {
		this.usuarioCod = usuarioCod;
	}

	public String getSolicitacaoAnexoNome() {
		return solicitacaoAnexoNome;
	}

	public void setSolicitacaoAnexoNome(String solicitacaoAnexoNome) {
		this.solicitacaoAnexoNome = solicitacaoAnexoNome;
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

}


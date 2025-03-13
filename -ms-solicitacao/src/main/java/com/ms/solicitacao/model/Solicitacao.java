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
    @Column(name = "solicitacao_anexo")
    private byte[] solicitacaoAnexo;

    @Column(name = "solicitacao_devolutiva", length = 120)
    private String solicitacaoDevolutiva;

    @Column(name = "solicitacao_dataPeriodo", nullable = false, updatable = false)
    private LocalDate solicitacaoDataPeriodo = LocalDate.now();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "solicitacao_status")
    private SolicitacaoStatus solicitacaoStatus = SolicitacaoStatus.PENDENTE;
    
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

}


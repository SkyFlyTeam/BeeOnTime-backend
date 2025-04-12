package com.ms.banco_horas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.banco_horas.model.HistoricoCompensacao;

public interface HistoricoCompensacaoRepository extends JpaRepository<HistoricoCompensacao, Long> {
	List<HistoricoCompensacao> findByBancoHorasCod_UsuarioCod(Long usuarioCod);
}

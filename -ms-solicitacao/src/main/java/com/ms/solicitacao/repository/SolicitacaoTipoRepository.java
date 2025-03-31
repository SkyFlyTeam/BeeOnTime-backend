package com.ms.solicitacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.solicitacao.model.SolicitacaoTipo;

@Repository
public interface SolicitacaoTipoRepository extends JpaRepository<SolicitacaoTipo, Long> {
	boolean existsByTipoSolicitacaoNome(String tipoSolicitacaoNome);
}

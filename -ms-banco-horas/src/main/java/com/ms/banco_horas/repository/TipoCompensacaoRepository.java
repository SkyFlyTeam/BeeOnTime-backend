package com.ms.banco_horas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.banco_horas.model.TipoCompensacao;

@Repository
public interface TipoCompensacaoRepository extends JpaRepository<TipoCompensacao, Long> {
	boolean existsByTipoCompensacaoName(String tipoCompensacaoNome);
}

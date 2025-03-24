package com.msponto.ms_ponto.repositorio.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;

public interface MarcacaoPontosRepositorio extends MongoRepository<MarcacaoPontos, String>{
    List<MarcacaoPontos> findByUsuarioCod(Long usuarioCod);
    Optional<MarcacaoPontos> findByHorasCod(Long horasCod);
}

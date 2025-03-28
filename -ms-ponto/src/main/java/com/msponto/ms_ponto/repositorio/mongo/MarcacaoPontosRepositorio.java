package com.msponto.ms_ponto.repositorio.mongo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;

public interface MarcacaoPontosRepositorio extends MongoRepository<MarcacaoPontos, String>{
    List<MarcacaoPontos> findByUsuarioCod(Long usuarioCod);
    Optional<MarcacaoPontos> findByHorasCod(Long horasCod);
    List<MarcacaoPontos> findByUsuarioCodAndDataBetween(Long usuarioCod, LocalDate startDate, LocalDate endDate);
    MarcacaoPontos findByUsuarioCodAndHorasCod(Long usuarioCod, Long horasCod);
}

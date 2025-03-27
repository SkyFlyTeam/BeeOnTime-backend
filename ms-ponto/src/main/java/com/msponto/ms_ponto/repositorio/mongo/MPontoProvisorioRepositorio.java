package com.msponto.ms_ponto.repositorio.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.msponto.ms_ponto.entidade.mongo.MPontoProvisorio;

public interface MPontoProvisorioRepositorio extends MongoRepository<MPontoProvisorio, String>{
    MPontoProvisorio findBySolicitacaoCod(Long solicitacaoCod);
}

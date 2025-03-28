package com.msponto.ms_ponto.repositorio.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msponto.ms_ponto.entidade.mysql.Atraso;

public interface AtrasoRepositorio extends JpaRepository<Atraso, Long>{
    
}

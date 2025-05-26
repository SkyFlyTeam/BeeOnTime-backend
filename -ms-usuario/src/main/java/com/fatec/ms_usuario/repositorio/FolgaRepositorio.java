package com.fatec.ms_usuario.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.Usuario;


@Repository
public interface FolgaRepositorio extends JpaRepository<Folga, Long> {
	List<Folga> findByUsuarioCodIn(List<Usuario> usuarios);
    @Query("SELECT f FROM Folga f WHERE f.usuarioCod.usuario_cod = :usuarioCod")
    List<Folga> findByUsuarioCodId(@Param("usuarioCod") Long usuarioCod);
}
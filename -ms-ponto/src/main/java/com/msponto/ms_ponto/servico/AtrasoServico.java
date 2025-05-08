package com.msponto.ms_ponto.servico;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.entidade.mysql.Atraso;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;
import com.msponto.ms_ponto.repositorio.mysql.AtrasoRepositorio;

@Service
public class AtrasoServico {
	
	@Autowired
	private AtrasoRepositorio repositorio;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	public Atraso save(Atraso atraso) {
		return repositorio.save(atraso);
	}
	
	public List<Atraso> findAll(){
		return repositorio.findAll();
	}
	
	public List<Atraso> findByUsuario(Long usuarioCod){
		List<Atraso> atrasos = repositorio.findAll();
		List<Atraso> usuarioAtrasos = atrasos.stream()
    			.filter(atraso -> atraso.getHoras().getUsuarioCod() == usuarioCod)
    			.collect(Collectors.toList());
		if (usuarioAtrasos != null) {
			return usuarioAtrasos;
		}
		return null;
	}
	
	public Atraso edit(Atraso atraso) {
		Atraso atrasoSelecionado = repositorio.findById(atraso.getAtrasoCod())
	            .orElseThrow(() -> new IllegalArgumentException("Atraso não encontrado"));
		
		if (atrasoSelecionado == null) {
			new RuntimeException("Atraso não encontrada!");
		}
		

		if (atraso.getAtrasoTempo() != null) {
			atrasoSelecionado.setAtrasoTempo(atraso.getAtrasoTempo());
		}

		if (atraso.getHoras() != null) {
			atrasoSelecionado.setHoras(atraso.getHoras());
		}
		
		Atraso saved = repositorio.save(atrasoSelecionado);
		return saved;
	}
	
	public void delete(Atraso atraso) {
		repositorio.deleteById(atraso.getAtrasoCod());;
	}
	
	public List<Atraso> findBySetor(Long setorCod) {
	    List<UsuarioDTO> usuarios = usuarioClient.getAllUsuarios();

	    List<UsuarioDTO> usuariosSetor = usuarios.stream()
	        .filter(usuario -> usuario.getSetor().getSetorCod().equals(setorCod))
	        .collect(Collectors.toList());

	    List<Atraso> atrasos = repositorio.findAll();
	    List<Atraso> atrasosSetor = atrasos.stream()
	        .filter(atraso -> {
	            if (atraso.getHoras() != null) {
	                Long usuarioCod = atraso.getHoras().getUsuarioCod(); 
	                return usuariosSetor.stream()
	                    .anyMatch(usuario -> usuario.getUsuario_cod().equals(usuarioCod)); 
	            }
	            return false;
	        })
	        .collect(Collectors.toList());
	    
	    return atrasosSetor; 
	}

	public List<Atraso> findByDate(LocalDate dataInicio, LocalDate dataFim) {
	    List<Atraso> atrasos = repositorio.findAll();

	    return atrasos.stream()
	            .filter(atraso -> {
	                LocalDate data = atraso.getHoras().getHorasData();
	                boolean afterInicio = (dataInicio == null) || (!data.isBefore(dataInicio)); 
	                boolean beforeFim = (dataFim == null) || (!data.isAfter(dataFim));         
	                return afterInicio && beforeFim;
	            })
	            .collect(Collectors.toList());
	}

	public List<Atraso> findByDateFrom(LocalDate dataInicio) {
	    return findByDate(dataInicio, null);   
	}

	public List<Atraso> findByDateUntil(LocalDate dataFim) {
	    return findByDate(null, dataFim);   
	}
}

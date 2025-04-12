package com.ms.banco_horas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.ms.banco_horas.dto.UsuarioDTO;
import com.ms.banco_horas.model.BancoHoras;
import com.ms.banco_horas.model.ExtrasPagas;
import com.ms.banco_horas.repository.ExtrasPagasRepositorty;

@Service
public class ExtrasPagasService {
	
	@Autowired
	public ExtrasPagasRepositorty repository;
	
	@Autowired
    private RestTemplate restTemplate;
	
	private static final String URL_SERVICO_USUARIO = "http://localhost:8081/usuario/";
	
	public List<ExtrasPagas> findAll(){
		List<ExtrasPagas> extrasPagas = repository.findAll();
		for(ExtrasPagas extra: extrasPagas) {
			adicionarUsuarioInformacao(extra);
		}
		return extrasPagas;
	}
	
	public List<ExtrasPagas> findAllByUsuario(long usuarioCod) {
		List<ExtrasPagas> extrasPagas = repository.findAll();
		for(ExtrasPagas extra: extrasPagas) {
			adicionarUsuarioInformacao(extra);
		}
		
		List<ExtrasPagas> extrasUsuario = extrasPagas.stream()
				.filter(extra -> extra.getUsuarioCod() == usuarioCod)
				.collect(Collectors.toList());
		if(extrasUsuario != null) {
			return extrasUsuario;
		}
		
		return null;
	}
	
	public ExtrasPagas findById(long id){
		ExtrasPagas extra = repository.findById(id).orElseThrow(() -> new RuntimeException("Extra paga não encontrada"));
		adicionarUsuarioInformacao(extra);
		return extra;
	}
	
	public ExtrasPagas save(ExtrasPagas extraPaga) {
		String usuarioUrl = URL_SERVICO_USUARIO + extraPaga.getUsuarioCod();
		try {
	        UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);
	        if (usuario != null) {
	            return repository.save(extraPaga);
	        } else {
	            System.err.println("Usuário com ID " + extraPaga.getUsuarioCod() + " não encontrado.");
	        }
	    } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	        System.err.println("Usuário com ID " + extraPaga.getUsuarioCod() + " não encontrado.");
	    } catch (Exception e) {
	        System.err.println("Erro ao buscar usuário com ID " + extraPaga.getUsuarioCod() + ": " + e.getMessage());
	    }
	    return null;
	}
	
	public void delete(ExtrasPagas extraPaga) {
		repository.deleteById(extraPaga.getExtrasPagasCod());
	}
	
	public ExtrasPagas edit(ExtrasPagas extraPaga) {
		ExtrasPagas selecionado = repository.findById(extraPaga.getExtrasPagasCod())
				.orElseThrow(() -> new RuntimeException("Extra paga não encontrada!"));
		
		if(extraPaga.getExtrasPagasSaldoAtual() != null) {
			selecionado.setExtrasPagasSaldoAtual(extraPaga.getExtrasPagasSaldoAtual());
		}
		
		if(extraPaga.getExtrasPagasData() != null) {
			selecionado.setExtrasPagasData(extraPaga.getExtrasPagasData());
		}
		
		if(extraPaga.getUsuarioCod() != null) {
			selecionado.setUsuarioCod(extraPaga.getUsuarioCod());
		}
		
		return repository.save(selecionado);
	}
	
	private void adicionarUsuarioInformacao(ExtrasPagas extrasPagas) {
	    if (extrasPagas.getUsuarioCod() != 0) {
	        String usuarioUrl = URL_SERVICO_USUARIO + extrasPagas.getUsuarioCod();
	        try {
	            UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);
	            if (usuario != null) {
	            	extrasPagas.setUsuarioNome(usuario.getUsuario_nome());
	            	extrasPagas.setUsuarioCargo(usuario.getUsuario_cargo());
	            	extrasPagas.setNivelAcesso_cod(usuario.getNivelAcesso_cod());
	            	extrasPagas.setSetorCod(usuario.getSetorCod());
	            }
	        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	            System.err.println("Usuário com ID " + extrasPagas.getUsuarioCod() + " não encontrado.");
	        } catch (Exception e) {
	            System.err.println("Erro ao buscar usuário com ID " + extrasPagas.getUsuarioCod() + ": " + e.getMessage());
	        }
	    }
	}
}

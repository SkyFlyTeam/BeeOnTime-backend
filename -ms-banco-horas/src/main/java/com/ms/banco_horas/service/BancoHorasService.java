package com.ms.banco_horas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.banco_horas.model.BancoHoras;
import com.ms.banco_horas.repository.BancoHorasRepository;
import com.ms.banco_horas.dto.UsuarioDTO;

@Service
public class BancoHorasService {
	
	@Autowired
	public BancoHorasRepository repository;
	
	@Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_USUARIO = "http://localhost:8081/usuario/"; 
	
	public List<BancoHoras> findAll(){
		List<BancoHoras> bancoHoras = repository.findAll();
		for (BancoHoras bancoHora : bancoHoras) {
			adicionarUsuarioInformacao(bancoHora);
		}
		return bancoHoras;
	}
	
	public List<BancoHoras> findAllByUsuario(long usuarioCod) {
		String usuarioUrl = URL_SERVICO_USUARIO + usuarioCod;
		try {
			UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);
			if (usuario != null) {
				List<BancoHoras> bancoHoras = repository.findAll();
				for (BancoHoras bancoHora : bancoHoras) {
					adicionarUsuarioInformacao(bancoHora);
				}
				List<BancoHoras> bancoHorasUsuario = bancoHoras.stream()
						.filter(bancoHora -> bancoHora.getUsuarioCod() == usuarioCod)
						.collect(Collectors.toList());
				if (bancoHorasUsuario != null) {
					return bancoHorasUsuario;
				} 
			}
		} catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	        System.err.println("Usuário com ID " + usuarioCod + " não encontrado.");
	    } catch (Exception e) {
	        System.err.println("Erro ao buscar usuário com ID " + usuarioCod + ": " + e.getMessage());
	    }
		return null;
	}
	
	public BancoHoras findById(long id) {
		BancoHoras bancoHoras = repository.findById(id).orElseThrow(() -> new RuntimeException("Banco de horas não encontrada"));
		adicionarUsuarioInformacao(bancoHoras);
		return bancoHoras;
	}
	
	public BancoHoras save(BancoHoras bancoHoras) {
	    String usuarioUrl = URL_SERVICO_USUARIO + bancoHoras.getUsuarioCod();
	    try {
	        UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);
	        if (usuario != null) {
	            return repository.save(bancoHoras);
	        } else {
	            System.err.println("Usuário com ID " + bancoHoras.getUsuarioCod() + " não encontrado.");
	        }
	    } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	        System.err.println("Usuário com ID " + bancoHoras.getUsuarioCod() + " não encontrado.");
	    } catch (Exception e) {
	        System.err.println("Erro ao buscar usuário com ID " + bancoHoras.getUsuarioCod() + ": " + e.getMessage());
	    }
	    return null; 
	}
	
	public BancoHoras edit(BancoHoras bancoHoras) {
		BancoHoras selecionado = repository.findById(bancoHoras.getBancoHorasCod())
				.orElseThrow(() -> new RuntimeException("Banco de horas não encontrada!"));
		
		if (bancoHoras.getBancoHorasSaldoAtual() != null) {
			selecionado.setBancoHorasSaldoAtual(bancoHoras.getBancoHorasSaldoAtual());
		}
		
		if (bancoHoras.getBancoHorasData() != null) {
			selecionado.setBancoHorasData(bancoHoras.getBancoHorasData());
		}
		
		if (bancoHoras.getUsuarioCod() != null) {
			selecionado.setUsuarioCod(bancoHoras.getUsuarioCod());
		}
		
		return repository.save(selecionado);
	}
	
	public void delete(BancoHoras bancoHoras) {
		repository.deleteById(bancoHoras.getBancoHorasCod());
	}
	
	private void adicionarUsuarioInformacao(BancoHoras bancoHoras) {
	    if (bancoHoras.getUsuarioCod() != 0) {
	        String usuarioUrl = URL_SERVICO_USUARIO + bancoHoras.getUsuarioCod();
	        try {
	            UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);
	            if (usuario != null) {
	            	bancoHoras.setUsuarioNome(usuario.getUsuario_nome());
	            	bancoHoras.setUsuarioCargo(usuario.getUsuario_cargo());
	            	bancoHoras.setNivelAcesso_cod(usuario.getNivelAcesso_cod());
	            	bancoHoras.setSetorCod(usuario.getSetorCod());
	            }
	        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	            System.err.println("Usuário com ID " + bancoHoras.getUsuarioCod() + " não encontrado.");
	        } catch (Exception e) {
	            System.err.println("Erro ao buscar usuário com ID " + bancoHoras.getUsuarioCod() + ": " + e.getMessage());
	        }
	    }
	}
}

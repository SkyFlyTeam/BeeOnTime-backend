package com.ms.solicitacao.service;

import com.ms.solicitacao.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.solicitacao.dto.UsuarioDTO;
import com.ms.solicitacao.model.Solicitacao;
import com.ms.solicitacao.model.SolicitacaoTipo;
import com.ms.solicitacao.repository.SolicitacaoRepository;
import com.ms.solicitacao.repository.SolicitacaoTipoRepository;

@Service
public class SolicitacaoService {
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private SolicitacaoTipoRepository solicitacaoTipoRepository;
	
	@Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_USUARIO = "http://localhost:8081/usuario/";  

    public List<Solicitacao> findAll() {
        List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
        for (Solicitacao solicitacao : solicitacoes) {
            adicionarUsuarioInformacao(solicitacao);
        }
        return solicitacoes;
    }
    
    public List<Solicitacao> findAllByUsuario(long usuarioId) {
    	List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
    	for (Solicitacao solicitacao : solicitacoes) {
            adicionarUsuarioInformacao(solicitacao);
        }
    	List<Solicitacao> solicitacoesUsuario = solicitacoes.stream()
    			.filter(solicitacao -> solicitacao.getUsuarioCod() == usuarioId)
    			.collect(Collectors.toList());
    	if (solicitacoesUsuario != null) {
    		return solicitacoesUsuario;
    	}
    	return null;
    }
    
    public List<Solicitacao> findAllBySetor(long setorCod) {
    	List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
    	for (Solicitacao solicitacao : solicitacoes) {
            adicionarUsuarioInformacao(solicitacao);
        }
    	List<Solicitacao> solicitacoesSetor = solicitacoes.stream()
    			.filter(solicitacao -> solicitacao.getSetorCod() == setorCod)
    			.collect(Collectors.toList());
    	if (solicitacoesSetor != null) {
    		return solicitacoesSetor;
    	}
    	return null;
    }

    public Solicitacao findById(long id) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        adicionarUsuarioInformacao(solicitacao);
        return solicitacao;
    }
	
	public Solicitacao save(Solicitacao solicitacao) {
	    if (solicitacao.getTipoSolicitacaoCod() != null) {
	        Optional<SolicitacaoTipo> tipo = solicitacaoTipoRepository.findById(solicitacao.getTipoSolicitacaoCod().getTipoSolicitacaoCod());
	        tipo.ifPresent(tipoSolicitacao -> solicitacao.setTipoSolicitacaoCod(tipoSolicitacao));
	    }
	    return solicitacaoRepository.save(solicitacao);
	}
	
	public Solicitacao edit(Solicitacao solicitacao) {
	    Solicitacao selecionado = solicitacaoRepository.findById(solicitacao.getSolicitacaoCod())
	        .orElseThrow(() -> new RuntimeException("Solicitação não encontrada!"));

	    if (solicitacao.getSolicitacaoDevolutiva() != null) {
	        selecionado.setSolicitacaoDevolutiva(solicitacao.getSolicitacaoDevolutiva());
	    }
	    if (solicitacao.getSolicitacaoStatus() != null) {
	        selecionado.setSolicitacaoStatus(solicitacao.getSolicitacaoStatus());
	    }
	    if (solicitacao.getSolicitacaoAnexo() != null && solicitacao.getSolicitacaoAnexo().length > 0) {
	        selecionado.setSolicitacaoAnexo(solicitacao.getSolicitacaoAnexo());
	        selecionado.setSolicitacaoAnexoNome(solicitacao.getSolicitacaoAnexoNome());
	    }
	    if (solicitacao.getSolicitacaoMensagem() != null) {
	        selecionado.setSolicitacaoMensagem(solicitacao.getSolicitacaoMensagem());
	    }
	    
	    if (solicitacao.getHorasSolicitadas() != null) {
	    	selecionado.setHorasSolicitadas(solicitacao.getHorasSolicitadas());
	    }

	    return solicitacaoRepository.save(selecionado);
	}



	public void delete(Solicitacao solicitacao) {
		solicitacaoRepository.deleteById(solicitacao.getSolicitacaoCod());
	}
	
	private void adicionarUsuarioInformacao(Solicitacao solicitacao) {
	    if (solicitacao.getUsuarioCod() != 0) {
	        String usuarioUrl = URL_SERVICO_USUARIO + solicitacao.getUsuarioCod();
	        try {
	            UsuarioDTO usuario = restTemplate.getForObject(usuarioUrl, UsuarioDTO.class);
	            if (usuario != null) {
	                solicitacao.setUsuarioNome(usuario.getUsuario_nome());
	                solicitacao.setUsuarioCargo(usuario.getUsuario_cargo());
	                solicitacao.setNivelAcesso_cod(usuario.getNivelAcesso_cod());
	                solicitacao.setSetorCod(usuario.getSetorCod());
	            }
	        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	            System.err.println("Usuário com ID " + solicitacao.getUsuarioCod() + " não encontrado.");
	        } catch (Exception e) {
	            System.err.println("Erro ao buscar usuário com ID " + solicitacao.getUsuarioCod() + ": " + e.getMessage());
	        }
	    }
	}

}

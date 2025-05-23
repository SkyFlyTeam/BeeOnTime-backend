package com.ms.solicitacao.service;

import com.ms.solicitacao.dto.HorasDTO;
import com.ms.solicitacao.dto.SolicitacaoTipoDTO;
import com.ms.solicitacao.dto.UsuarioDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.solicitacao.dto.HorasDTO;
import com.ms.solicitacao.dto.UsuarioDTO;
import com.ms.solicitacao.model.Solicitacao;
import com.ms.solicitacao.model.SolicitacaoStatus;
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
    
    private static final String URL_SERVICO_HORAS = "http://localhost:8082/horas/"; 

    public List<Solicitacao> findAll() {
        List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
        for (Solicitacao solicitacao : solicitacoes) {
            adicionarUsuarioInformacao(solicitacao);
        }
        return solicitacoes;
    }
    
    public List<Solicitacao> findByTipo(long tipoSolicitacaoCod){
    	List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
    	return solicitacoes.stream()
    			.filter(solicitacao -> solicitacao.getTipoSolicitacaoCod().getTipoSolicitacaoCod() == tipoSolicitacaoCod)
    			.collect(Collectors.toList());
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
    
    public List<Solicitacao> findAllBySetorTipo(long setorCod, long tipoSolicitacaoCod) {
    	List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();
    	for (Solicitacao solicitacao : solicitacoes) {
            adicionarUsuarioInformacao(solicitacao);
        }
    	List<Solicitacao> solicitacoesSetor = solicitacoes.stream()
    			.filter(solicitacao -> solicitacao.getSetorCod() == setorCod)
    			.collect(Collectors.toList());
    	if (solicitacoesSetor != null) {
    		return solicitacoesSetor.stream()
        			.filter(solicitacao -> solicitacao.getTipoSolicitacaoCod().getTipoSolicitacaoCod() == tipoSolicitacaoCod)
        			.collect(Collectors.toList());
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
            tipo.ifPresent(solicitacao::setTipoSolicitacaoCod);
        }

        // Tipos que não atualizam horas
        List<Long> tiposSemHoras = List.of(2L, 3L, 4L, 6L);
        Long tipoCod = solicitacao.getTipoSolicitacaoCod() != null ? solicitacao.getTipoSolicitacaoCod().getTipoSolicitacaoCod() : null;

        if (tipoCod != null && !tiposSemHoras.contains(tipoCod)) {
            // Atualiza horas só para os tipos que não estão na lista acima
            List<LocalDate> datas = solicitacao.getSolicitacaoDataPeriodo();

            if (datas == null || datas.isEmpty()) {
                throw new RuntimeException("Período de datas inválido ou vazio.");
            }

            for (LocalDate data : datas) {
                String dataFormatada = data.toString();
                String horasUrl = URL_SERVICO_HORAS + "usuario/" + solicitacao.getUsuarioCod() + "/dia?data=" + dataFormatada;

                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<String> entity = new HttpEntity<>(null, headers);

                    HorasDTO horas = restTemplate.exchange(horasUrl, HttpMethod.POST, entity, HorasDTO.class).getBody();

                    if (horas != null) {
                        horas.setHorasExtras(solicitacao.getHorasSolicitadas());
                        String horasUrlAtualizar = URL_SERVICO_HORAS + "atualizar";
                        HttpEntity<HorasDTO> requestEntity = new HttpEntity<>(horas, headers);
                        restTemplate.exchange(horasUrlAtualizar, HttpMethod.PUT, requestEntity, HorasDTO.class);
                    } else {
                        System.err.println("Horas não encontradas para o usuário na data " + dataFormatada);
                    }

                } catch (Exception e) {
                    System.err.println("Erro ao processar data " + dataFormatada + ": " + e.getMessage());
                }
            }
        } else {
            // Para os tipos que não atualizam horas, só salvar direto
            System.out.println("Tipo " + tipoCod + " não requer atualização de horas.");
        }

        return solicitacaoRepository.save(solicitacao);
    }


	
	public Solicitacao edit(Solicitacao solicitacao) {
	    Solicitacao selecionado = solicitacaoRepository.findById(solicitacao.getSolicitacaoCod())
	        .orElseThrow(() -> new RuntimeException("Solicitação não encontrada!"));

	    if (solicitacao.getSolicitacaoStatus() == SolicitacaoStatus.RECUSADA &&
	    	    solicitacao.getTipoSolicitacaoCod() != null &&
	    	    solicitacao.getTipoSolicitacaoCod().getTipoSolicitacaoCod() == 5) {
	        System.out.println("Status da solicitação é RECUSADA, atualizando horas...");

	        String dataFormatada = solicitacao.getSolicitacaoDataPeriodo().toString();
	        String horasUrl = URL_SERVICO_HORAS + "usuario/" + solicitacao.getUsuarioCod() + "/dia?data=" + dataFormatada;
	        System.out.println("URL de consulta: " + horasUrl);  

	        try {
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_JSON);  

	            HttpEntity<String> entity = new HttpEntity<>(null, headers);

	            HorasDTO horas = restTemplate.exchange(horasUrl, HttpMethod.POST, entity, HorasDTO.class).getBody();
	            if (horas != null) {
	                System.out.println("Horas recuperadas: " + horas);

	                horas.setHorasExtras(0.0);
	                String horasUrlAtualizar = URL_SERVICO_HORAS + "atualizar";
	                System.out.println("URL de atualização: " + horasUrlAtualizar);

	                HttpEntity<HorasDTO> requestEntity = new HttpEntity<>(horas, headers);
	                restTemplate.exchange(horasUrlAtualizar, HttpMethod.PUT, requestEntity, HorasDTO.class);

	            } else {
	                System.err.println("Horas não encontradas para o usuário.");
	            }
	        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
	            System.err.println("Usuário com ID " + solicitacao.getUsuarioCod() + " não encontrado.");
	        } catch (Exception e) {
	            System.err.println("Erro ao buscar usuário com ID " + solicitacao.getUsuarioCod() + ": " + e.getMessage());
	        }
	    }

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

	    if (solicitacao.getSolicitacaoDataPeriodo() != null) {
	        selecionado.setSolicitacaoDataPeriodo(solicitacao.getSolicitacaoDataPeriodo());
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
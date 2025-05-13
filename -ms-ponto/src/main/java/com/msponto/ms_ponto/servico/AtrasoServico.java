package com.msponto.ms_ponto.servico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.dto.AtrasoComNomeDTO;
import com.msponto.ms_ponto.dto.HorasComNomeDTO;
import com.msponto.ms_ponto.dto.JornadaDTO;
import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.entidade.mysql.Atraso;
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.enums.TipoPonto;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;
import com.msponto.ms_ponto.repositorio.mongo.MarcacaoPontosRepositorio;
import com.msponto.ms_ponto.repositorio.mysql.AtrasoRepositorio;
import com.msponto.ms_ponto.repositorio.mysql.HorasRepositorio;

import jakarta.transaction.Transactional;

@Service
public class AtrasoServico {
	
	@Autowired
	private AtrasoRepositorio repositorio;
	
	@Autowired
	private MarcacaoPontosRepositorio mponto_repo;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	@Autowired
	private MarcacaoPontosServico marcacaoPontosServico;
	
	@Autowired
	private HorasRepositorio horasRepositorio;
	
	public Atraso save(Atraso atraso) {
		return repositorio.save(atraso);
	}
	
	public List<AtrasoComNomeDTO> findAll(){
		List<Atraso> atrasos = repositorio.findAll();
		List<AtrasoComNomeDTO> atrasosFiltrados = adicionarNome(atrasos);
	    
	    return atrasosFiltrados;
	}
	
	public List<AtrasoComNomeDTO> findByUsuario(Long usuarioCod){
		List<Atraso> atrasos = repositorio.findAll();
		List<Atraso> usuarioAtrasos = atrasos.stream()
    			.filter(atraso -> atraso.getHoras().getUsuarioCod() == usuarioCod)
    			.collect(Collectors.toList());
		
		List<AtrasoComNomeDTO> atrasosFiltrados = adicionarNome(usuarioAtrasos);
		
		if (usuarioAtrasos != null) {
			return atrasosFiltrados;
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
	
	@Transactional
	public void delete(Long atrasoCod) {
	    // Busca o atraso pelo ID
	    Optional<Atraso> atrasoOpt = repositorio.findById(atrasoCod);
	    
	    if (atrasoOpt.isPresent()) {
	        Atraso atraso = atrasoOpt.get();
	        
	        // Recupera a entidade Horas associada
	        Horas horas = atraso.getHoras();
	        
	        if (horas != null) {
	            // Remove a referência ao Atraso em Horas
	            horas.setAtraso(null);
	            
	            // Salva a Horas com a referência removida
	            horasRepositorio.save(horas);
	        }
	        
	        // Agora é seguro excluir o Atraso
	        repositorio.delete(atraso);
	    } else {
	        throw new IllegalArgumentException("Atraso não encontrado com ID: " + atrasoCod);
	    }
	}



	public List<AtrasoComNomeDTO> findBySetor(Long setorCod) {
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
	    
	    List<AtrasoComNomeDTO> atrasosFiltrados = adicionarNome(atrasosSetor);
	    
	    return atrasosFiltrados;
	}

	public List<AtrasoComNomeDTO> findByDate(LocalDate dataInicio, LocalDate dataFim) {
	    List<Atraso> atrasos = repositorio.findAll();

	    atrasos = atrasos.stream()
	            .filter(atraso -> {
	                LocalDate data = atraso.getHoras().getHorasData();
	                boolean afterInicio = (dataInicio == null) || (!data.isBefore(dataInicio)); 
	                boolean beforeFim = (dataFim == null) || (!data.isAfter(dataFim));         
	                return afterInicio && beforeFim;
	            })
	            .collect(Collectors.toList());
	    
	    List<AtrasoComNomeDTO> atrasosFiltrados = adicionarNome(atrasos);
	    
	    return atrasosFiltrados;
	}

	public List<AtrasoComNomeDTO> findByDateFrom(LocalDate dataInicio) {
	    return findByDate(dataInicio, null);   
	}

	public List<AtrasoComNomeDTO> findByDateUntil(LocalDate dataFim) {
	    return findByDate(null, dataFim);   
	}
	
	public List<AtrasoComNomeDTO> adicionarNome(List<Atraso> atrasos) {
	    List<AtrasoComNomeDTO> atrasasAgrupados = atrasos.stream()
	        .map(atraso -> {
	            UsuarioDTO usuario = usuarioClient.getUsuarioByCod(atraso.getHoras().getUsuarioCod());
	            JornadaDTO jornada = usuarioClient.getJornadaByUsuario(usuario.getUsuario_cod());

	            AtrasoComNomeDTO atrasoComNome = new AtrasoComNomeDTO();
	            atrasoComNome.setAtrasoCod(atraso.getAtrasoCod());
	            atrasoComNome.setAtrasoTempo(atraso.getAtrasoTempo());

	            HorasComNomeDTO horas = new HorasComNomeDTO();
	            horas.setHorasCod(atraso.getHoras().getHorasCod());
	            horas.setHorasExtras(atraso.getHoras().getHorasExtras());
	            horas.setHorasTrabalhadas(atraso.getHoras().getHorasTrabalhadas());
	            horas.setHorasNoturnas(atraso.getHoras().getHorasNoturnas());
	            horas.setHorasFaltantes(atraso.getHoras().getHorasFaltantes());
	            horas.setHorasData(atraso.getHoras().getHorasData());
	            horas.setUsuarioCod(atraso.getHoras().getUsuarioCod());
	            horas.setUsuarioNome(usuario.getUsuario_nome());

	            // Verificação para o valor de jornada_horarioFlexivel (caso seja null, assume um valor padrão)
	            Boolean isFlexivel = jornada.getJornada_horarioFlexivel();
	            if (isFlexivel != null && isFlexivel) {
	                // Para jornada flexível, calcula o horário limite
	                LocalTime horarioIdealEntrada = marcacaoPontosServico.calcularHoraLimiteFlexivel(jornada.getUsuario_cod());
	                java.sql.Time horarioIdealEntradaTime = java.sql.Time.valueOf(horarioIdealEntrada);
	                horas.setJornada_horarioEntrada(horarioIdealEntradaTime);
	            } else {
	                // Caso não seja flexível ou seja null, usa o horário fixo
	                horas.setJornada_horarioEntrada(jornada.getJornada_horarioEntrada());
	            }

	            Optional<MarcacaoPontos> mpontos = mponto_repo.findByHorasCod(atraso.getHoras().getHorasCod());

	            if (mpontos.isPresent()) {
	                List<MarcacaoPontos.Ponto> pontos = mpontos.get().getPontos();
	                MarcacaoPontos.Ponto pontoEntrada = pontos.stream()
	                        .filter(ponto -> TipoPonto.ENTRADA.equals(ponto.getTipoPonto()))
	                        .findFirst()
	                        .orElse(null);

	                if (pontoEntrada != null) {
	                    horas.setHorarioBatida(pontoEntrada.getHorarioPonto());
	                }
	            }

	            atrasoComNome.setHoras(horas);
	            return atrasoComNome;
	        })
	        .collect(Collectors.toList());

	    return atrasasAgrupados;
	}
}

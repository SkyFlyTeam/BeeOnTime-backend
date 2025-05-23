package com.fatec.ms_usuario.servico;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.ms_usuario.dto.UsuarioDTO;
import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.FolgaRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

@Service
public class FolgaServico {
	
	@Autowired
    private FolgaRepositorio folgaRepository;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

    public List<Folga> listarTodos() {
        return folgaRepository.findAll();
    }

    public Optional<Folga> buscarPorId(Long id) {
        return folgaRepository.findById(id);
    }

    public Folga salvar(Folga folga) {
        return folgaRepository.save(folga);
    }

    public Folga atualizar(Folga folgaAtualizada) {
        return folgaRepository.findById(folgaAtualizada.getFolgaCod()).map(folga -> {
            if (folgaAtualizada.getFolgaDataPeriodo() != null) {
                folga.setFolgaDataPeriodo(folgaAtualizada.getFolgaDataPeriodo());
            }
            if (folgaAtualizada.getFolgaObservacao() != null) {
                folga.setFolgaObservacao(folgaAtualizada.getFolgaObservacao());
            }
            folga.setFolgaDiasUteis(folgaAtualizada.getFolgaDiasUteis() != 0 ? folgaAtualizada.getFolgaDiasUteis() : folga.getFolgaDiasUteis());

            return folgaRepository.save(folga);
        }).orElseThrow(() -> new RuntimeException("Folga não encontrada com id " + folgaAtualizada.getFolgaCod()));
    }


    public void deletar(Folga folga) {
        folgaRepository.deleteById(folga.getFolgaCod());
    }
    
    public List<Folga> listarPorSetor(Long setorCod) {
        List<Usuario> usuarios = usuarioRepositorio.findBySetorCod(setorCod);
        if (usuarios.isEmpty()) {
            return Collections.emptyList();
        }
        return folgaRepository.findByUsuarioCodIn(usuarios);
    }

    public List<Folga> listarFaltasPorEmpresaEData(Long empCod, LocalDate data) {
        YearMonth anoMes = YearMonth.from(data);
        LocalDate dataInicial = anoMes.atDay(1);
        LocalDate dataFinal = anoMes.atEndOfMonth();

        List<Folga> todasFolgas = folgaRepository.findAll(); 

        // Buscar todos usuários da empresa
        List<Usuario> usuarios = usuarioRepositorio.findAll();

        // Filtrar usuários da empresa empCod
        Set<Long> usuarioCodsDaEmpresa = usuarios.stream()
            .filter(u -> empCod.equals(u.getEmpCod()))
            .map(Usuario::getUsuario_cod)
            .collect(Collectors.toSet());

        // Filtrar folgas que:
        // 1) pertençam a usuário da empresa
        // 2) tenham pelo menos uma data dentro do mês (dataInicial <= data <= dataFinal)
        List<Folga> folgasFiltradas = todasFolgas.stream()
            .filter(folga -> 
                folga.getUsuarioCod() != null 
                && usuarioCodsDaEmpresa.contains(folga.getUsuarioCod().getUsuario_cod())
                && folga.getFolgaDataPeriodo() != null
                && folga.getFolgaDataPeriodo().stream()
                    .anyMatch(d -> !d.isBefore(dataInicial) && !d.isAfter(dataFinal))
            )
            .collect(Collectors.toList());
        return folgasFiltradas;
    }
}
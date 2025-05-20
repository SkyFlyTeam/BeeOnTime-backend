package com.fatec.ms_usuario.servico;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
package com.fatec.ms_usuario.controle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.servico.FolgaServico;


@RestController
@RequestMapping("/folgas")
public class FolgaControle {

	@Autowired
    private FolgaServico folgaServico;

    @GetMapping
    public ResponseEntity<List<Folga>> listarTodos() {
        return ResponseEntity.ok(folgaServico.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Folga> buscarPorId(@PathVariable Long id) {
        return folgaServico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/setor/{setorCod}")
    public ResponseEntity<?> buscarPorSetor(@PathVariable Long setorCod){
    	return ResponseEntity.ok(folgaServico.listarPorSetor(setorCod));
    }

    @GetMapping("/empresa/{empCod}/mes/{data}")
    public ResponseEntity<?> buscarPorEmpresaEData(@PathVariable Long empCod, @PathVariable LocalDate data){
    	return ResponseEntity.ok(folgaServico.listarFaltasPorEmpresaEData(empCod, data));
    }

    @GetMapping("/usuario/{usuarioCod}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioCod){
    	return ResponseEntity.ok(folgaServico.listarPorUsuario(usuarioCod));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Folga> criar(@RequestBody Folga folga) {
        Folga novaFolga = folgaServico.salvar(folga);
        return ResponseEntity.ok(novaFolga);
    }

    @PutMapping("/editar")
    public ResponseEntity<Folga> atualizar(@RequestBody Folga folgaAtualizada) {
        try {
            Folga folga = folgaServico.atualizar(folgaAtualizada);
            return ResponseEntity.ok(folga);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletar(@RequestBody Folga folga) {
    	folgaServico.deletar(folga);
        return ResponseEntity.noContent().build();
    }
}
package com.fatec.ms_usuario.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.FolgaRepositorio;
import com.fatec.ms_usuario.repositorio.FolgaTipoRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

@RequestMapping("/folgas")
public class FolgaControle {
    private static final Logger logger = LoggerFactory.getLogger(FolgaControle.class);

    @Autowired
    private FolgaRepositorio folgaRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private FolgaTipoRepositorio folgaTipoRepositorio;

    @GetMapping
    public List<Folga> listarTodas() {
        return folgaRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Folga> buscarPorId(@PathVariable long id) {
        Optional<Folga> folga = folgaRepositorio.findById(id);
        return folga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<Folga> salvar(
        @RequestParam("solicitacaoJson") String solicitacaoJson,
        @RequestParam(value = "solicitacaoAnexo", required = false) MultipartFile solicitacaoAnexo
    ) {
        
        System.out.println("solicitacaoJson: " + solicitacaoJson);
        try {
            solicitacaoJson = solicitacaoJson.replace('\u00A0', ' ').trim();
            ObjectMapper objectMapper = new ObjectMapper();
            Folga folga = objectMapper.readValue(solicitacaoJson, Folga.class);

            // Se o folgaTipoCod for 0 ou não for passado, atribuímos um valor padrão
            if (folga.getFolgaTipo() == null || folga.getFolgaTipo().getFolTipoCod() == 0) {
                FolgaTipo folgaTipoDefault = folgaTipoRepositorio.findById(1L).orElse(null); // Pega o tipo de folga com ID = 1
                if (folgaTipoDefault != null) {
                    folga.setFolgaTipo(folgaTipoDefault); // Define o tipo de folga padrão
                }
            }

            // Processa o anexo, se fornecido
            if (solicitacaoAnexo != null && !solicitacaoAnexo.isEmpty()) {
                folga.setDocumento(solicitacaoAnexo.getBytes()); // Armazenando o arquivo no banco como binário
            }

            // Buscar o Usuario pelo código
            Usuario usuario = usuarioRepositorio.findById(folga.getUsuario().getUsuario_cod()).orElse(null);
            if (usuario != null) {
                folga.setUsuario(usuario);
                // Salvar a Folga no banco de dados
                Folga folgaSalva = folgaRepositorio.save(folga);
                return ResponseEntity.status(HttpStatus.CREATED).body(folgaSalva);
            } else {
                return ResponseEntity.badRequest().build();  // Se não encontrar o usuário
            }
        } catch (IOException e) {
            logger.error("Erro ao processar JSON ou arquivo: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar a Folga: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        folgaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

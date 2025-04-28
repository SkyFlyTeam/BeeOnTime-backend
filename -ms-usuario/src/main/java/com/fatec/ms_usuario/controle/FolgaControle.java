package com.fatec.ms_usuario.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.FolgaRepositorio;
import com.fatec.ms_usuario.repositorio.FolgaTipoRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;
import com.fatec.ms_usuario.dto.SolicitacaoFolgaDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @PostMapping("/cadastrar")
    public ResponseEntity<Folga> cadastrarFolga(@RequestParam("solicitacaoJson") String solicitacaoJson,
                                                @RequestParam(value = "solicitacaoAnexo", required = false) MultipartFile solicitacaoAnexo) {
        
         try {
            // Converte o JSON recebido para o formato esperado
            ObjectMapper objectMapper = new ObjectMapper();
            SolicitarFolgaDTO solicitacao = objectMapper.readValue(solicitacaoJson, SolicitarFolgaDTO.class);
            
            // Converte a lista de strings para LocalDate
            List<LocalDate> dataPeriodo = solicitacao.getFolDataPeriodo().stream()
                                                    .map(data -> LocalDate.parse(data))
                                                    .collect(Collectors.toList());
            
            // Cria a folga com os dados recebidos
            Folga folga = new Folga();
            folga.setUsuario(solicitacao.getUsuario());
            folga.setFolgaTipo(solicitacao.getFolgaTipo());
            folga.setFolDataPeriodo(dataPeriodo);
            folga.setFolObservacao(solicitacao.getFolObservacao());
            
            // Verifica se o anexo foi enviado e armazena como byte array
            if (solicitacaoAnexo != null) {
                folga.setDocumento(solicitacaoAnexo.getBytes());  // Armazena o arquivo como byte array
            }

            // Salva a folga no banco de dados
            folgaRepositorio.save(folga);
        
            return ResponseEntity.ok(folga);
        } catch (JsonProcessingException e) {
            // Trate a exceção específica do parsing do JSON
            return ResponseEntity.status(400).body(null); // Retorna erro 400 em caso de falha no parsing JSON
        } catch (IOException e) {
            // Trate a exceção de leitura do arquivo (MultipartFile)
            return ResponseEntity.status(500).body(null); // Retorna erro 500 em caso de falha ao ler o arquivo
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        folgaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

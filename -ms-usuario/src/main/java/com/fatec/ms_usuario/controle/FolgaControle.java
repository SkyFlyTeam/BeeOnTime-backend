package com.fatec.ms_usuario.controle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.ms_usuario.dto.SolicitacaoDTO;
import com.fatec.ms_usuario.entidade.Folga;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.entidade.FolgaTipo;
import com.fatec.ms_usuario.servico.FolgaServico;

@RestController
@RequestMapping("/folgas")
public class FolgaControle {

    @Autowired
    private FolgaServico folgaServico;

    @Autowired
    private ObjectMapper objectMapper;

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

    @PostMapping(value = "/cadastrar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> criarFolga(
            @RequestParam("solicitacaoJson") String solicitacaoJson,
            @RequestParam(value = "solicitacaoAnexo", required = false) MultipartFile solicitacaoAnexo
    ) {
        try {
            SolicitacaoDTO dto = objectMapper.readValue(solicitacaoJson, SolicitacaoDTO.class);

            Folga folga = mapearDtoParaFolga(dto);

            if (solicitacaoAnexo != null && !solicitacaoAnexo.isEmpty()) {
                // Caso queira armazenar o anexo na entidade, adicione o campo e salve aqui:
                // folga.setDocumento(solicitacaoAnexo.getBytes());
                // Ou implemente lógica para salvar arquivo em storage externo e guardar referência
            }

            folgaServico.salvar(folga);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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

    // Método privado para mapear DTO para entidade Folga
    private Folga mapearDtoParaFolga(SolicitacaoDTO dto) {
        Folga folga = new Folga();
        folga.setFolgaObservacao(dto.getSolicitacaoMensagem());

        if (dto.getSolicitacaoDataPeriodo() != null) {
            List<LocalDate> datas = dto.getSolicitacaoDataPeriodo().stream()
                    .map(LocalDate::parse)
                    .toList();
            folga.setFolgaDataPeriodo(datas);
        }

        Usuario usuario = new Usuario();
        usuario.setUsuario_cod(dto.getUsuarioCod());
        folga.setUsuarioCod(usuario);

        FolgaTipo tipo = new FolgaTipo();
        tipo.setTipoFolgaCod(dto.getTipoSolicitacaoCod());
        folga.setFolgaTipo(tipo);

        // Se tiver outros campos no DTO que queira mapear, faça aqui

        return folga;
    }
}

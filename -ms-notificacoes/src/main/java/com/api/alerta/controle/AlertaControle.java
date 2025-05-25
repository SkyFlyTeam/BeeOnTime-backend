package com.api.alerta.controle;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.alerta.dto.AlertaDTO;
import com.api.alerta.entidade.Alerta;
import com.api.alerta.repositorio.AlertaRepositorio;

@RestController
@RequestMapping("/alerta")
@CrossOrigin(origins = "*")
public class AlertaControle {

    @Autowired
    private AlertaRepositorio repositorio;

    @GetMapping
        public List<AlertaDTO> listarAlertas() {
        List<Alerta> alertas = repositorio.findAll();

        return alertas.stream().map(alerta -> {
            AlertaDTO dto = new AlertaDTO();
            dto.setAlertaCod(alerta.getAlertaCod());
            dto.setAlertaMensagem(alerta.getAlertaMensagem());
            dto.setAlertaDataCriacao(alerta.getAlertaDataCriacao());

            AlertaDTO.AlertaTipoDTO tipoDTO = new AlertaDTO.AlertaTipoDTO(
                alerta.getTipoAlerta().getTipoAlertaCod(),
                alerta.getTipoAlerta().getTipoAlertaNome()
            );

            dto.setTipoAlerta(tipoDTO);
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Alerta> cadastrarAlerta(@RequestBody Alerta alerta) {
        Alerta savedAlerta = repositorio.save(alerta);
        return new ResponseEntity<>(savedAlerta, HttpStatus.CREATED);
    }

    @GetMapping("/{alertaCod}")
    public ResponseEntity<AlertaDTO> obterAlertaPorId(@PathVariable Long alertaCod) {
        return repositorio.findById(alertaCod)
            .map(alerta -> {
                AlertaDTO dto = new AlertaDTO();
                dto.setAlertaCod(alerta.getAlertaCod());
                dto.setAlertaMensagem(alerta.getAlertaMensagem());
                dto.setAlertaDataCriacao(alerta.getAlertaDataCriacao());

                AlertaDTO.AlertaTipoDTO tipoDTO = new AlertaDTO.AlertaTipoDTO(
                    alerta.getTipoAlerta().getTipoAlertaCod(),
                    alerta.getTipoAlerta().getTipoAlertaNome()
                );
                dto.setTipoAlerta(tipoDTO);

                return new ResponseEntity<>(dto, HttpStatus.OK);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tipo/{nome}")
        public ResponseEntity<List<AlertaDTO>> getAlertasPorTipo(@PathVariable String nome) {
            List<Alerta> alertas = repositorio.findByTipoAlerta_TipoAlertaNome(nome);

            List<AlertaDTO> alertasDTO = alertas.stream().map(alerta -> {
                AlertaDTO dto = new AlertaDTO();
                dto.setAlertaCod(alerta.getAlertaCod());
                dto.setAlertaMensagem(alerta.getAlertaMensagem());
                dto.setAlertaDataCriacao(alerta.getAlertaDataCriacao());

                AlertaDTO.AlertaTipoDTO tipoDTO = new AlertaDTO.AlertaTipoDTO(
                    alerta.getTipoAlerta().getTipoAlertaCod(),
                    alerta.getTipoAlerta().getTipoAlertaNome()
                );
                dto.setTipoAlerta(tipoDTO);

                return dto;
            }).toList();

            return ResponseEntity.ok(alertasDTO);
        }


    @PutMapping("/{alertaCod}")
    public ResponseEntity<Alerta> editarAlerta(@PathVariable Long alertaCod, @RequestBody Alerta alertaAtualizado) {
        return repositorio.findById(alertaCod)
                .map(alerta -> {
                    alerta.setAlertaMensagem(alertaAtualizado.getAlertaMensagem());
                    alerta.setAlertaDataCriacao(alertaAtualizado.getAlertaDataCriacao());
                    alerta.setTipoAlerta(alertaAtualizado.getTipoAlerta());
                    Alerta updatedAlerta = repositorio.save(alerta);
                    return new ResponseEntity<>(updatedAlerta, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{alertaCod}")
    public ResponseEntity<Void> deletarAlerta(@PathVariable Long alertaCod) {
        if (repositorio.existsById(alertaCod)) {
            repositorio.deleteById(alertaCod);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

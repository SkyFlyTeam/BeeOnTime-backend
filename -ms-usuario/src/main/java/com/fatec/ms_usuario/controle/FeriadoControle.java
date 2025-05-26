package com.fatec.ms_usuario.controle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

import com.fatec.ms_usuario.entidade.Feriado;
import com.fatec.ms_usuario.repositorio.FeriadoRepositorio;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/feriado")
public class FeriadoControle {

    @Autowired
    private FeriadoRepositorio repositorio;

    @GetMapping
    public ResponseEntity<List<Feriado>> obterTodosFeriados() {
        List<Feriado> feriados = repositorio.findAll();
        return new ResponseEntity<>(feriados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Feriado>> cadastrarFeriados(@RequestBody List<Feriado> feriados) {
        List<Feriado> savedFeriados = repositorio.saveAll(feriados);
        return new ResponseEntity<>(savedFeriados, HttpStatus.CREATED);
    }

    @GetMapping("/{feriadoCod}")
    public ResponseEntity<Feriado> obterFeriadoPorId(@PathVariable Long feriadoCod) {
        return repositorio.findById(feriadoCod)
                .map(feriado -> new ResponseEntity<>(feriado, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/empresa/{empCod}")
    public ResponseEntity<List<Feriado>> obterFeriadosPorEmpresa(@PathVariable Long empCod) {
        List<Feriado> feriados = repositorio.findByEmpCod(empCod);
        return new ResponseEntity<>(feriados, HttpStatus.OK);
    }

    @GetMapping("/empresa/{empCod}/data/{data}")
    public ResponseEntity<Feriado> obterFeriadosPorEmpresaEDia(@PathVariable Long empCod, @PathVariable LocalDate data) {
        Optional<Feriado> feriado = repositorio.findByEmpCodAndFeriadoData(empCod, data);
        if (feriado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feriado.get(), HttpStatus.OK);
    }

    @PutMapping("/{feriadoCod}")
    public ResponseEntity<Feriado> editarFeriado(@PathVariable Long feriadoCod, @RequestBody Feriado feriadoAtualizado) {
        return repositorio.findById(feriadoCod)
                .map(feriado -> {
                    feriado.setFeriadoData(feriadoAtualizado.getFeriadoData());
                    feriado.setFeriadoNome(feriadoAtualizado.getFeriadoNome());
                    Feriado updatedFeriado = repositorio.save(feriado);
                    return new ResponseEntity<>(updatedFeriado, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{feriadoCod}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long feriadoCod) {
        if (repositorio.existsById(feriadoCod)) {
            repositorio.deleteById(feriadoCod);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

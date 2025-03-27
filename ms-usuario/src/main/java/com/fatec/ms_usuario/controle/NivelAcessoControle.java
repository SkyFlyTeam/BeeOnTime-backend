package com.fatec.ms_usuario.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.ms_usuario.entidade.NivelAcesso;
import com.fatec.ms_usuario.repositorio.NivelAcessoRepositorio;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/nivelacesso")
public class NivelAcessoControle {

    @Autowired
    private NivelAcessoRepositorio repositorio;

    @GetMapping
    public ResponseEntity<List<NivelAcesso>> obterNivelAcessos() {
		List<NivelAcesso> nivelAcessos = repositorio.findAll();
		return new ResponseEntity<>(nivelAcessos, HttpStatus.OK);
	}

    @GetMapping("/{id}")
    public ResponseEntity<NivelAcesso> obterNivelAcessoId(@PathVariable Long id) {
        NivelAcesso nivelAcesso = repositorio.findById(id).orElse(null);
		
  		if (nivelAcesso == null) 
  			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  		
  		return new ResponseEntity<>(nivelAcesso, HttpStatus.OK);
    }

    /*
    // Não necessário devido a natureza estática dos níveis de acesso
    @PostMapping
    public ResponseEntity<?> cadastrarNivelAcesso(@RequestBody NivelAcesso novo) {
        if(novo == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repositorio.save(novo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */

    @PutMapping
    public ResponseEntity<?> atualizarNivelAcesso(@RequestBody NivelAcesso nivelAcessoNovo) {
        Optional<NivelAcesso> nivelAcessoOptional = repositorio.findById(nivelAcessoNovo.getNivelAcesso_cod());
       
        NivelAcesso nivelAcesso = nivelAcessoOptional.get();
        nivelAcesso.setNivelAcesso_nome(nivelAcessoNovo.getNivelAcesso_nome());

        repositorio.save(nivelAcesso);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

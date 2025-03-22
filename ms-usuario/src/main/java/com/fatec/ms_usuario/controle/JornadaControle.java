package com.fatec.ms_usuario.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.ms_usuario.entidade.Jornada;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.JornadaRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/jornada")
public class JornadaControle {
	
	@Autowired
	private JornadaRepositorio repositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@GetMapping("/jornadas")
	public ResponseEntity<List<Jornada>> obterJornadas() {
		List<Jornada> jornadas = repositorio.findAll();
		return new ResponseEntity<>(jornadas, HttpStatus.OK);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarJornada(@RequestBody Jornada jornada) {
	    HttpStatus status = HttpStatus.BAD_REQUEST;
	    if (jornada != null) {
	        if (jornada.getJornada_horarioFlexivel() != null && jornada.getJornada_diasSemana() != null) {

	            Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(jornada.getUsuario_cod());
	            if (usuarioOptional.isPresent()) {
	                Usuario usuario = usuarioOptional.get();
	                // Aqui, associamos o usuário à jornada
	                jornada.setUsuario(usuario);

	                // Salva a jornada com o usuario_cod preenchido corretamente
	                repositorio.save(jornada);
	                status = HttpStatus.OK;
	            } else {
	                return new ResponseEntity<>("Usuário com ID " + jornada.getUsuario_cod() + " não encontrado.", HttpStatus.BAD_REQUEST);
	            }
	        }
	    }
	    return new ResponseEntity<>(status);
	}






	
	@GetMapping("/{id}")
	public ResponseEntity<Jornada> obterJornadaId(@PathVariable Long id) {
		Jornada jornada = repositorio.findById(id).orElse(null);
		
		if (jornada == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(jornada, HttpStatus.OK);
		}
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<?> atualizarJornada(@RequestBody Jornada jornada) {
		Optional<Jornada> jornadaOptional = repositorio.findById(jornada.getJornada_cod());
		
		if (jornadaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Jornada não encontrada: " + jornada.getJornada_cod());
		}
	
		Jornada alvo = jornadaOptional.get();
		alvo.setJornada_horarioFlexivel(jornada.getJornada_horarioFlexivel());
		alvo.setJornada_cod(jornada.getJornada_cod());
		alvo.setJornada_diasSemana(jornada.getJornada_diasSemana());
		alvo.setJornada_horarioEntrada(jornada.getJornada_horarioEntrada());
		alvo.setJornada_horarioSaida(jornada.getJornada_horarioSaida());
		
		repositorio.save(alvo);
		return ResponseEntity.ok("Jornada atualizada com sucesso");
	}
	
	@DeleteMapping("/excluir")
	public ResponseEntity<?> excluirJornada(@RequestBody Jornada exclusao) {
		Optional<Jornada> jornadaOptional = repositorio.findById(exclusao.getJornada_cod());
		
		if (jornadaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Jornada com ID " + exclusao.getJornada_cod() + " não encontrada.");
		}
		
		repositorio.delete(jornadaOptional.get());
		return ResponseEntity.ok("Jornada excluída com sucesso");
	}
}

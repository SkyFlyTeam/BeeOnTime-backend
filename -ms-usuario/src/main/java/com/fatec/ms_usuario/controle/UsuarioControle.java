package com.fatec.ms_usuario.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.ms_usuario.dto.UsuarioDTO;
import com.fatec.ms_usuario.entidade.Empresa;
import com.fatec.ms_usuario.entidade.Jornada;
import com.fatec.ms_usuario.entidade.Setor;
import com.fatec.ms_usuario.entidade.Usuario;
import com.fatec.ms_usuario.repositorio.EmpresaRepositorio;
import com.fatec.ms_usuario.repositorio.JornadaRepositorio;
import com.fatec.ms_usuario.repositorio.SetorRepositorio;
import com.fatec.ms_usuario.repositorio.UsuarioRepositorio;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

    @Autowired
    private UsuarioRepositorio repositorio;
    
    @Autowired
    private JornadaRepositorio jornadaRepositorio;

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private SetorRepositorio setorRepositorio;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obterUsuarios() {
		List<Usuario> usuarios = repositorio.findAll();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDTO novoDTO) {
        if (novoDTO == null || novoDTO.getEmpCod() == null || novoDTO.getSetorCod() == null) {
            return new ResponseEntity<>("empCod ou setorCod está nulo", HttpStatus.BAD_REQUEST);
        }

        // Recupera Empresa e Setor pelo código
        Optional<Empresa> empresaOptional = empresaRepositorio.findById(novoDTO.getEmpCod());
        Optional<Setor> setorOptional = setorRepositorio.findById(novoDTO.getSetorCod());

        if (empresaOptional.isEmpty() || setorOptional.isEmpty()) {
            return new ResponseEntity<>("Empresa ou Setor não encontrado", HttpStatus.BAD_REQUEST);
        }

        // Criando o objeto Usuario
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsuario_nome(novoDTO.getUsuario_nome());
        novoUsuario.setUsuario_cpf(novoDTO.getUsuario_cpf());
        novoUsuario.setUsuario_nrRegistro(novoDTO.getUsuario_nrRegistro());
        novoUsuario.setUsuario_cargaHoraria(novoDTO.getUsuario_cargaHoraria());
        novoUsuario.setUsuarioTipoContratacao(novoDTO.getUsuarioTipoContratacao());
        novoUsuario.setUsuario_dataContratacao(novoDTO.getUsuario_dataContratacao());
        novoUsuario.setUsuario_senha(passwordEncoder.encode(novoDTO.getUsuario_senha()));
        novoUsuario.setUsuarioEmail(novoDTO.getUsuarioEmail());
        novoUsuario.setUsuario_DataNascimento(novoDTO.getUsuario_DataNascimento());
        novoUsuario.setUsuario_cargo(novoDTO.getUsuario_cargo());

        novoUsuario.setEmpCod(novoDTO.getEmpCod());
        novoUsuario.setSetorCod(novoDTO.getSetorCod());
        novoUsuario.setNivelAcesso_cod(novoDTO.getNivelAcesso_cod());

        novoUsuario.setEmpresa(empresaOptional.get());
        novoUsuario.setSetor(setorOptional.get());

        repositorio.save(novoUsuario);

        Jornada jornada = new Jornada();
        jornada.setUsuario(novoUsuario); 
        jornada.setUsuario_cod(novoUsuario.getUsuario_cod());
        jornada.setJornada_horarioFlexivel(novoDTO.getHorarioFlexivel());
        jornada.setJornada_diasSemana(novoDTO.getDiasSemana()); 
        jornada.setJornada_horarioEntrada(novoDTO.getHorarioEntrada()); 
        jornada.setJornada_horarioSaida(novoDTO.getHorarioSaida()); 
        jornada.setJornada_horarioAlmoco(novoDTO.getHorarioAlmoco());

        jornadaRepositorio.save(jornada);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioId(@PathVariable Long id) {
    	Usuario usuario = repositorio.findById(id).orElse(null);
		
  		if (usuario == null) {
  			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  		} else {
  			return new ResponseEntity<>(usuario, HttpStatus.OK);
  		}
    }

    @GetMapping("/empresa/{empCod}")
    public ResponseEntity<List<Usuario>> obterUsuariosEmpresa(@PathVariable Long empCod) {
    	List<Usuario> usuario = repositorio.findByEmpCod(empCod);
		
  		return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = repositorio.findById(usuario.getUsuario_cod());

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: Usuário com ID " + usuario.getUsuario_cod() + " não encontrado.");
        }

        Usuario alvo = usuarioOptional.get();
        alvo.setUsuario_nome(usuario.getUsuario_nome());
        alvo.setUsuario_cpf(usuario.getUsuario_cpf());
        alvo.setUsuario_nRegistro(usuario.getUsuario_nRegistro());
        alvo.setUsuario_cargaHoraria(usuario.getUsuario_cargaHoraria());
        alvo.setUsuarioTipoContratacao(usuario.getUsuarioTipoContratacao());
        alvo.setUsuario_dataContratacao(usuario.getUsuario_dataContratacao());
        if(!usuario.getUsuario_senha().equals(""))
            alvo.setUsuario_senha(passwordEncoder.encode(usuario.getUsuario_senha()));
        alvo.setUsuarioEmail(usuario.getUsuarioEmail());
        alvo.setUsuario_DataNascimento(usuario.getUsuario_DataNascimento());
        alvo.setEmpCod(usuario.getEmpCod());
        alvo.setSetorCod(usuario.getSetorCod());

        repositorio.save(alvo);
        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    }


    
    
    @DeleteMapping("/excluir")
    public ResponseEntity<?> deletarUsuario(@RequestBody Usuario exclusao) {
        Optional<Usuario> usuarioOptional = repositorio.findById(exclusao.getUsuario_cod());

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: Usuário com ID " + exclusao.getUsuario_cod() + " não encontrado.");
        }

        // Excluir as jornadas associadas a este usuário antes de excluir o usuário
        Jornada jornadas = jornadaRepositorio.findByUsuario(usuarioOptional.get());
            jornadaRepositorio.delete(jornadas);

        repositorio.delete(usuarioOptional.get());
        return ResponseEntity.ok("Usuário e suas jornadas excluídos com sucesso.");
    }

    public Optional<Usuario> obterFuncionarioEmail(String email) {
        return repositorio.findByUsuarioEmail(email);
     }
}
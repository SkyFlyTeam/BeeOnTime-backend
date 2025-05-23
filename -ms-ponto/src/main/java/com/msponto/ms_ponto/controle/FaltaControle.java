package com.msponto.ms_ponto.controle;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.entidade.mysql.Falta;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;
import com.msponto.ms_ponto.repositorio.mysql.FaltaRepositorio;


@RestController
@RequestMapping("/faltas")
public class FaltaControle {

    @Autowired
    private FaltaRepositorio faltaRepositorio;
    
    @Autowired
    private UsuarioClient usuarioClient;

    @GetMapping("/")
    public ResponseEntity<List<Falta>> getAllFaltas() {
        List<Falta> faltas = faltaRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(faltas);
    }

    @GetMapping("/{usuario_cod}")
    public ResponseEntity<List<Falta>> getAllFaltas(@PathVariable Long usuario_cod) {
        List<Falta> faltas = faltaRepositorio.findByUsuarioCod(usuario_cod);
        return ResponseEntity.status(HttpStatus.OK).body(faltas);
    }

    @GetMapping("/{usuario_cod}/dia")
    public ResponseEntity<Falta> getAllFaltas(@PathVariable Long usuario_cod, @RequestParam("data") String data) {
        LocalDate converted_data = LocalDate.parse(data);
        Falta falta = faltaRepositorio.findByFaltaDiaAndUsuarioCod(converted_data, usuario_cod);
        return ResponseEntity.status(HttpStatus.OK).body(falta);
    }

    @GetMapping("/empresa/{empCod}/mes/{dia}")
    public ResponseEntity<List<Falta>> getAllEmpresaFaltasByDate(@PathVariable Long empCod, @PathVariable LocalDate data) {
        YearMonth anoMes = YearMonth.from(data);
        LocalDate dataInicial = anoMes.atDay(1);
        LocalDate dataFinal = anoMes.atEndOfMonth();
        List<Falta> faltasMes = faltaRepositorio.findByFaltaDiaBetween(dataInicial, dataFinal);
        List<UsuarioDTO> usuarios = usuarioClient.getAllUsuarios();
        // Filtra só os usuários da empresa empCod
        Set<Long> usuarioCodsDaEmpresa = usuarios.stream()
            .filter(u -> empCod.equals(u.getEmpCod()))  
            .map(UsuarioDTO::getUsuario_cod)  
            .collect(Collectors.toSet());

        // Filtra faltas para só as dos usuários da empresa
        List<Falta> faltasDaEmpresa = faltasMes.stream()
            .filter(f -> usuarioCodsDaEmpresa.contains(f.getUsuarioCod()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(faltasDaEmpresa);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> updateHoras(@RequestBody Falta falta) {

        Optional<Falta> faltaOptional = faltaRepositorio.findById(falta.getFaltaCod());
		
		if (faltaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Falta não encontrada: " + falta.getFaltaCod());
		}

        Falta faltaEntity = faltaOptional.get();

        faltaEntity.setFaltaJustificativa(falta.getFaltaJustificativa());

        faltaRepositorio.save(faltaEntity);

        return ResponseEntity.status(HttpStatus.OK).body("Falta atualizada com sucesso");
    }
    
}

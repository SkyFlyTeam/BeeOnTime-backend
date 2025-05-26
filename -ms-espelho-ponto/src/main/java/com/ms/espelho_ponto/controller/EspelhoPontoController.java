package com.ms.espelho_ponto.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.espelho_ponto.dto.BancoHorasDTO;
import com.ms.espelho_ponto.dto.EmpresaDTO;
import com.ms.espelho_ponto.dto.HorasTrabalhadasDTO;
import com.ms.espelho_ponto.dto.PontoDTO;
import com.ms.espelho_ponto.dto.UsuarioDTO;
import com.ms.espelho_ponto.model.EspelhoPonto;
import com.ms.espelho_ponto.service.BancoHorasService;
import com.ms.espelho_ponto.service.EmpresaService;
import com.ms.espelho_ponto.service.EspelhoPontoService;
import com.ms.espelho_ponto.service.HorasTrabalhadasService;
import com.ms.espelho_ponto.service.PdfService;
import com.ms.espelho_ponto.service.PontoService;
import com.ms.espelho_ponto.service.UsuarioService;


@RestController
@RequestMapping("/espelho-ponto")
public class EspelhoPontoController {

    @Autowired
    private EspelhoPontoService espelhoPontoService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private BancoHorasService bancoHorasService;

    @Autowired
    private HorasTrabalhadasService horasTrabalhadasService;

    @Autowired
    private PontoService pontoService;

    @GetMapping()
    private List<EspelhoPonto> findAll() {
        return espelhoPontoService.findAll();
    }

    @GetMapping("/usuario/{usuarioCod}")
	private List<EspelhoPonto> findByUsuario(@PathVariable long usuarioCod) {
        return espelhoPontoService.findAllByUsuario(usuarioCod);
    }

    @PostMapping("/cadastrar")
	private ResponseEntity<?> save(@RequestBody EspelhoPonto espelhoPonto) {
		if(espelhoPonto.getUsuarioCod() == null) {
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		espelhoPontoService.save(espelhoPonto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

    @PutMapping("/editar")
	private ResponseEntity<EspelhoPonto> edit(@RequestBody EspelhoPonto espelhoPonto) {
		EspelhoPonto updated = espelhoPontoService.update(espelhoPonto);
		return ResponseEntity.ok(updated);
	}

@GetMapping("/generate-pdf/{usuarioCod}/{espelhoPontoCod}")
public ResponseEntity<byte[]> generatePdf(@PathVariable int usuarioCod, @PathVariable int espelhoPontoCod) {

    UsuarioDTO usuarioSelecionado = usuarioService.getUsuarioById(usuarioCod);
    EmpresaDTO empresaSelecionada = empresaService.getEmpresaById(usuarioSelecionado.getEmpCod());
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = today.format(formatter);
    List<BancoHorasDTO> bancoHorasSelecionado = bancoHorasService.getBancoHoras(formattedDate);

    List<BancoHorasDTO> filteredBancoHoras = bancoHorasSelecionado.stream()
                            .filter(horas -> horas.getUsuarioCod().equals((long)usuarioCod))
                            .collect(Collectors.toList());

    List<HorasTrabalhadasDTO> horasTrabalhadasSelecionada = horasTrabalhadasService.getHorasTrabalhadasByDate(formattedDate);

    List<HorasTrabalhadasDTO> histPontos = horasTrabalhadasService.getHorasDiariasByDateAndUserCod(formattedDate, usuarioCod);

    List<HorasTrabalhadasDTO> filteredHorasTrabalhadas = horasTrabalhadasSelecionada.stream()
                        .filter(horas -> horas.getUsuarioCod().equals((long)usuarioCod))
                        .collect(Collectors.toList());
    
    List<PontoDTO> pontos = pontoService.getPontoByUsuarioId(usuarioCod);

    EspelhoPonto espelhosPontosRelacionados = espelhoPontoService.findById((long)espelhoPontoCod);

    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));
    String monthName = today.format(monthFormatter).toUpperCase();

    int year = LocalDate.now().getYear();
    int lastTwoDigits = year % 100;

    monthName = monthName + "/" + lastTwoDigits;


    String empNome = empresaSelecionada.getEmpNome();
    String empCNPJ = empresaSelecionada.getEmpCnpj();
    String username = usuarioSelecionado.getUsuario_nome();
    String nrRegistro = usuarioSelecionado.getUsuario_nrRegistro();
    String usuarioCpf = usuarioSelecionado.getUsuario_cpf();
    String cargo = usuarioSelecionado.getUsuario_cargo();
    String setor = usuarioSelecionado.getSetor().getSetorNome();
    String bancoHoras = filteredBancoHoras.get(0).getSaldoAcumulado().toString();
    String horasTrabalhadas = filteredHorasTrabalhadas.get(0).getHorasTotal().toString();

    try {
        byte[] pdfBytes = pdfService.generateUserPointsPdf(empNome, empCNPJ, username, nrRegistro, usuarioCpf,
         cargo, setor, bancoHoras, horasTrabalhadas, monthName, histPontos, pontos, espelhosPontosRelacionados);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_points.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        // Return 500 Internal Server Error or custom error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

}

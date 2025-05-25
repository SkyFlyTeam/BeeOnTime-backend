package com.ms.espelho_ponto.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ms.espelho_ponto.dto.UsuarioDTO;
import com.ms.espelho_ponto.model.EspelhoPonto;
import com.ms.espelho_ponto.repository.EspelhoPontoRepository;


@Service
public class EspelhoPontoService {

    @Autowired
    public EspelhoPontoRepository repository;

    @Autowired
    public UsuarioService usuarioService;

    public List<EspelhoPonto> findAll() {
        List<EspelhoPonto> espelhoPontos = repository.findAll();
        return espelhoPontos;
    }

    public List<EspelhoPonto> findAllByUsuario(long usuarioCod) {
        List<EspelhoPonto> espelhoPontos = repository.findAll();
        List<EspelhoPonto> EspelhosPontoUsuario = espelhoPontos.stream()
    		.filter(espelhoPonto -> espelhoPonto.getUsuarioCod() == usuarioCod)
    		.collect(Collectors.toList());
    	if (EspelhosPontoUsuario != null) {
    		return EspelhosPontoUsuario;
    	}
    	return null;
    }

    public EspelhoPonto findById(long id){
        EspelhoPonto espelhoPonto = repository.findById(id).orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        return espelhoPonto;
    }

    public EspelhoPonto save(EspelhoPonto espelhoPonto) {
        EspelhoPonto novoEspelhoPonto = new EspelhoPonto();
        novoEspelhoPonto.setEspelhoPontoAssinado(espelhoPonto.isEspelhoPontoAssinado());
        novoEspelhoPonto.setEspelhoPontoDataGeracao(espelhoPonto.getEspelhoPontoDataGeracao());
        novoEspelhoPonto.setEspelhoPontoMes(espelhoPonto.getEspelhoPontoMes());
        novoEspelhoPonto.setEspelhoPontoUrl(espelhoPonto.getEspelhoPontoUrl());
        novoEspelhoPonto.setEspelhoPontoDataAssinatura(espelhoPonto.getEspelhoPontoDataAssinatura());
        novoEspelhoPonto.setUsuarioCod(espelhoPonto.getUsuarioCod());

        repository.save(novoEspelhoPonto);
        return novoEspelhoPonto;
    }

    public EspelhoPonto update(EspelhoPonto espelhoPonto) {
        Optional<EspelhoPonto> espelhoOptional = repository.findById(espelhoPonto.getEspelhoPontoCod());

        EspelhoPonto alvo = espelhoOptional.get();
        alvo.setEspelhoPontoAssinado(espelhoPonto.isEspelhoPontoAssinado());
        alvo.setEspelhoPontoDataGeracao(espelhoPonto.getEspelhoPontoDataGeracao());
        alvo.setEspelhoPontoMes(espelhoPonto.getEspelhoPontoMes());
        alvo.setEspelhoPontoUrl(espelhoPonto.getEspelhoPontoUrl());
        alvo.setEspelhoPontoDataAssinatura(espelhoPonto.getEspelhoPontoDataAssinatura());

        repository.save(alvo);

        return alvo;
    }

    //Função responsável por criar os espelhos de ponto para todos os usuários na virada de mês
    @Scheduled(cron = "0 0 1 * * ?") 
    private void generateEspelhoPonto() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuario();

        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));
        String lastMonthName = lastMonth.format(monthFormatter); 

        Date espelhoPontoDataGeracao = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        for (UsuarioDTO usuario : usuarios) {
            EspelhoPonto novoEspelhoPonto = new EspelhoPonto();
            novoEspelhoPonto.setEspelhoPontoAssinado(false); 
            novoEspelhoPonto.setEspelhoPontoDataGeracao(espelhoPontoDataGeracao); 
            novoEspelhoPonto.setEspelhoPontoMes(lastMonthName);  
            novoEspelhoPonto.setEspelhoPontoUrl("");  
            novoEspelhoPonto.setEspelhoPontoDataAssinatura(null); 
            novoEspelhoPonto.setUsuarioCod(usuario.getUsuario_cod());

            repository.save(novoEspelhoPonto);
        }

        System.out.println("EspelhoPonto generated for all users for the previous month (" + lastMonthName + ").");
    }
}

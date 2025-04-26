package com.msponto.ms_ponto.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.msponto.ms_ponto.servico.HorasServico;

import jakarta.annotation.PostConstruct;

@EnableScheduling
@Configuration
public class GerarRegistroHoras {

    @Autowired
    private HorasServico horas_servico;

    @PostConstruct
    public void runJobOnStartup() { // Rodar o job de verificação de usuários ao iniciar a aplicação
        verificarDiaTrabalhadoDosUsuarios();
        horas_servico.verificarFaltaDosUsuarios();  
    }

    // Job Cron para rodar uma vez por dia, à meia-noite
    @Scheduled(cron = "0 0 0 * * ?") // Execução diária às 00:00
    public void verificarDiaTrabalhadoDosUsuarios() {
        System.out.println("JOB CRON EXECUTADO");
        horas_servico.verificarDiaTrabalhadoDosUsuarios();
    }

     // Job Cron para rodar uma vez por dia, à meia-noite
    @Scheduled(cron = "0 0 0 * * ?") // Execução diária às 00:00
    public void verificarFaltaDosUsuarios() {
        System.out.println("FALTAS ATUALIZADAS");
        horas_servico.verificarFaltaDosUsuariosJob();
    }
}

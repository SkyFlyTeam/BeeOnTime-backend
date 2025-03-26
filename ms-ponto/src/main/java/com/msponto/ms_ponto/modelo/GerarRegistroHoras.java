package com.msponto.ms_ponto.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;
import com.msponto.ms_ponto.servico.HorasServico;

import jakarta.annotation.PostConstruct;

@EnableScheduling
@Configuration
public class GerarRegistroHoras {

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private HorasServico horas_servico;

    @PostConstruct
    public void runJobOnStartup() { // Rodar o job de verificação de usuários ao iniciar a aplicação
        verificarDiaTrabalhadoDosUsuarios();  
    }

    // Job Cron para rodar uma vez por dia, à meia-noite
    @Scheduled(cron = "0 0 0 * * ?") // Execução diária às 00:00
    public void verificarDiaTrabalhadoDosUsuarios() {
        System.out.println("JOB CRON EXECUTADO");
        // Buscar todos os usuários do microserviço
        List<UsuarioDTO> usuarios = usuarioClient.getAllUsuarios();
        LocalDate dataAtual = LocalDate.now();
        
        // Enviar uma tarefa para a fila para cada usuário
        for (UsuarioDTO usuario : usuarios) {
            
            VerificadorDiaTrabalhado verificador = new VerificadorDiaTrabalhado();
            Boolean dia_trabalhado = verificador.verificar(usuario, dataAtual);
            
            if(dia_trabalhado){
                System.out.println("USUARIO TRABALHA HOJE, CRIANDO TABELA VAZIA" + usuario.getUsuario_cod());
                horas_servico.createEmptyHoras(usuario.getUsuario_cod(), dataAtual);
            }
        }
    }
}

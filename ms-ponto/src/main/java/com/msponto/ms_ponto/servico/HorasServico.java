package com.msponto.ms_ponto.servico;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.dto.UsuarioDTO;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos.Ponto;
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.enums.TipoPonto;
import com.msponto.ms_ponto.modelo.HorasAtualizador;
import com.msponto.ms_ponto.ms_clients.UsuarioClient;
import com.msponto.ms_ponto.repositorio.mysql.HorasRepositorio;

@Service
public class HorasServico {
    @Autowired
    private HorasRepositorio horas_repo;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<Horas> getUsuarioHoras(Long usuario_cod){
        List<Horas> usuario_horas = horas_repo.findByUsuarioCod(usuario_cod);
        return usuario_horas;
    }
    
    public List<Horas> getUsuarioHorasByDate(Long usuario_cod, LocalDate horas_data){
        List<Horas> usuario_horas = horas_repo.findByUsuarioCodAndHorasData(usuario_cod, horas_data);
        return usuario_horas;
    }

    public List<Horas> getUsuarioHorasByPeriod(Long usuario_cod, LocalDate start_date, LocalDate end_date){
        List<Horas> usuario_horas = horas_repo.findByUsuarioCodAndHorasDataBetween(usuario_cod, start_date, end_date);
        return usuario_horas;
    }

    public Horas getHorasCodByDate(LocalDate date){
        Horas horas = horas_repo.findByHorasData(date);
        return horas;
    }

    public Optional<Horas> getHorasByCod(Long horas_cod){
        Optional<Horas> horas = horas_repo.findById(horas_cod);
        return horas;
    }

    public Boolean createHoras(Horas horas){
        try{
            horas_repo.save(horas);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public Horas createEmptyHoras(Long usuario_cod, LocalDate data){
        Horas horas = new Horas(); 
        
        horas.setUsuarioCod(usuario_cod);
        horas.setHorasExtras(0.0f); 
        horas.setHorasNoturnas(0.0f);
        horas.setHorasTrabalhadas(0.0f);
        horas.setHorasFaltantes(0.0f);
        horas.setHorasData(data);

        horas_repo.save(horas);

        return horas;
    }

    public Boolean updateHoras(Horas horas_att){
         try {
            Optional<Horas> horas_existente = horas_repo.findById(horas_att.getHorasCod());
            if (horas_existente.isPresent()) {
                Horas horas = horas_existente.get();
                
                HorasAtualizador atualizador = new HorasAtualizador();
                atualizador.atualizarDados(horas_att, horas);

                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("deu erro: " + e);
            return false;
        }
    }

    public Boolean calculatingHours(MarcacaoPontos mPontos){
        try {
           // Buscando ponto de entrada
           List<Ponto> pontos = mPontos.getPontos();
           Optional<Ponto> entrada_existe = pontos.stream()
            .filter(ponto -> TipoPonto.ENTRADA.equals(ponto.getTipoPonto()))
            .findFirst();

           if (entrada_existe.isPresent()) {
                // Pegando carga hóraria do usuário
                Long usuario_cod = mPontos.getUsuarioCod();
                UsuarioDTO usuario =  usuarioClient.getUsuarioByCod(usuario_cod);
                Integer usuario_carga = usuario.getUsuario_cargaHoraria();

                // Pegando o registro de horas
                Horas horas = horas_repo.findByHorasCod(mPontos.getHorasCod());

                // Pegando ponto de entrada e o último registrado
                Ponto entrada = entrada_existe.get();
                Ponto ultimo_ponto = pontos.get(pontos.size() - 1);
               
                // Calculando tempo decorrido
                Duration tempo_decorrido = Duration.between(entrada.getHorarioPonto(), ultimo_ponto.getHorarioPonto());

                // Se ainda não for hora extra
                if(tempo_decorrido.toHours() <= usuario_carga){
                    Float horas_faltantes = (float) usuario_carga - (float) tempo_decorrido.toMinutes() / 60.0f;
    
                    Float horas_trabalhadas = (float) tempo_decorrido.toMinutes() / 60.0f;

                    horas.setHorasFaltantes(horas_faltantes);
                    horas.setHorasTrabalhadas(horas_trabalhadas);
                }else{ // Se for hora extra
                    Float horas_extras = (float) tempo_decorrido.toMinutes() / 60.0f - (float) usuario_carga;

                    horas.setHorasFaltantes(0.0f);
                    horas.setHorasTrabalhadas((float) usuario_carga);
                    horas.setHorasExtras(horas_extras);
                }

                horas_repo.save(horas); 
                return true;
           }
           return false;
       } catch (Exception e) {
           System.out.println("deu erro: " + e);
           return false;
       }
   }
}

package com.msponto.ms_ponto.servico;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos.Ponto;
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.repositorio.mongo.MarcacaoPontosRepositorio;

@Service
public class MarcacaoPontosServico {

    @Autowired
    private MarcacaoPontosRepositorio mponto_repo;

    @Autowired
    HorasServico horas_servico;

    public List<MarcacaoPontos> getPontosUsuario(Long usuario_cod) {
        List<MarcacaoPontos> pontos = mponto_repo.findByUsuarioCod(usuario_cod);
        return pontos;
    }

    public List<MarcacaoPontos> getPontosUsuarioByPeriod(Long mpontos_cod, LocalDate start_date, LocalDate end_date){
        List<MarcacaoPontos> mpontos = mponto_repo.findByUsuarioCodAndDataBetween(mpontos_cod, start_date, end_date);
        return mpontos;
    }

    public Optional<MarcacaoPontos> getPontosUsuarioByCodHoras(Long horasCod){
        Optional<MarcacaoPontos> mpontos = mponto_repo.findByHorasCod(horasCod);
        return mpontos;
    }

    public Boolean baterPonto(MarcacaoPontos mponto) {
        try {
            Optional<Horas> horas_existente = horas_servico.getHorasByCod(mponto.getHorasCod());
        
            if(horas_existente.isPresent()){
                Horas horas = horas_existente.get();
                Optional<MarcacaoPontos> mpontos_existente = mponto_repo.findByHorasCod(horas.getHorasCod());
                // Marcação de ponto já existe, só irei adicionar
                if(mpontos_existente.isPresent()){
                    MarcacaoPontos mponto_existe = mpontos_existente.get();
                    mponto_existe.getPontos().add(mponto.getPontos().getFirst());
                    mponto_repo.save(mponto_existe);

                    // Atualizando a quantidade de horas 
                    List<Ponto> pontos = mponto_existe.getPontos();
        
                    if (pontos.size() <= 1) {
                        System.out.println("[ERRO]: Não há pontos o suficiente para calcular as horas");
                        return true;
                    }

                    Boolean horas_atualizadas = horas_servico.calculatingHours(mponto_existe);

                    if(!horas_atualizadas){
                        System.err.println("[ERRO]: Falha ao tentar atualizar as horas");
                        return false;
                    }

                    return true;
                }else{
                    mponto.setData(LocalDate.now());
                    mponto_repo.save(mponto);
                }
                return true;
            } 
            return false;
        } catch (Exception e) {
            System.err.println("[ERRO]: " + e);
            return false;
        }
    }
    
    public Boolean updateMponto(MarcacaoPontos mpontos_att) {
        try {
            Optional<MarcacaoPontos> mponto_existente = mponto_repo.findById(mpontos_att.getId());
            if (mponto_existente.isPresent()) {
                MarcacaoPontos mponto = mponto_existente.get();
                
                mponto.setPontos(mpontos_att.getPontos());
                
                mponto_repo.save(mponto);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("[ERRO]: " + e);
            return false;
        }
    }    

}

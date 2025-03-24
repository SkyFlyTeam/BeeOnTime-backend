package com.msponto.ms_ponto.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.entidade.mysql.Horas;
import com.msponto.ms_ponto.repositorio.mongo.MarcacaoPontosRepositorio;

@Service
public class MarcacaoPontosServico {

    @Autowired
    private MarcacaoPontosRepositorio mponto_repo;

    @Autowired
    HorasServico horas_servico;


    public Boolean baterPonto(MarcacaoPontos mponto) {
        try {
            Long horasCod = horas_servico.getHorasCodByDate(mponto.getData()).getHorasCod();
            mponto.setHorasCod(horasCod);

            Optional<Horas> horas_existente = horas_servico.getHorasByCod(mponto.getHorasCod());
            
            // Marcação de ponto já existe, só irei adicionar
            if(horas_existente.isPresent()){
                Horas horas = horas_existente.get();
                Optional<MarcacaoPontos> mpontos_existente = mponto_repo.findByHorasCod(horas.getHorasCod());
            
            }else{ // Nova marcação de ponto

                mponto_repo.save(mponto);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("deu erro" + e);
            return false;
        }
    }
    
    public List<MarcacaoPontos> getPontosUsuario(Long usuario_cod) {
        List<MarcacaoPontos> pontos = mponto_repo.findByUsuarioCod(usuario_cod);
        return pontos;
    }

    public Optional<MarcacaoPontos> getMPontosById(Long mpontos_cod){
        Optional<MarcacaoPontos> mpontos = mponto_repo.findByHorasCod(mpontos_cod);
        return mpontos;
    }
}

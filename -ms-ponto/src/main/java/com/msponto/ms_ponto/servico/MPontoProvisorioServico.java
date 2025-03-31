package com.msponto.ms_ponto.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msponto.ms_ponto.entidade.mongo.MPontoProvisorio;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos;
import com.msponto.ms_ponto.entidade.mongo.MPontoProvisorio.PontoProvisorio;
import com.msponto.ms_ponto.entidade.mongo.MarcacaoPontos.Ponto;
import com.msponto.ms_ponto.repositorio.mongo.MPontoProvisorioRepositorio;

@Service
public class MPontoProvisorioServico {

    @Autowired
    private MPontoProvisorioRepositorio mpontoprov_repo;

    @Autowired
    private MarcacaoPontosServico mponto_servico;

    @Autowired
    private HorasServico horas_servico;

    public MPontoProvisorio getMpontoProvBySolicitacaoCod(Long solicitacao_cod) {
        MPontoProvisorio pontos = mpontoprov_repo.findBySolicitacaoCod(solicitacao_cod);
        return pontos;
    }

    public Boolean createMPontoProv(MPontoProvisorio mpontoprov) {
        try {
            mpontoprov_repo.save(mpontoprov);
            return true;
        } catch (Exception e) {
            System.out.println("deu erro" + e);
            return false;
        }
    }
    
    public Boolean makeSolicitacaoAction(MPontoProvisorio mpontoprov, Integer decision) {
        try {
            Optional<MPontoProvisorio> mpontoprov_existe = mpontoprov_repo.findById(mpontoprov.getId());
            if (mpontoprov_existe.isPresent()) {
                MPontoProvisorio mpontoprov_existente = mpontoprov_existe.get();
                
                // Se a solicitação for aprovada
                if(decision == 0){
                    Optional<MarcacaoPontos> mponto_existe = mponto_servico.getPontosUsuarioByCodHoras(mpontoprov_existente.getHorasCod());
                    if (mponto_existe.isPresent()) {
                        MarcacaoPontos mponto = mponto_existe.get();
                        
                        List<Ponto> pontos = mponto.getPontos();
                        List<PontoProvisorio> pontos_prov = mpontoprov_existente.getPontos();
                        for (int i = 0; i < pontos.size(); i++) {
                            pontos.get(i).setHorarioPonto(pontos_prov.get(i).getHorarioPonto());
                            pontos.get(i).setTipoPonto(pontos_prov.get(i).getTipoPonto());
                        }

                        Boolean att_sucessful = mponto_servico.updateMponto(mponto);
                        if(!att_sucessful){
                            System.err.println("Erro ao atualizar o ponto solicitado");
                            return false;
                        }

                        Boolean att_horas = horas_servico.calculatingHours(mponto);
                        if(!att_horas){
                            System.err.println("Erro ao atualizar as horas depois do ajuste do ponto");
                            return false;
                        }
                    }else{
                        System.err.println("Marcação de Ponto não encontrado, buscando para o cod: " + mpontoprov_existente.getHorasCod());
                        return false;
                    }
                }

                // mpontoprov_repo.delete(mpontoprov_existente);
                return true;
            }
            
            System.err.println("Ponto provisório não encontrado");
            return false;
        } catch (Exception e) {
            System.err.println("[ERRO]: " + e);
            return false;
        }
    }    

}

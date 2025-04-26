package com.ms.banco_horas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ms.banco_horas.model.BancoHoras;
import com.ms.banco_horas.model.ExtrasPagas;
import java.time.LocalDate;
import java.util.List;

@Component
public class BancoHorasScheduler {

    @Autowired
    private BancoHorasService bancoHorasService;

    @Autowired
    private ExtrasPagasService extrasPagasService;

    @Scheduled(cron = "0 0 0 1 6,12 *")  
    public void converterBancoHorasParaExtrasPagas() {
        List<BancoHoras> bancoHorasList = bancoHorasService.findAll();

        for (BancoHoras bancoHoras : bancoHorasList) {
            if (bancoHoras.getBancoHorasSaldoAtual() > 0) {
                ExtrasPagas extrasPagas = new ExtrasPagas();
                extrasPagas.setUsuarioCod(bancoHoras.getUsuarioCod());
                extrasPagas.setExtrasPagasSaldoAtual(bancoHoras.getBancoHorasSaldoAtual());
                extrasPagas.setExtrasPagasData(LocalDate.now()); 
                
                extrasPagasService.save(extrasPagas);

                bancoHoras.setBancoHorasSaldoAtual(0.0);
                bancoHorasService.edit(bancoHoras);  
            }
        }
    }
}

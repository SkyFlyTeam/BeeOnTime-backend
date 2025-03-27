package com.msponto.ms_ponto.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msponto.ms_ponto.entidade.mongo.MPontoProvisorio;
import com.msponto.ms_ponto.servico.MPontoProvisorioServico;


@RestController
@RequestMapping("/mpontoprov")
public class MPontoProvisorioControle {

    @Autowired
    MPontoProvisorioServico mpontoprov_servico;


    @GetMapping("/solicitacao/{solicitacao_cod}")
    public MPontoProvisorio getSolicitacaoPontos(@PathVariable Long solicitacao_cod) {
        MPontoProvisorio pontos = mpontoprov_servico.getMpontoProvBySolicitacaoCod(solicitacao_cod);
        return pontos;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> createMPontoProv(@RequestBody MPontoProvisorio mpontoprov) {

        Boolean ponto_sucessful = mpontoprov_servico.createMPontoProv(mpontoprov);

        if(!ponto_sucessful){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao cadastrar pontos provisórios.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Ponto provisórios cadastrados com sucesso!");
    }
    
    @PostMapping("/decisao/{decisao}")
    public ResponseEntity<String> makeSolicitacaoAction(@PathVariable Integer decisao, @RequestBody MPontoProvisorio mpontoprov) {

        Boolean action_sucessful = mpontoprov_servico.makeSolicitacaoAction(mpontoprov, decisao);

        if(!action_sucessful){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao processar decisão da solicitação.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Decisão processada com sucesso!");
    }
}

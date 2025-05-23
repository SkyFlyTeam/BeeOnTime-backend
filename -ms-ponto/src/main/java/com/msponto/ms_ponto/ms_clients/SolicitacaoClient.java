package com.msponto.ms_ponto.ms_clients;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.msponto.ms_ponto.dto.SolicitacaoDTO;

@Component
public class SolicitacaoClient {
    private final String SOLICITACAO_API_URL = "http://mssolicitacao:8083/solicitacao/cadastrar";  // URL do microserviço de solicitações

    private final WebClient.Builder webClientBuilder;

    public SolicitacaoClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Boolean criarSolicitacao(SolicitacaoDTO solicitacaoDTO) {
        try {
            // Construindo o JSON que será enviado no corpo multipart
            String json = "{ \"solicitacaoDataPeriodo\": \"" + solicitacaoDTO.getSolicitacaoDataPeriodo() +
                    "\", \"solicitacaoMensagem\": \"" + solicitacaoDTO.getSolicitacaoMensagem() +
                    "\", \"usuarioCod\": " + solicitacaoDTO.getUsuarioCod() +
                    ", \"horasSolicitadas\": " + solicitacaoDTO.getHorasSolicitadas() + 
                    ", \"tipoSolicitacaoCod\": { \"tipoSolicitacaoCod\": " + solicitacaoDTO.getTipoSolicitacaoCod().getTipoSolicitacaoCod() + " } }";

            // Criando o mapa de parâmetros multipart
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("solicitacaoJson", json);  // Corpo JSON com a chave solicitacaoJson

            // Enviando a requisição multipart sem o arquivo
            webClientBuilder.build()
                .post()  // Usando o método POST
                .uri(SOLICITACAO_API_URL)
                .contentType(MediaType.MULTIPART_FORM_DATA)  // Tipo de conteúdo multipart
                .bodyValue(body)  // Passando o corpo da requisição
                .retrieve()
                .bodyToMono(Void.class)  // A resposta será vazia
                .block();  // Bloqueia até que a requisição seja concluída

            return true;  // Se a solicitação foi criada sem erros
        } catch (Exception e) {
            System.err.println("[ERRO]: Falha ao criar solicitação de hora extra - " + e);
            return false;  // Se ocorrer um erro, retorna false
        }
    }
}

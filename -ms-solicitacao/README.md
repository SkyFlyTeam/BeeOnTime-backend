# SERVIÇO DE SOLICITAÇÃO

⚙️ **Execução do Sistema**

1. Crie um banco de dados com o nome 'solicitacoes_db'

2. Clone o repositório:

        git clone https://github.com/SkyFlyTeam/BeeOnTime-backend.git

3. Navegue até /-ms-solicitacao/src/main/resources, no arquivo application.properties, altere:

        spring.datasource.username={usuário do seu banco de dados}
        spring.datasource.password={senha do seu banco de dados}

4. Navegue até o diretório do serviço:

        /-ms-solicitacao/com/ms/solicitacao
        Execute o arquivo SolicitacaoApplication.java

**Requisições**

GET

    http://localhost:8080/solicitacao
    http://localhost:8080/solicitacao/{id}

POST

    http://localhost:8080/solicitacao
    corpo:
        solicitacaoJson: { 
            "solicitacaoMensagem": "Mensagem da solicitação",  
            "usuarioCod": código do usuário,  
            "tipoSolicitacaoCod": 
                {    
                "tipoSolicitacaoCod": número do tipo de solicitação  
                }
            }
PUT

    http://localhost:8080/solicitacao/editar
    corpo:
            {
                "solicitacaoCod": código da solicitação,
                "solicitacaoAnexo": arquivo de anexo,
                "solicitacaoDevolutiva": mensagem devolutiva,
                "solicitacaoStatus": número do tipo de solicitação ,
                "usuarioCod": código do usuário
            }
DELETE  

    http://localhost:8080/solicitacao/deletar
    corpo:
        {
            "solicitacaoCod": código da solicitação
        }
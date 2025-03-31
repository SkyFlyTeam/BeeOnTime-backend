## Instalação e utilização

### Configuração do backend

- Fazer o clone do repositório backend
https://github.com/SkyFlyTeam/BeeOnTime-backend.git

- Dentro da pasta "BeeOnTime-backend" entrar na pasta de "-ms-usuario" e abrir em seu editor de código

- Fazendo o caminho src > main > resources terá o arquivo "application.properties" onde você irá configurar seu banco de dados (será necessário ter criado ele antecipadamente)

```
spring.datasource.url=jdbc:mysql://localhost:3306/[NOME_SCHEMA]
spring.datasource.username=[USUÁIO]
spring.datasource.password=[SENHA]
spring.jpa.show-sql=true
```

- Após configurar estes arquivos digite o seguinte comando dentro da pasta para iniciar o projeto:o 
```
mvn spring-boot:run
```

- Repita o processo com o -ms-solicitações, rodando os dois ao mesmo tempo

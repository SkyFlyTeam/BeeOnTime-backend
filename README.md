## 🚀 Instalação

### 1. Clone o repositório
```bash
git clone https://github.com/SkyFlyTeam/BeeOnTime-backend.git
```

### 2. Configuração dos serviços

Para **cada serviço** (`-ms-usuario`, `-ms-ponto`, `-ms-solicitacao`, `-ms-banco-horas`):

- Abra o projeto no seu editor.
- Edite o arquivo `src/main/resources/application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/[NOME_DO_SCHEMA]
  spring.datasource.username=[USUÁRIO]
  spring.datasource.password=[SENHA]
  spring.jpa.show-sql=true
  ```
> Certifique-se de que o schema (banco de dados) foi criado no MySQL.

### 3. Configuração extra para o `-ms-ponto`

Além do `application.properties`, edite também:
```
src/main/java/com/msponto/ms_ponto/config/MySqlConfig.java
```
Atualize as informações do banco de dados conforme necessário.

### 4. Executando os serviços

Navegue até a pasta de cada serviço e execute:
```bash
mvn spring-boot:run
```
> Todos os serviços precisam estar rodando ao mesmo tempo.

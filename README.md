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
Todos os serviços precisam estar rodando ao mesmo tempo.


## Testes no Postman

Para a realização dos testes no `Postman`, você deve usar os seguintes endereços:

```ts
# GET
http://localhost:8080/test/postgres
```

```ts
# GET
http://localhost:8080/test/mongo
```

```ts
# GET
http://localhost:8080/test/redis
```

```ts
# GET
http://localhost:8080/test/mysql
```

## Rotas disponíveis

<div align="center">

|                                                                    Tipo | Rota                     | Ação                               |
| ----------------------------------------------------------------------: | :----------------------- | :--------------------------------- |
|  <hr>                                                                   |  <hr>                    | **Controle de usuários**       |
|    [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `http://localhost:8080`                | Listagem de usuários               |
|    [![](https://img.shields.io/badge/GET-2E8B57?style=for-the-badge)]() | `http://localhost:8080/buscar-id/1`            | Dados de um usuário específico     |
|   [![](https://img.shields.io/badge/POST-4682B4?style=for-the-badge)]() | `http://localhost:8080/cadastrar`          | Cadastro de usuários               |
|    [![](https://img.shields.io/badge/PUT-9370DB?style=for-the-badge)]() | `http://localhost:8080/atualizar-id`          | Alteração dos dados do usuário     |
| [![](https://img.shields.io/badge/DELETE-CD853F?style=for-the-badge)]() | `http://localhost:8080/2`     | Exclusão de usuários               |


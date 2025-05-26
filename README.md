## Proocesso de instalação:
Clone o repositório:
```git
git clone https://github.com/SkyFlyTeam/BeeOnTime-backend.git
```

Instale DOCKER na sua máquina:
- https://docs.docker.com/desktop/

Com o docker rodando em sua máquina, abra um terminal na pasta onde foi clonado o repositório e excute o seguinte comando:

```bash
# Inicializa a aplicação (Todos os mmicro-serviços)

docker compose up --build
```

Após o inicio da aplicação, acesse o container do banco de dados com o comando:
```bash
docker exec -it mysql-dev
```

Acesse o banco de dados:
```bash
mysql -u root -p
```
E digite a senha do container (root por padrão)

Após isso, execute os seguintes comandos de criação no banco de dados:
```sql
create database botponto;
create database botsolicitacao;
create database botbancohoras;
/* Adicionar nova database aqui caso tenha sido criado um novo micro-serviço */
```

Inicie novamente a aplicação com o comando:
```bash
docker compose up --build
```

---
---

## Criação de um novo micro-servio:
Adicione a pasta do novo micro-seriço na raiz do clone do projeto.

Altere as conexões com o banco de dados mysql em seu application.properties conforme o exemplo abaixo
```properties
spring.datasource.url=jdbc:mysql://db:3308/suadatabase
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.show-sql=true
```

Tenha certeza de que as referências http para outros micro-serviços carregam seus respectivos nomes ao invés do IP. Exemplo:
```java
    private static final String URL_SERVICO_USUARIO = "http://msusuario:8081/usuario/";
```

Essa referência é definida pelo serviço no arquivo docker-compose.yml

```yml
version: "3.8"

services:
  msusuario:
    build: ./-ms-usuario
    container_name: ms-usuario-dev
    ports:
      - "8081:8081"
    volumes:
      - ./-ms-usuario:/msusuario
    environment:
      - JAVA_OPTS=-Xmx512m
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3308/botusuario
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
    depends_on:
      - db

  db:
    image: mysql:8
    container_name: mysql-dev
    restart: always
    environment:
      MYSQL_DATABASE: botusuario
      MYSQL_ROOT_PASSWORD: root
      MYSQL_TCP_PORT: 3308
    ports:
      - "3308:3308"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:

```

Por fim adicione um novo serviço referente ao seu microserviço:

```yml
version: "3.8"

services:
  msusuario:
    build: ./-ms-usuario
    container_name: ms-usuario-dev
    ports:
      - "8081:8081"
    volumes:
      - ./-ms-usuario:/msusuario
    environment:
      - JAVA_OPTS=-Xmx512m
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3308/botusuario
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
    depends_on:
      - db

  msnovo:
    build: ./-ms-novo
    container_name: ms-novo-dev
    ports:
      - "8085:8085"
    volumes:
      - ./-ms-novo:/msnovo
    environment:
      - JAVA_OPTS=-Xmx512m
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3308/botnovo
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
    depends_on:
      - db

  db:
    image: mysql:8
    container_name: mysql-dev
    restart: always
    environment:
      MYSQL_DATABASE: botusuario
      MYSQL_ROOT_PASSWORD: root
      MYSQL_TCP_PORT: 3308
    ports:
      - "3308:3308"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:

```

ATENÇÃO:
- build: deve ser passado o caminho até a pasta do novo micro-serviço;
- volumes: A primeira parte do caminho (antes dos dois pontos) deve ser o caminho até a pasta do novo micro-serviço;
- ports: Não deve conter uma porta igual à: 8081, 8082, 8083, 8084 (Atualizar aqui caso atualizado);
- envirioment: SPRING_DATASOURCE_URL=jdbc:mysql://db:3308/botnovo (botnovo deve ser o mesmo nome da dabase presente em seu application.properties);

Por fim crie um arquivo chamado "dockerfile" na pasta do novo micro-serviço com esse coonteúdo:

```dockerfile
FROM eclipse-temurin:21-jdk

# Install maven if you're not using pre-built jars
RUN apt-get update && apt-get install -y maven

# Create app directory inside container
WORKDIR /msusuario

# Copy pom.xml and download dependencies early (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY . .

# Default command to build and run app (for dev purposes)
CMD ["mvn", "spring-boot:run"]

```

E outro chamado ".dockerignore" com esse conteúdo:
```dockcerignore
# Maven / Gradle build directories (compiled code)
target/
build/

# Local environment settings
.env
*.env

# IDE files and folders
.idea/
*.iml
.vscode/
*.classpath
*.project
*.settings/

# OS generated files
.DS_Store
Thumbs.db

# Logs
*.log

# Git
.git
.gitignore

# Temporary files
*.tmp
*.swp

# Dependency caches (optional, if you accidentally copy local repos)
.mvn/
.gradle

```

---
---

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


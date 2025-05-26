## Processo de instalação:
Clone o repositório:
```git
git clone https://github.com/SkyFlyTeam/BeeOnTime-backend.git
```

Instale DOCKER na sua máquina:
- https://docs.docker.com/desktop/

Com o docker rodando em sua máquina, abra um terminal na pasta onde foi clonado o repositório e excute o seguinte comando:

```bash
# Inicializa a aplicação (Todos os micro-serviços)

docker compose up --build
```

Após o inicio da aplicação, acesse o container do banco de dados com o comando:
```bash
docker exec -it mysql-dev bash
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
create database botespelhoponto;
create database botalertas;
/* Adicionar nova database aqui caso tenha sido criado um novo micro-serviço */
```

Inicie novamente a aplicação com o comando:
```bash
docker compose up --build
```



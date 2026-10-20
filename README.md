# Cadastro de Eletrônicos

Aplicação web para gerenciar eletrônicos e suas marcas, desenvolvida com **Java, Spring Boot e Thymeleaf**. O projeto reúne uma API REST e uma interface web para cadastrar, consultar, editar e excluir registros.

## Funcionalidades

### Eletrônicos

- Cadastro e edição de nome, categoria, cor, valor e ano.
- Listagem com busca por nome, marca ou ID e filtro por categoria.
- Resumo com quantidade de dispositivos, categorias utilizadas e valor total cadastrado.
- Página de detalhes e exclusão com confirmação.
- Valores formatados em reais e layout adaptável a diferentes tamanhos de tela.

### Marcas

- Cadastro, listagem, edição e exclusão de marcas.
- Busca por nome ou ID.
- Página de detalhes com os eletrônicos vinculados à marca.
- Confirmação de exclusão e mensagens de erro na interface.

O relacionamento permite que uma marca tenha vários eletrônicos. A associação de um eletrônico a uma marca pode ser enviada pela API; o formulário de eletrônicos atualmente mantém o vínculo existente, mas não oferece um seletor de marcas.

## Tecnologias

| Tecnologia | Uso |
| --- | --- |
| Java 21 | Linguagem do backend |
| Spring Boot 4.1.0 | Configuração e execução da aplicação |
| Spring Web MVC | API REST e controllers das páginas |
| Spring Data JPA / Hibernate | Persistência e mapeamento das entidades |
| H2 Database | Banco de dados local |
| Flyway | Migrações do banco |
| Thymeleaf | Renderização dos templates HTML |
| HTML, CSS e JavaScript | Interface responsiva e chamadas à API |
| Lombok | Redução de código repetitivo |
| Maven Wrapper | Gerenciamento de dependências e build |

## Como executar

### Pré-requisitos

- JDK 21 instalado e `JAVA_HOME` configurado.
- Git para clonar o repositório.
- Acesso à internet na primeira execução para baixar o Maven e as dependências.

Não é necessário instalar Node.js ou executar `npm install`: os arquivos do frontend são servidos pelo Spring Boot.

### 1. Clone o repositório

```bash
git clone https://github.com/PedroSinhuk/CadastroDeEletronicos.git
cd CadastroDeEletronicos
```

### 2. Configure o banco e inicie a aplicação

A aplicação lê estas variáveis de ambiente:

| Variável | Descrição | Exemplo local |
| --- | --- | --- |
| `DATABASE_URL` | URL JDBC do banco | `jdbc:h2:file:./data/eletronicos-local` |
| `DATABASE_USERNAME` | Usuário do banco | `sa` |
| `DATABASE_PASSWORD` | Senha do banco | `local-dev` |

**Primeira execução com um banco novo:** o repositório possui a migração `V2__Add_ano_tb_eletronicos.sql`, que pressupõe a existência da tabela `tb_eletronicos`, mas não possui uma migração inicial de criação. Para iniciar um banco local vazio, os comandos abaixo desativam o Flyway apenas nessa execução. O Hibernate cria as tabelas usando o `ddl-auto=update` já configurado.

**Windows — PowerShell:**

```powershell
$env:DATABASE_URL = 'jdbc:h2:file:./data/eletronicos-local'
$env:DATABASE_USERNAME = 'sa'
$env:DATABASE_PASSWORD = 'local-dev'
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--spring.flyway.enabled=false"
```

**Linux / macOS:**

```bash
export DATABASE_URL='jdbc:h2:file:./data/eletronicos-local'
export DATABASE_USERNAME='sa'
export DATABASE_PASSWORD='local-dev'
sh mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.flyway.enabled=false
```

Use esse modo para o banco local criado pelo Hibernate. Para utilizar o Flyway, é necessário compatibilizar o histórico das migrações com o esquema do banco antes de habilitá-lo. Em um banco já preparado para as migrações existentes, execute `./mvnw.cmd spring-boot:run` no Windows ou `sh mvnw spring-boot:run` no Linux/macOS.

As credenciais acima são exemplos para desenvolvimento local. Um arquivo `.env` não é carregado automaticamente pela configuração atual; defina as variáveis no terminal ou na configuração de execução da IDE.

### 3. Acesse as páginas

| Página | Endereço local |
| --- | --- |
| Catálogo de eletrônicos | [localhost:8080/eletronicos/ui/listar](http://localhost:8080/eletronicos/ui/listar) |
| Cadastro de eletrônico | [localhost:8080/eletronicos/ui/novo](http://localhost:8080/eletronicos/ui/novo) |
| Marcas | [localhost:8080/marca/ui/listar](http://localhost:8080/marca/ui/listar) |
| Cadastro de marca | [localhost:8080/marca/ui/novo](http://localhost:8080/marca/ui/novo) |
| Console H2 | [localhost:8080/h2-console](http://localhost:8080/h2-console) |

No console H2, utilize a mesma URL JDBC, usuário e senha definidos nas variáveis de ambiente.

## API REST

URL base local: `http://localhost:8080`. As requisições de criação e atualização recebem JSON com o cabeçalho `Content-Type: application/json`.

### Eletrônicos

| Método | Rota | Operação |
| --- | --- | --- |
| `POST` | `/eletronicos/criar` | Cadastrar eletrônico |
| `GET` | `/eletronicos/listar` | Listar eletrônicos |
| `GET` | `/eletronicos/listar/{id}` | Consultar por ID |
| `PUT` | `/eletronicos/alterar/{id}` | Atualizar eletrônico |
| `DELETE` | `/eletronicos/deletar/{id}` | Excluir eletrônico |

Exemplo de corpo para cadastro:

```json
{
  "nome": "Notebook de estudos",
  "tipoEletronico": "NOTEBOOK",
  "cor": "Prata",
  "valor": 3500.00,
  "ano": 2026,
  "marca": null
}
```

Para vincular uma marca existente, substitua `null` por `{"id": 1}`, usando o ID real da marca cadastrada. Ao atualizar um eletrônico, envie todos os campos que deseja manter, incluindo a marca.

Categorias disponíveis: `CELULAR`, `NOTEBOOK`, `TV`, `TABLET`, `MONITOR` e `DISPOSITIVO_DE_SOM`.

### Marcas

| Método | Rota | Operação |
| --- | --- | --- |
| `POST` | `/marca/criarMarca` | Cadastrar marca |
| `GET` | `/marca/listarMarcas` | Listar marcas |
| `GET` | `/marca/listarPorID/{id}` | Consultar por ID |
| `PUT` | `/marca/alterarID/{id}` | Atualizar marca |
| `DELETE` | `/marca/deletarID/{id}` | Excluir marca |

Exemplo de corpo para cadastro ou edição:

```json
{
  "nome": "Samsung"
}
```

Marcas com eletrônicos vinculados podem ter a exclusão impedida pela integridade referencial do banco. Desvincule ou reatribua os eletrônicos antes de excluir a marca.

## Organização do projeto

```text
src/main/
├── java/Projeto/CadastroDeEletronicos/
│   ├── CadastroDeEletronicosApplication.java
│   ├── Eletronicos/
│   │   └── Controller, ControllerUi, Service, Repository, Model, DTO e Mapper
│   └── Marca/
│       └── Controller, MarcasControllerUi, Service, Repository, Model, DTO e Mapper
└── resources/
    ├── application.properties
    ├── db/migrations/
    ├── static/
    │   ├── css/
    │   └── js/
    └── templates/
```

Os controllers REST expõem os dados da aplicação. Os controllers de UI carregam os dados para os templates Thymeleaf. O JavaScript das páginas utiliza as rotas REST para salvar e excluir registros.

## Build e testes

Com as variáveis de ambiente configuradas, execute no PowerShell:

```powershell
# Compilar
.\mvnw.cmd compile

# Executar os testes com banco temporário em memória
.\mvnw.cmd test "-Dspring.datasource.url=jdbc:h2:mem:eletronicos-test" "-Dspring.datasource.username=sa" "-Dspring.datasource.password=test" "-Dspring.flyway.enabled=false"

# Gerar o JAR sem executar os testes
.\mvnw.cmd package -DskipTests
```

No Linux/macOS, substitua `.\mvnw.cmd` por `sh mvnw`. O JAR é gerado na pasta `target/`.

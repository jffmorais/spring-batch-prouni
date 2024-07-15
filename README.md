# Projeto Spring Batch para criação da base de Dados do Prouni

Este projeto utiliza Java 21 e Spring Batch para ler arquivos CSV dos [dados abertos do Prouni](https://dadosabertos.mec.gov.br/prouni), salvar os dados em uma base de dados PostgreSQL.
## Índice

- [Introdução](#introdução)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação e Configuração](#instalação-e-configuração)
- [Executando o Projeto](#executando-o-projeto)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

## Introdução

Este projeto é a primeira parte de três módulos:
1. **Processamento Batch:** Lê múltiplos arquivos CSV com os dados abertos do PROUNI e salva em uma base PostgreSQL.
2. **API REST:** Fornece endpoints para acessar os dados processados de forma organizada.
3. **Frontend em React:** Uma aplicação frontend com geração de gráficos para visualização dos dados processados.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Batch
- PostgreSQL
- Spring Data JPA
- Gradle

## Instalação e Configuração

### Pré-requisitos

- Java 21
- PostgreSQL
- Gradle

### Passos para Configuração

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/jffmorais/spring-batch-prouni.git
   cd spring-batch-prouni
   ```

2. **Configure o banco de dados PostgreSQL:**
Crie um banco de dados e atualize as configurações no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. **Instale as dependências**
```sh
./gradlew build
```

### Executando o Projeto
1. **Execute a aplicação:**
```sh
./gradlew bootRun
```
2. **A aplicação irá iniciar e processar os arquivos CSV na pasta `input`, salvando os dados no banco de dados PostgreSQL.**

### Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

### Licença
Este projeto está licenciado sob a MIT License.

### Contato
Para mais informações, entre em contato:
- Nome: Jefferson Morais Marques
- Email: jffmorais@outlook.com
- LinkedIn: [jffmorais](https://www.linkedin.com/in/jffmorais/)
- GitHub: [jffmorais](https://github.com/jffmorais)
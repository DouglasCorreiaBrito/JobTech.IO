# JobTech.IO

Este projeto é uma WEB API responsável por controlar o server-side de um sistema de vagas de emprego para a Fatec ZL.

## Começando

Essas instruções fornecerão uma cópia do projeto em execução na sua máquina local para fins de desenvolvimento e teste.

### Pré-requisitos

O que você precisa para instalar o software e como instalá-lo

`` ``
Apache Maven: http://maven.apache.org/
`` ``
`` ``
JDK 8 : https://www.oracle.com/java/technologies/javase-jdk8-downloads.html 
`` ``
`` ``
MySQL Community Server: https://dev.mysql.com/downloads/mysql/
`` ``

### Instalando

O projeto foi criado com o Spring initializr (https://start.spring.io/) e para "instalar este projeto" basta que você adquira o código fonte (via git clone preferencialmente) e importe a pasta Jobtech em sua IDE como "Maven Project"

 - Será necessário que você dê atenção ao arquivo application.properties (que se encontra no path src/main/resources) para configurar as informações de acesso ao seu banco, exemplo:

Conector e local do seu banco:
`` ``
spring.datasource.url=jdbc:mysql://localhost:3306/dbjobtech?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&createDatabaseIfNotExist=true
`` ``

Usuário e senha de acesso ao banco:
`` ``
spring.datasource.username=meu_usuario
spring.datasource.password=minha_senha_secreta
`` ``

- Dado que você já tenha atendido aos pré-requisitos e tenha uma cópia do código com você, abra o terminal na pasta do projeto e digite:
`` ``
mvn clean install spring-boot:run
`` ``
-Se o projeto buildar, vá ao navegador e digite http://localhost:8080/swagger-ui.html#/ , você deve ver uma página parecida como a abaixo:

COLOCAR UMA FOTO DO SWAGGER AQUI


## Construído com

* [Maven] (https://maven.apache.org/) - Gerenciamento de dependências
* [Spring Boot] (https://spring.io/projects/spring-boot) - Redução de configurações Spring
* [Spring Data] (https://spring.io/projects/spring-data) - Acesso a dados
* [Spring Security] (https://spring.io/projects/spring-security) - Controle de autenticação e acesso
* [Spring MVC] (https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) - Implementação de contêineres para Servlet


## Contribuindo

Leia [CONTRIBUTING.md] para obter detalhes sobre nosso código de conduta e o processo para enviar solicitações pull para nós.

## Controle de versão

Usamos Git e GitHUb para versionar. Para as versões disponíveis, consulte as [tags neste repositório] (https://github.com/DouglasCorreiaBrito/JobTech.IO/tags).

## Autores

* ** Douglas Correia ** - * Trabalho inicial * -(https://github.com/DouglasCorreiaBrito)
* ** Gustavo José    ** - * Trabalho inicial * -(https://github.com/gustuxd)
* ** Daniela Ramo    ** - * Trabalho inicial * -(https://github.com/Daniramo)

Veja também a lista de [colaboradores] (https://github.com/your/project/contributors) que participaram deste projeto.

## Licença

Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE.md] (LICENSE.md) para obter detalhes

## Agradecimentos

* Gorjeta para qualquer pessoa cujo código foi usado
* Inspiração
* etc

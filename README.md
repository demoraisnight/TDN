# Sistema de Compra de Ingressos para um Cinema

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

Este é um sistema de compra de ingressos para um cinema, desenvolvido para atender as necessidades de um site que consome essa aplicação. O sistema possui duas áreas principais: a do funcionário e a do cliente. O funcionário pode cadastrar filmes, excluir filmes e listar os filmes em cartaz, enquanto o cliente pode comprar ingressos, listar os filmes em cartaz de acordo com a classificação indicativa, visualizar os ingressos comprados e fazer logout no sistema.
## Table of Contents

- [Features](#features)
- [Conceptual Model](#modelo-conceitual)
- [Installation](#instalação)
- [Usage](#usage)
- [Some Endpoints](#some-endpoints)
- [Technologies Used](#tecnologias-usadas)
- [Passo a passo caso queira JWT](#passo-a-passo-caso-queira-implementar-a-autenticação-usando-jwt)
- [License](#license)

## Features

- Autenticação de usuário e controle de acesso baseado em papéis(Cliente, Funcionário)
- Gerenciamento de Filmes: Adicione, liste, e delete filmes.
- Gerenciamento de acessos: login, logout e criação de novos usuários e acessos.
- Filmes são listados conforme a classificação indicativa do filme.
- Gerenciamento de ingressos: liste e compre ingressos.
- Filmes com ingressos vendidos não podem ser excluídos.

## Modelo Conceitual

![Conceptual Model](cinemaProject.png)

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/demoraisnight/TDN.git

2. Set up the database:
    - Run POSTGRES container
        ```bash
        sudo docker run -p 5432:5432 --name meu-container-pg12 -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=minha_base_2 postgres:12-alpine
    - Download and Install [pgAdmin4](https://www.pgadmin.org/download/pgadmin-4-apt/)
    - Run [ddl.sql](ddl.sql) on your database using pgAdmin.
3. Run the project:
    1. Run via Command Line
    ```bash
      mvn package
      java -jar target/cinema-0.0.1-SNAPSHOT.jar
    ```
    2. Running using and IDE
    - [SchoolApplication](/src/main/java/com/vinicius/cinema/CinemaApplication.java)
## Usage
- Baixe a coleção do postman [Cinema.postman](Cinema.postman_collection.json)
- Execute o projeto
- Crie um usuário ou faça login com os já registrados na aba Cadrastro e Login/Logout
- Se entrou como cliente os endpoints se encontram na aba Cliente, caso contrário na aba Funcionário.
- Para entender melhor os endpoints a documentação em html produzida com o swagger(openapi) se encontra em http://localhost:8080/swagger-ui/index.html

## Some Endpoints
| Endpoint           | Method | Description                      |
|--------------------|--------|----------------------------------|
| `/acesso`          | POST   | Retrieve all users               |                                                                                             |
| `/filme`           | GET    | Lista os filmes                  |                                                                                            |  
| `/filme`           | POST   | Cria um filme                    | 
| `/filme/{idFilme}` | PUT    | Compra ingressos                 | 
| `/filme/{id}`      | DELETE | Remove um filme por id           |                                                                                        |
| `/ingressos`       | GET    | Lista os ingressos de um usuário |                                                                                             |


## Tecnologias Usadas
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Starter Test
- JUnit
- Spring Validations
- Docker
- PostgreSQL
- OpenAPI(swagger)


## Passo a passo caso queira implementar a autenticação usando JWT
O app foi concebido usando sessions, mas também criei uma forma de gerar um JWT, caso seja necessário.
- Descomente todas classes dentro de com/vinicius/cinema/config/jwt
- Remova os decorators Configuration e EnableMethodSecurity da classe WebSecurityConfigV2 que esta em com/vinicius/cinema/config
- Você pode conseguir o seu token de acesso nesse endpoint oauth/token usando essas credenciais
  #### App crendentials
        - client_id:myclientid
        - client_secret:myclientsecret
  #### User crendentials
        - client_id:RobertaFernandes
        - client_secret:123456
        - grant_type:password
- Com o seu token em mãos pode configurar a autorização no postman como:
`Authorization: Bearer <access_token>`
- E inserir o seu token.
- Obs: Não criei uma forma de logout utilizando o token, ou seja só quando o tempo de expiração dele chegar ao final que o logout será feito.
- Obs2: Pode ser feita uma implementação de BlackListing com o token para resolver o problema de logout, que consiste em armazenar o token do usuário em um banco de dados no momento do logout. Assim, toda próxima requisição terá que checar se o token usado não esta na lista. 
## License
This project is licensed under the MIT License. See the LICENSE file for details.
## API RESTful para Sistema de Usuários de Carros
Criar aplicação que exponha uma API RESTful de criação de usuários e carros com login. Este projeto é para resolver o desafio proposto pela Pitang para ingressar na empresa.

# Desafio Pitang - Reserva de Veículos
<div style="display: inline_block"><br>
  <img align="center" alt="mguedesmelo-java" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg">
  <img align="center" alt="mguedesmelo-angular" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/angularjs/angularjs-plain.svg">
  <img align="center" alt="mguedesmelo-Js" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/javascript/javascript-plain.svg">
  <img align="center" alt="mguedesmelo-Ts" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/typescript/typescript-plain.svg">
  <img align="center" alt="mguedesmelo-HTML" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/html5/html5-original.svg">
  <img align="center" alt="mguedesmelo-CSS" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original.svg">
</div>

## 💻 Tecnologias
* [Java](https://www.java.com/) - Linguagem Backend
* [Angular](https://angular.io/) - Linguagem Frontend
* [Spring](https://spring.io/) - Framework Web Java
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework Initializer
* [Hibernate](http://hibernate.org/orm/) - ORM
* [Tomcat](http://tomcat.apache.org/) - Servlet Container
* [Swagger](https://swagger.io/) - Gerenciador de Documentação e Testes Funcionais
* [H2 Database](http://www.h2database.com) - SGBD
* [Spring Tools 4 for Eclipse](https://spring.io/tools) - IDE

### ⌨️ Instalação
1. Clone o repositório para baixar as pastas contendo o código fonte dos projeto Java e Angular.

```
git clone https://github.com/mguedesmelo/desafio-pitang.git
```
2. Importe o projeto "car-rental-spring" no Eclipse STS como um projeto maven e execute como uma aplicação Spring Boot

3. Abra a pasta "car-rental-angular" do projeto Angular no Visual Studio Code

É necessário ter o Node.js / NPM instalados localmente.

Execute o projeto:
```
npm run start
```
Abra o browser e acesse o endereço **http://localhost:4200** (porta padrão do Angular).


## ✅ Atributos das Classes

**Usuário**
| Nome | Tipo | Descrição |
| ------ | ------ | ------ |
| firstName | String | Nome do usuário |
| lastName | String | Sobrenome do usuário |
| email | String | E-mail do usuário |
| birthday | Date | Data de nascimento do usuário |
| login | String | Login do usuário |
| password | String | Senha do usuário |
| phone | String | Telefone do usuário |
| cars | List | Lista de carros do usuário |

**Carro**
| Nome | Tipo | Descrição |
| ------ | ------ | ------ |
| year | Int | Ano de fabricação do carro |
| licensePlate | String | Placa do carro |
| model | String | Modelo do carro |
| color | String | Cor predominante do carro |

## 🚧 Rotas

**Rotas que **NÃO** exigem autenticação**

| Rota | Descrição | Tipo | Erros possíveis |
| ------ | ------ | ------ | ------ |
| /api/signin | Esta rota espera um objeto com os campos login e password, e deve retornar o token de acesso da API (JWT) com as informações do usuário logado. | GET | 1 |
| /api/users | Listar todos os usuários | GET | |
| /api/users | Cadastrar um novo usuário | POST | 2,3,4,5 |
| /api/users/{id} | Buscar um usuário pelo id | GET | |
| /api/users/{id} | Remover um usuário pelo id | DELETE | |
| /api/users/{id} | Atualizar um usuário pelo id | PUT | 2,3,4,5 |

**Erros possíveis:**
1. Login inexistente ou senha inválida: retornar um erro com a mensagem “Invalid login or password”;
2. E-mail já existente: retornar um erro com a mensagem “Email already exists”;
3. Login já existente: retornar um erro com a mensagem “Login already exists”;
4. Campos inválidos: retornar um erro com a mensagem “Invalid fields”;
5. Campos não preenchidos: retornar um erro com a mensagem “Missing fields”.

**Exemplo de JSON para criação do usuário:**
```
{
  "firstName": "Hello",
  "lastName": "World",
  "email": "hello@world.com",
  "birthday": "1990-05-01",
  "login": "hello.world",
  "password": "h3ll0",
  "phone": "988888888",
  "cars": [
    {
      "year": 2018,
      "licensePlate": "PDV-0625",
      "model": "Audi",
      "color": "White"
    }
  ]
}
```

**Rotas que exigem autenticação**

| Rota | Descrição | Erros possíveis |
| ------ | ------ | ------ |
| /api/me | Retornar as informações do usuário logado (firstName, lastName, email, birthday, login, phone, cars) + createdAt (data da criação do usuário) + lastLogin (data da última vez que o usuário realizou login) | GET | 1,2 |
| /api/cars | Listar todos os carros do usuário logado | GET | 1,2 |
| /api/cars | Cadastrar um novo carro para o usuário logado | POST | 1,2,3,4,5 |
| /api/cars/{id} | Buscar um carro do usuário logado pelo id | GET | 1,2 |
| /api/cars/{id} | Remover um carro do usuário logado pelo id | DELETE | 1,2 |
| /api/cars/{id} | Atualizar um carro do usuário logado pelo id | PUT | 1,2,3,4,5 |

**Erros possíveis:**
1. Token não enviado: retornar um erro com a mensagem “Unauthorized”;
2. Token expirado: retornar um erro com a mensagem “Unauthorized - invalid session”;
3. Placa já existente: retornar um erro com a mensagem “License plate already exists”;
4. Campos inválidos: retornar um erro com a mensagem “Invalid fields”;
5. Campos não preenchidos: retornar um erro com a mensagem “Missing fields”.


**Exemplo de JSON para criação do carro:**
```
{
  "year": 2018,
  "licensePlate": "PDV-0625",
  "model": "Audi",
  "color": "White"
}
```

## Para mais detalhes e efetuar os testes, basta executar o Swagger do projeto pelo endereço:
```
http://localhost:8080/swagger-ui.html
```

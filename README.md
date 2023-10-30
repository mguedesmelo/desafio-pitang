# Desafio Pitang - Reserva de Veículos
Criar aplicação que exponha uma API RESTful de criação de usuários e carros com login. Este projeto é para resolver o desafio proposto pela Pitang para ingressar na empresa.

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

## ⌨️ Instalação
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


## ✅Atributos das Classes

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

**Rotas que **NÃO EXIGEM** autenticação**

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

**Rotas que **EXIGEM** autenticação**

| Rota | Descrição | Tipo | Erros possíveis |
| ------ | ------ | ------ | ------ |
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


# ESTÓRIAS DE USUÁRIO
Mini Sprint: Semana de 24 a 27 de Outubro de 2023

Objetivo da Sprint: Desenvolver funcionalidades básicas do backend e frontend.

**1. História de Usuário 1: Sign In**
- **Eu como** usuário do sistema
- **Quero** fazer login na API com meu login e senha
- **Para que** eu possa acessar os recursos protegidos da API

*Critérios de Aceitação:*
- A API deve fornecer um endpoint de login para autenticar usuários.
- Deve ser possível enviar uma solicitação para o endpoint de signin com um login e senha válidos.
- Em caso de login bem-sucedido, a API deve retornar um token de autenticação.
- Em caso de falha no login, a API deve retornar uma mensagem de erro.

**2. História de Usuário 2: Listar Usuários**
- **Eu como** usuário do sistema
- **Quero** listar todos os usuários cadastrados na API
- **Para que** eu possa visualizar informações sobre os usuários na API

*Critérios de Aceitação:*
- A API deve fornecer um endpoint para listar todos os usuários cadastrados.
- A lista de usuários deve ser retornada como uma resposta JSON.
- Os usuários devem ser retornados como objetos JSON com informações como nome, sobrenome, e-mail, etc.

**3. História de Usuário 3: Cadastrar Novo Usuário**
- **Eu como** usuário do sistema
- **Quero** cadastrar um novo usuário na API informando seus dados
- **Para que** o novo usuário tenha acesso à API

*Critérios de Aceitação:*
- A API deve fornecer um endpoint para cadastrar um novo usuário.
- Deve ser possível enviar uma solicitação para o endpoint de cadastro com os dados do novo usuário em formato JSON.
- A API deve validar os dados inseridos e retornar uma resposta com status apropriado.
- Em caso de falha no cadastro, a API deve retornar uma mensagem de erro.

**4. História de Usuário 4: Buscar Usuário pelo ID**
- **Eu como** usuário do sistema
- **Quero** buscar um usuário pelo seu ID na API
- **Para que** eu possa visualizar os detalhes de um usuário específico

*Critérios de Aceitação:*
- A API deve fornecer um endpoint para buscar um usuário pelo seu ID.
- Deve ser possível enviar uma solicitação para o endpoint com o ID do usuário.
- Se o usuário for encontrado, a API deve retornar os detalhes do usuário em formato JSON.
- Se nenhum usuário for encontrado com o ID fornecido, a API deve retornar um status apropriado.

**5. História de Usuário 5: Remover Usuário pelo ID**
- **Eu como** usuário do sistema
- **Quero** remover um usuário pelo seu ID na API
- **Para que** eu possa desativar ou excluir usuários da API

*Critérios de Aceitação:*
- A API deve fornecer um endpoint para remover um usuário pelo seu ID.
- Deve ser possível enviar uma solicitação DELETE para o endpoint com o ID do usuário.
- Após a remoção, o usuário deve ser desativado ou removido do sistema.
- A API deve retornar um status apropriado após a remoção.

**6. História de Usuário 6: Atualizar Usuário pelo ID**
- **Eu como** usuário do sistema
- **Quero** atualizar os detalhes de um usuário existente pelo seu ID na API
- **Para que** eu possa corrigir informações ou fazer atualizações necessárias

*Critérios de Aceitação:*
- A API deve fornecer um endpoint para atualizar os detalhes de um usuário pelo seu ID.
- Deve ser possível enviar uma solicitação PUT para o endpoint com os dados atualizados em formato JSON.
- A API deve validar os dados atualizados e retornar uma resposta com status apropriado.
- Após a atualização bem-sucedida, o usuário deve poder usar as informações atualizadas para acessar a API.


# SOLUÇÃO

A escolha das tecnologias para o desenvolvimento de um sistema, como Java no backend e Angular no frontend, foi ser baseada em diversos fatores técnicos que podem influenciar no desempenho, manutenção, escalabilidade e sucesso do projeto. Vamos justificar essa decisão tecnicamente:

**Java no Backend:**

1. **Ampla Comunidade e Ecossistema:** Java é uma das linguagens de programação mais populares e amplamente adotadas no mundo. Isso resulta em uma grande comunidade de desenvolvedores, vasta documentação e uma ampla gama de bibliotecas e frameworks disponíveis. Essa riqueza de recursos facilita o desenvolvimento e a solução de problemas.

2. **Robustez e Confiabilidade:** Java é conhecido por sua robustez e confiabilidade. Ele é altamente tolerante a falhas e oferece um ambiente de execução seguro. Essas características são essenciais para sistemas críticos que precisam estar sempre disponíveis e funcionando corretamente.

3. **Desempenho:** Java possui um bom desempenho, principalmente quando combinado com otimizações de JVM (Java Virtual Machine). A capacidade de escalabilidade vertical e horizontal também é uma vantagem, permitindo que a aplicação cresça com o aumento da demanda.

4. **Segurança:** Java oferece recursos de segurança avançados, como controle de acesso, autenticação e autorização. Isso é fundamental para proteger os dados e garantir a conformidade com regulamentações de segurança.

5. **Integração:** Java suporta integração fácil com outros sistemas e serviços, tornando-o uma escolha sólida para aplicativos empresariais que precisam se comunicar com sistemas legados ou outros serviços web.

**Angular no Frontend:**

1. **Framework Robusto e Estruturado:** Angular é um framework JavaScript mantido pelo Google que oferece uma estrutura robusta e bem organizada para o desenvolvimento de aplicações web. Ele segue os princípios de arquitetura MVC (Model-View-Controller) e promove boas práticas de desenvolvimento.

2. **Componentização:** Angular utiliza o conceito de componentes reutilizáveis, o que facilita a construção e a manutenção de interfaces de usuário complexas. Cada componente é independente e pode ser reutilizado em várias partes do aplicativo.

3. **SPA (Single-Page Application):** Angular é ideal para o desenvolvimento de SPAs, onde a navegação ocorre sem a necessidade de recarregar a página. Isso melhora a experiência do usuário, tornando o aplicativo mais rápido e responsivo.

4. **TypeScript:** Angular é escrito em TypeScript, uma linguagem superset do JavaScript que oferece recursos de tipagem estática e melhor autocompletamento, o que ajuda a evitar erros em tempo de execução e a facilitar o desenvolvimento.

5. **Ferramentas e Ecossistema:** Angular possui um ecossistema de ferramentas e bibliotecas complementares, como o Angular CLI, que simplificam o desenvolvimento, a construção e o teste de aplicações. Além disso, ele se integra facilmente com outras tecnologias.

6. **Suporte a Testes:** Angular inclui suporte integrado para testes, o que torna mais fácil e eficaz realizar testes unitários, de integração e de ponta a ponta para garantir a qualidade do código.

Em resumo, a escolha de Java no backend e Angular no frontend oferece um ambiente técnico robusto e escalável para o desenvolvimento de aplicações web. Java é uma escolha madura e segura para o backend, enquanto Angular fornece uma estrutura de desenvolvimento estruturada e moderna para o frontend. Essas tecnologias trabalham bem juntas e podem resultar em uma experiência de desenvolvimento eficiente e um sistema web confiável.


# BACKLOG

**Backend**
- [x] /api/signin (GET) - Login de acesso
- [x] /api/users (GET) - Listar todos os usuários
- [x] /api/users (POST) - Cadastrar um novo usuário
- [x] /api/users/{id} (GET) - Buscar um usuário pelo id
- [x] /api/users/{id} (DELETE) - Remover um usuário pelo id
- [x] /api/users/{id} (PUT) - Atualizar um usuário pelo id
- [x] /api/users/upload/{id} (POST) - Enviar foto
- [x] /api/users/image/{id} (GET) - Obter foto do usuário
- [x] /api/me (GET) - Retornar as informações do usuário logado
- [x] /api/cars (GET) - Listar todos os carros do usuário logado
- [x] /api/cars (POST) - Cadastrar um novo carro para o usuário logado
- [x] /api/cars/{id} (GET) - Buscar um carro do usuário logado pelo id
- [x] /api/cars/{id} (DELETE) - Remover um carro do usuário logado pelo id
- [x] /api/cars/{id} (PUT) - Atualizar um carro do usuário logado pelo id
- [x] Testes unitários de autenticação
- [x] Testes unitários do controlador de usuários
- [ ] Testes unitários do controlador de carros

**Frontend**
- [ ] Tela principal com menu dinâmico
- [ ] Login de acesso
- [x] Listagem de usuários
- [x] Tela de novo usuário
- [x] Salvar novo usuário
- [ ] Tela de alteração de usuário
- [ ] Alterar usuário
- [ ] Remover usuário
- [ ] Enviar foto do usuário
- [ ] Listagem de carros de um usuário
- [ ] Tela de novo carro
- [ ] Salvar novo carro
- [ ] Tela de alteração de carro
- [ ] Alterar carro
- [ ] Remover carro
- [ ] Detalhar carro
- [ ] Enviar foto do carro
- [ ] Requisito extra (bonus stage)

# dataa
Projeto Eleição

Projeto de uma aplicação de Eleicões para empresas e entidades de previdência complementar utilizando as tecnologias `Thymeleaf`, `Spring boot` e `Hibernate` como forma de objetivo para que essas empresas possam gerenciar suas eleições aos cargos a serem preenchidos por candidatos. O projeto é desenvolvido com base no curso **CRIANDO APLICAÇÕES WEB COM SPRING BOOT** ministrados pela [Devmedia](https://www.devmedia.com.br) || link curso(https://www.devmedia.com.br/curso/curso-spring-boot/2185).

Configurando o projeto e banco de dados.
----------------------------------------

O banco de dados esta configurado `MySql Server`, mas como se trata de uma aplicação com `Hibernate`, você pode configura-lo para trabalhar com qualquer outro banco de dados, basta acessar o arquivo `application.yml` dentro de "src\main\resources\paconte default\application.yml.  

Os passos básicos são:

1. Importe o projeto no Netbeans(https://netbeans.org/downloads/8.0.2/), Intellij Idea(https://www.jetbrains.com/idea/download/#section=windows) ou sua IDE de preferencia.
2. Confiure o arquivo `application.yml` caso seu banco de dados padrão não seja o `MySql Server`. 
3. Faça o deploy no `Apache Tomcat 6.x` e inicie o servidor;
4. Acesse a aplicação através da url [http://localhost:8080/](http://localhost:8080/) ;
8. Crie uma nova eleiçaõ;
9. Cadastre os cargos relacionados;
9. Cadastre os candidatos relacionados;
10. Cadastre os Eleitores;
11. Acesse no painel dentro de eleitores no botão `Area de eleitor`;
12. Escolha os candidatos concorrentes as vagas e clique votar;
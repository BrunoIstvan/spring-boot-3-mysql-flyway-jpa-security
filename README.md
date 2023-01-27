## Reparando banco de dados

Para acessar o console do H2

    http://localhost:8080/h2-console

    User: sa
    Password: password

Para resolver esse problema será necessário acessar o banco de dados da aplicação e executar o seguinte comando sql:

    delete from flyway_schema_history where success = 0;



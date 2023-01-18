## Reparando banco de dados

Para resolver esse problema será necessário acessar o banco de dados da aplicação e executar o seguinte comando sql:

    delete from flyway_schema_history where success = 0;
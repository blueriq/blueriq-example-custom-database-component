[![][logo]][website] 

# About

Since Blueriq 11.5, we added multi-tenant support and as a result we changed the way Blueriq initializes datasources.
The plugin `blueriq-example-custom-database-component` is an example on how to set up a component with the Blueriq API which has database access. No rights reserved.

# Build from source

To compile and build war use:

```bash
mvn clean verify
```

# Run example

Deploy `Runtime.war` to Tomcat container. Create a configuration folder and add Blueriq `license.aql` or package Blueriq `license.aql` by adding it to `src\main\resources`.
Start Tomcat container with the following parameters:

```bash
-Dspring.config.additional-location=file://path_to_conf/ # URI of the configuration folder which contains the Blueriq license.
-Dspring.profiles.active=native,development-tools,custom-database-component (embedded) or
-Dspring.profiles.active=native,development-tools,custom-database-component,externaldatasources (direct connection) or 
-Dspring.profiles.active=native,development-tools,custom-database-component,jndidatasources (JNDI)
```

Configure the datasource via either the externaldatasources profile or the jndidatasources profile.

## externaldatasources profile

*application-externaldatasources.properties*

```bash
blueriq.datasource.custom-database-component.url=jdbc:sqlserver://<database_url>:<port>;databaseName=<databaseName>;instance=<instanceName>
blueriq.datasource.custom-database-component.username=<username>
blueriq.datasource.custom-database-component.password=<password>
blueriq.datasource.custom-database-component.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
blueriq.hibernate.custom-database-component.hbm2ddl.auto=validate
blueriq.hibernate.custom-database-component.dialect=org.hibernate.dialect.SQLServer2012Dialect
```

## jndidatasources profile

*application-jndidatasources.properties*

```bash
blueriq.datasource.custom-database-component.jndiName=java:/comp/env/jdbc/customDatabaseComponent
blueriq.hibernate.custom-database-component.hbm2ddl.auto=validate
blueriq.hibernate.custom-database-component.dialect=org.hibernate.dialect.SQLServer2012Dialect
```

## Supported dialects

- org.hibernate.dialect.SQLServer2012Dialect (for SQL Server)
- org.hibernate.dialect.Oracle10gDialect (for Oracle)
- org.hibernate.dialect.H2Dialect (for H2)

## Supported hbm2ddl.auto

- none
- validate

## Multi-tenant setup

See https://my.blueriq.com/display/DOC/Multi-tenant+setup for full example.

*application-externaldatasources.properties*

```bash
blueriq.datasource.custom-database-component.tenants.tenant1.url=jdbc:sqlserver://<database_url>:<port>;databaseName=<databaseName>;instance=<instanceName>
blueriq.datasource.custom-database-component.tenants.tenant1.username=<username>
blueriq.datasource.custom-database-component.tenants.tenant1.password=<password>
blueriq.datasource.custom-database-component.tenants.tenant1.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
blueriq.hibernate.custom-database-component.hbm2ddl.auto=validate
blueriq.hibernate.custom-database-component.dialect=org.hibernate.dialect.SQLServer2012Dialect
```

*application-jndidatasources.properties*

```bash
blueriq.datasource.custom-database-component.tenants.tenant1.jndiName=java:/comp/env/jdbc/customDatabaseComponent
blueriq.hibernate.custom-database-component.hbm2ddl.auto=validate
blueriq.hibernate.custom-database-component.dialect=org.hibernate.dialect.SQLServer2012Dialect
```

## Database scripts

Database scripts can be found in `dbscripts`.

[logo]: https://www.blueriq.com/wp-content/uploads/2018/07/BLUERIQ-rgb-logo-kleur-gradient-PNG-300x111.png
[website]: http://www.blueriq.com

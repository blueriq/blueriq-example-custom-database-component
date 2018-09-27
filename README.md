[![][logo]][website] 

# About

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
-Dspring.profiles.active=native,development-tools,custom-database-component
```

[logo]: https://www.blueriq.com/wp-content/uploads/2018/07/BLUERIQ-rgb-logo-kleur-gradient-PNG-300x111.png
[website]: http://www.blueriq.com

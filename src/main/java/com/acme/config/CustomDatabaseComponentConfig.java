package com.acme.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(CustomDatabaseComponentConfig.PROFILE_NAME)
@ComponentScan(basePackages = { "com.acme" })
public class CustomDatabaseComponentConfig {
  public static final String PROFILE_NAME = "custom-database-component";
  public static final String ENTITY_BASE_PACKAGE = "com.acme.domain";
  public static final String DATA_SOURCE_NAME = "customDatabaseComponentDataSource";
  public static final String SESSION_FACTORY_NAME = "customDatabaseComponentSessionFactory";
  public static final String TRANSACTION_MANAGER_NAME = "customDatabaseComponentTransactionManager";
}

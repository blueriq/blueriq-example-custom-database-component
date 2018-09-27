package com.acme.data;

import com.blueriq.component.api.datasource.EmbeddedCondition;

import com.acme.config.CustomDatabaseComponentConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Conditional(EmbeddedCondition.class)
public class CustomDatabaseComponentEmbeddedConfig {

  @Bean(name = CustomDatabaseComponentConfig.TRANSACTION_MANAGER_NAME)
  public HibernateTransactionManager commentsSqlStoreTransactionManager(
      @Qualifier(CustomDatabaseComponentConfig.SESSION_FACTORY_NAME) SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }

  @Bean(name = CustomDatabaseComponentConfig.SESSION_FACTORY_NAME)
  public LocalSessionFactoryBean commentsSqlStoreSessionFactory(
      @Qualifier(CustomDatabaseComponentConfig.DATA_SOURCE_NAME) DataSource dataSource) {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setPackagesToScan(CustomDatabaseComponentConfig.ENTITY_BASE_PACKAGE);
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  private Properties hibernateProperties() {
    Properties result = new Properties();
    result.setProperty("hibernate.hbm2ddl.auto", "create");
    result.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    return result;
  }

  @Bean(name = CustomDatabaseComponentConfig.DATA_SOURCE_NAME)
  public DataSource commentsSqlStoreDataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
  }

}

package com.acme.data;

import com.blueriq.component.api.datasource.DataSourceProfile;
import com.blueriq.component.api.datasource.DatasourceProperties;
import com.blueriq.component.api.datasource.DatasourcePropertiesUtil;
import com.blueriq.component.api.datasource.TenantAwareRoutingSource;
import com.blueriq.component.api.tenant.CurrentTenantHelper;

import com.acme.config.CustomDatabaseComponentConfig;
import com.acme.properties.CustomDatabaseComponentMultiTenantDatasourceProperties;
import com.acme.properties.CustomDatabaseComponentSingleTenantDatasourceProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Profile(DataSourceProfile.JNDI_DATASOURCE_PROFILE_NAME)
public class CustomDatabaseComponentJndiConfig {

  @Autowired
  private ConfigurableEnvironment configurableEnv;

  @Autowired(required = false)
  private CustomDatabaseComponentSingleTenantDatasourceProperties singleTenantDatasourceProperties;

  @Autowired(required = false)
  private CustomDatabaseComponentMultiTenantDatasourceProperties multiTenantDatasourceProperties;

  @Bean(name = CustomDatabaseComponentConfig.TRANSACTION_MANAGER_NAME)
  public HibernateTransactionManager commentsSqlStoreTransactionManager(
      @Qualifier(CustomDatabaseComponentConfig.SESSION_FACTORY_NAME) SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }

  @RefreshScope
  @Bean(name = CustomDatabaseComponentConfig.SESSION_FACTORY_NAME)
  public LocalSessionFactoryBean commentsSqlStoreSessionFactory(
      @Qualifier(CustomDatabaseComponentConfig.DATA_SOURCE_NAME) DataSource dataSource) {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setPackagesToScan(CustomDatabaseComponentConfig.ENTITY_BASE_PACKAGE);
    sessionFactory.setHibernateProperties(
        DatasourcePropertiesUtil.getHibernateProperties(configurableEnv, CustomDatabaseComponentConfig.PROFILE_NAME));
    return sessionFactory;
  }

  @RefreshScope
  @Bean(name = CustomDatabaseComponentConfig.DATA_SOURCE_NAME)
  @ConditionalOnProperty(prefix = "blueriq.multi-tenancy", name = "enabled", havingValue = "false",
      matchIfMissing = true)
  public DataSource commentsSqlStoreDataSource() {
    return singleTenantDatasourceProperties.getCustomDatabaseComponent().buildJndiDataSource();
  }

  @RefreshScope
  @Bean(name = CustomDatabaseComponentConfig.DATA_SOURCE_NAME)
  @ConditionalOnProperty(prefix = "blueriq.multi-tenancy", name = "enabled", havingValue = "true")
  public DataSource commentsSqlStoreMultitenantDataSource() {
    Map<String, DatasourceProperties> tenants = multiTenantDatasourceProperties.getTenants();

    if (tenants.isEmpty()) {
      throw new IllegalStateException(
          String.format("No tentants configured for '%s'", CustomDatabaseComponentConfig.PROFILE_NAME));
    }

    Map<Object, Object> targetDataSources = new HashMap<>();
    for (Map.Entry<String, DatasourceProperties> tenant : tenants.entrySet()) {
      DatasourceProperties tenantDatasource = tenant.getValue();
      targetDataSources.put(tenant.getKey(), tenantDatasource.buildJndiDataSource());
    }

    AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();
    dataSource.setTargetDataSources(targetDataSources);
    dataSource.afterPropertiesSet();

    CurrentTenantHelper.setTenantName(tenants.keySet().iterator().next());

    return dataSource;
  }
}

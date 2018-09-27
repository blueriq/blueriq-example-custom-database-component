package com.acme.properties;

import com.blueriq.component.api.datasource.DatasourceProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties(prefix = CustomDatabaseComponentSingleTenantDatasourceProperties.PROPERTY_PREFIX)
public class CustomDatabaseComponentSingleTenantDatasourceProperties {
  public static final String PROPERTY_PREFIX = "blueriq.datasource";

  private DatasourceProperties customDatabaseComponent;

  public DatasourceProperties getCustomDatabaseComponent() {
    return customDatabaseComponent;
  }

  public void setCustomDatabaseComponent(DatasourceProperties customDatabaseComponent) {
    this.customDatabaseComponent = customDatabaseComponent;
  }
}

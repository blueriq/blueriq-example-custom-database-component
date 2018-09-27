package com.acme.properties;

import com.blueriq.component.api.datasource.DatasourceProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@Component
@ConfigurationProperties(prefix = CustomDatabaseComponentMultiTenantDatasourceProperties.PROPERTY_PREFIX)
public class CustomDatabaseComponentMultiTenantDatasourceProperties {
  public static final String PROPERTY_PREFIX = "blueriq.datasource.custom-database-component";

  private Map<String, DatasourceProperties> tenants = new HashMap<>();

  public Map<String, DatasourceProperties> getTenants() {
    return tenants;
  }

  public void setTenants(Map<String, DatasourceProperties> tenants) {
    this.tenants = tenants;
  }
}

package com.blueriq;

import com.aquima.web.boot.RootConfig;

import com.acme.config.CustomDatabaseComponentConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class WarApplication extends AbstractWarApplication {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return RootConfig.configure(application).sources(CustomDatabaseComponentConfig.class);
  }
}

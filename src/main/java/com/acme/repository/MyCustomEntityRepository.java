package com.acme.repository;

import com.acme.config.CustomDatabaseComponentConfig;
import com.acme.domain.MyCustomEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MyCustomEntityRepository {

  private final SessionFactory sessionFactory;

  @Autowired
  public MyCustomEntityRepository(
      @Qualifier(CustomDatabaseComponentConfig.SESSION_FACTORY_NAME) SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public MyCustomEntity save(MyCustomEntity entity) {
    sessionFactory.getCurrentSession().saveOrUpdate(entity);

    return entity;
  }
}

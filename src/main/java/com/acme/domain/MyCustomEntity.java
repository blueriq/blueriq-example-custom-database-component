package com.acme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = MyCustomEntity.TABLE)
@SuppressWarnings("PMD.UnusedPrivateField") // MvE: Not considered important for a JPA class
public class MyCustomEntity {

  // Table name
  public static final String TABLE = "custom_entity";

  // Column names
  public static final String ID = "id";
  public static final String DESCRIPTION = "description";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SG_custom_entity")
  @SequenceGenerator(name = "SG_custom_entity", sequenceName = "s_custom_entityid", initialValue = 1,
      allocationSize = 1)
  @Column(name = ID)
  private long id;

  @Column(name = DESCRIPTION, length = 150)
  private String description;
}

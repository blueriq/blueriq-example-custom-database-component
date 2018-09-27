/** 
 * Create Tables 
 */ 
CREATE TABLE custom_entity
  (
     id          BIGINT NOT NULL,
     description NVARCHAR2(150),
 	 CONSTRAINT pk_custom_entity PRIMARY KEY (id)
  );

/**
 * Create sequences
 */
CREATE SEQUENCE s_custom_entityid START WITH 1 INCREMENT BY 1 MINVALUE 0;

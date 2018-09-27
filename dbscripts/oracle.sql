-- *********************
-- *** Create Tables ***
-- *********************

CREATE TABLE custom_entity
  ( 
     id          NUMBER(19, 0) NOT NULL, 
     description NVARCHAR2(150),
	 
     CONSTRAINT pk_custom_entity PRIMARY KEY (
	   id
	 ) 
  )
/

-- ************************
-- *** Create sequences ***
-- ************************
CREATE SEQUENCE s_custom_entityid MINVALUE 0 START WITH 1 INCREMENT BY 1;
/

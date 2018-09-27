-- **********************
-- *** Drop sequences ***
-- **********************
DECLARE
  COUNT_SEQUENCES INTEGER;
BEGIN
  SELECT COUNT(*) INTO COUNT_SEQUENCES
    FROM USER_SEQUENCES
    WHERE SEQUENCE_NAME = upper('s_custom_entityid');
    
  IF COUNT_SEQUENCES > 0 THEN
    EXECUTE IMMEDIATE 'DROP SEQUENCE s_custom_entityid';
  END IF;
END;
/

-- *******************
-- *** Drop tables ***
-- *******************
DECLARE
  COUNT_TABLES INTEGER;
BEGIN
  SELECT COUNT(*) INTO COUNT_TABLES
    FROM USER_TABLES
    WHERE TABLE_NAME = upper('custom_entity');
    
  IF COUNT_TABLES > 0 THEN
    EXECUTE IMMEDIATE 'DROP TABLE custom_entity CASCADE CONSTRAINTS';
  END IF;
END;
/

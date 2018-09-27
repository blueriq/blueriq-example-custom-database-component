/**
 * Drop sequences
 */
IF Object_id('s_custom_entityid', 'SO') IS NOT NULL
	DROP SEQUENCE [s_custom_entityid];

/** 
 * Drop tables
 */
IF Object_id('custom_entity', 'U') IS NOT NULL
	DROP TABLE [custom_entity];

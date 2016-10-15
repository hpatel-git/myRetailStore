CREATE SCHEMA IF NOT EXISTS `my_retail_store_ums` ;
USE `my_retail_store_ums` ;
 
CREATE TABLE IF NOT EXISTS users (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

CREATE TABLE IF NOT EXISTS authorities (
  username varchar(256),
  authority varchar(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(256), authentication BLOB
);


create unique index ix_auth_username on authorities (username,authority);


INSERT INTO  oauth_client_details  VALUES ('acme','store-oauth2-resource','acmesecret','openid','authorization_code,refresh_token,password,client_credentials',NULL,'ROLE_CLIENT',NULL,NULL,NULL,'true');
INSERT INTO  users  VALUES ('sysadmin','sysadmin',1);
INSERT INTO  authorities  VALUES ('sysadmin','ROLE_ADMIN');


INSERT INTO  users  VALUES ('sysuser','sysuser',1);
INSERT INTO  authorities  VALUES ('sysuser','ROLE_USER');

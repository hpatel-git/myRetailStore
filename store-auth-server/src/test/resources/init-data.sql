CREATE SCHEMA IF NOT EXISTS `my_retail_store_ums` ;
USE `my_retail_store_ums` ;
 
CREATE TABLE IF NOT EXISTS users (
  username varchar(255),
  password varchar(255),
  enabled boolean
);

CREATE TABLE IF NOT EXISTS authorities (
  username varchar(255),
  authority varchar(255)
);

CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(255), authentication BLOB
);


create unique index ix_auth_username on authorities (username,authority);


INSERT INTO  oauth_client_details  VALUES ('acme','store-oauth2-resource','acmesecret','openid','authorization_code,refresh_token,password,client_credentials',NULL,'ROLE_CLIENT',NULL,NULL,NULL,'true');
INSERT INTO  users  VALUES ('sysadmin','sysadmin',1);
INSERT INTO  authorities  VALUES ('sysadmin','ROLE_ADMIN');


INSERT INTO  users  VALUES ('sysuser','sysuser',1);
INSERT INTO  authorities  VALUES ('sysuser','ROLE_USER');

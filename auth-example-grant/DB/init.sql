DROP TABLE IF EXISTS oauth_client_details CASCADE ;
create table oauth_client_details (
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

INSERT INTO oauth_client_details(client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('WebClientdExample', '$2a$10$73gCqeFxykiNGXG4R.6Qee1FgWmuoRZXWaRipoahA37cTDNADw57C', 'red', 'refresh_token,password', null, null, 3600, 7200, null, true);

-- Table Role
CREATE TABLE if not exists role (
                                    roleid BIGSERIAL NOT NULL PRIMARY KEY,
                                    role TEXT,
                                    description TEXT,
                                    createdDate TIMESTAMP NOT NULL,
                                    modifieddate TIMESTAMP NOT NULL
);

insert into "role" (roleid, role, description, createddate, modifieddate)
values (1, 'ADMIN', 'Admin', CURRENT_DATE, CURRENT_DATE);
insert into "role" (roleid, role, description, createddate, modifieddate)
values (2, 'USER', 'USER', CURRENT_DATE, CURRENT_DATE);


-- Table Users
CREATE TABLE if not exists users (
                                     userid bigserial NOT NULL PRIMARY KEY,
                                     userName VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fullName VARCHAR(255) NOT NULL,
    createdDate TIMESTAMP NOT NULL,
    modifieddate TIMESTAMP NOT NULL,
    statusid int8 NOT NULL,
    roleid int8 NOT NULL
    );
ALTER TABLE users ADD FOREIGN KEY (roleid) REFERENCES role(roleid);

insert into "users" (userName, password, fullName, createddate, modifieddate, statusid, roleid)
values ('user', '$2a$10$2LSXXFi3470QVGuRMEh0BOURUxRLERD/07VgoyY/ueuFCr2t6pWU6', 'account user', CURRENT_DATE, CURRENT_DATE, 1, 1);

insert into "users" (userName, password, fullName, createddate, modifieddate, statusid, roleid)
values ('admin', '$2a$10$2LSXXFi3470QVGuRMEh0BOURUxRLERD/07VgoyY/ueuFCr2t6pWU6', 'account user', CURRENT_DATE, CURRENT_DATE, 1, 2);

--
update oauth_client_details set authorized_grant_types = 'refresh_token,password,custom_grant_type' where client_id = 'WebClientdExample';

alter table users add column code VARCHAR(255);
update users set code = 'abc123' where userid = 1;
update users set code = 'efd123' where userid = 2;

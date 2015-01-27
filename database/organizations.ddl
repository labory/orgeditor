DROP TABLE IF EXISTS organization;
CREATE TABLE organization (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  address varchar(20),
  description varchar(20), 
  PRIMARY KEY (id) USING BTREE,
  UNIQUE KEY UNIQUE_NAME (name) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO organization (name, address, description) VALUES ('org1', 'address1', 'desc1');
INSERT INTO organization (name, address, description) VALUES ('org2', 'address2', 'desc2');
INSERT INTO organization (name, address, description) VALUES ('org3', 'address3', 'desc3');
INSERT INTO organization (name, address, description) VALUES ('org4', 'address4', 'desc4');
INSERT INTO organization (name, address, description) VALUES ('org5', 'address5', 'desc5');


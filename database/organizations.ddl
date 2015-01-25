DROP TABLE IF EXISTS organization;
CREATE TABLE organization (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  address varchar(20),
  description varchar(20), 
  PRIMARY KEY (id) USING BTREE,
  UNIQUE KEY UNIQUE_NAME (name) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


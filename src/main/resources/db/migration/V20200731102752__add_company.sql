DROP TABLE IF EXISTS company;
 CREATE TABLE company (
   company_id INT AUTO_INCREMENT  PRIMARY KEY,
   name VARCHAR(100) NOT NULL
 );

  INSERT INTO company (name) VALUES('tw');
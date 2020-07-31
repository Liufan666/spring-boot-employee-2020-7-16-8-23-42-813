DROP TABLE IF EXISTS company;
 CREATE TABLE company (
   company_id INT AUTO_INCREMENT  PRIMARY KEY,
   name VARCHAR(100) NOT NULL
 );

  INSERT INTO company (name) VALUES('tw');

  INSERT INTO employee (name, age, gender,company_id) VALUES
   ('chengcheng','54','male',1),
   ('yuexie','82','female',1),
   ('haifeng','69','female',1),
   ('ang','18','male',1);
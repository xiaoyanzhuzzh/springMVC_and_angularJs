DROP TABLE courses;
use gym_system;

DROP TABLE course_customer_relations;
DROP TABLE schedules;
DROP TABLE courses;
DROP TABLE customers;
DROP TABLE users;
DROP TABLE employees;

CREATE TABLE employees (
  id INT (11) NOT NULL auto_increment ,
  name VARCHAR (30) NOT NULL ,
  gender VARCHAR (10) NOT NULL ,
  age INT (6) NOT NULL ,
  email VARCHAR (40) DEFAULT NULL ,
  role VARCHAR (10) NOT NUll ,
  PRIMARY KEY  (id)
);

CREATE TABLE users (
  id INT (11) NOT NULL auto_increment ,
  name VARCHAR (30) NOT NULL ,
  password VARCHAR (60) NOT NULL ,
  employee_id INT (11) NOT NULL ,
  PRIMARY KEY (id) ,
  FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE customers (
  id INT (11) NOT NULL auto_increment,
  name VARCHAR (30) NOT NULL ,
  employee_id INT DEFAULT NULL ,
  PRIMARY KEY (id) ,
  FOREIGN KEY (employee_id) REFERENCES employees(id)
);


CREATE TABLE courses (
  id INT (11) NOT NULL auto_increment,
  name VARCHAR (30) NOT NULL ,
  employee_id INT NOT NULL ,
  PRIMARY KEY (id) ,
  FOREIGN KEY (employee_id) REFERENCES employees(id)
);


CREATE TABLE course_customer_relations (
  id INT (11) NOT NULL auto_increment,
  course_id INT (11) NOT NULL ,
  customer_id INT (11) NOT NULL ,
  PRIMARY KEY (id) ,
  FOREIGN KEY (course_id) REFERENCES courses(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE schedules (
  id INT (11) NOT NULL auto_increment,
  course_id INT (11) NOT NULL ,
  customer_id INT (11) DEFAULT NULL ,
  time DATE NOT NULL ,
  PRIMARY KEY (id) ,
  FOREIGN KEY (course_id) REFERENCES courses(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

INSERT INTO employees VALUES (NULL, "zhujiang", "male", 21, "12@qq.com", "coach");
INSERT INTO users VALUES (NULL, "111", "111", 1);


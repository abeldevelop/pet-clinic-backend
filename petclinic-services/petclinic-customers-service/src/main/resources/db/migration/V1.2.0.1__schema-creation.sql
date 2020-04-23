CREATE TABLE IF NOT EXISTS types (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80) NOT NULL,
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS customers (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  identification_document VARCHAR(15) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(first_name),
  INDEX(identification_document),
  UNIQUE (identification_document)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pets (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  birth_date DATE,
  type_id INT NOT NULL,
  customer_id INT NOT NULL,
  INDEX(name),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
) engine=InnoDB;

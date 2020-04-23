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
);

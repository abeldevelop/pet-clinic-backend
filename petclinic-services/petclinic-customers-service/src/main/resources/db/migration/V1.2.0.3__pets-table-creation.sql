CREATE TABLE IF NOT EXISTS pets (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  birth_date DATE,
  type_id INT NOT NULL,
  customer_id INT NOT NULL,
  INDEX(name),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
);

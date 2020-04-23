CREATE TABLE IF NOT EXISTS visits (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_identification_document VARCHAR(15) NOT NULL,
  pet_id INT NOT NULL,
  vet_id INT,
  visit_date DATE NOT NULL,
  visit_hour TIME NOT NULL,
  description VARCHAR(8192)
) engine=InnoDB;
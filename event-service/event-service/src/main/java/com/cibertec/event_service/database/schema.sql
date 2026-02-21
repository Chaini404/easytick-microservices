CREATE TABLE events (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  description TEXT,
  event_date TIMESTAMP NOT NULL,
  location VARCHAR(200),
  price DECIMAL(10,2),
  capacity INT,
  available_slots INT,
  organizer_id BIGINT NOT NULL,
  status VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  category_id bigint,
  FOREIGN KEY (id) REFERENCES event_categories(category_id)
);

CREATE TABLE event_categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

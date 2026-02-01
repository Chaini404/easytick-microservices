create database auth_db
create database event_db;
create database booking_db;
create database payment_db;
create database notification_db;
create database analytics_db;

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  password VARCHAR(255),
  role VARCHAR(50),
  enabled BOOLEAN,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

---------------------------------------------------------
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
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE event_categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

-----------------------------------------------------

CREATE TABLE bookings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  event_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  status VARCHAR(50),
  total_price DECIMAL(10,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE booking_history (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  booking_id BIGINT NOT NULL,
  status VARCHAR(50),
  changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

----------------------------------------------

CREATE TABLE payments (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  booking_id BIGINT NOT NULL,
  amount DECIMAL(10,2),
  payment_method VARCHAR(50),
  status VARCHAR(50),
  transaction_ref VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
----------------------------------------------

CREATE TABLE notifications (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  type VARCHAR(50),
  subject VARCHAR(150),
  message TEXT,
  status VARCHAR(50),
  sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE notification_queue (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_type VARCHAR(100),
  payload TEXT,
  processed BOOLEAN DEFAULT false
);

---------------------------------------------
CREATE TABLE event_metrics (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_id BIGINT NOT NULL,
  total_sales INT,
  total_revenue DECIMAL(10,2),
  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE user_activity (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  action VARCHAR(100),
  action_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE demand_prediction (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_id BIGINT NOT NULL,
  demand_level VARCHAR(50),
  confidence DECIMAL(5,2),
  calculated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS task_manager_db;
USE task_manager_db;

-- Create the Tasks table
CREATE TABLE IF NOT EXISTS Tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority VARCHAR(50),
    dueDate DATE,
    completed TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    completed_at TIMESTAMP NULL DEFAULT NULL
);

-- Insert sample tasks
INSERT INTO Tasks (title, description, priority, dueDate, completed, created_at, updated_at, completed_at) VALUES
('Task 1', 'Description for Task 1', 'High', '2024-01-05', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 2', 'Description for Task 2', 'Medium', '2024-01-06', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 3', 'Description for Task 3', 'Low', '2024-01-07', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 4', 'Description for Task 4', 'High', '2024-01-08', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 5', 'Description for Task 5', 'Medium', '2024-01-09', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 6', 'Description for Task 6', 'Low', '2024-01-10', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 7', 'Description for Task 7', 'High', '2024-01-11', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 8', 'Description for Task 8', 'Medium', '2024-01-12', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 9', 'Description for Task 9', 'Low', '2024-01-13', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
('Task 10', 'Description for Task 10', 'High', '2024-01-14', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);


SELECT * FROM tasks;

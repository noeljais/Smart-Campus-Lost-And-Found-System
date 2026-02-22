-- Smart Campus Lost and Found System Database Schema

-- Drop tables if they exist
DROP TABLE IF EXISTS found_items CASCADE;
DROP TABLE IF EXISTS lost_items CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(256) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Lost items table
CREATE TABLE lost_items (
    item_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    item_name VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    image_data BYTEA,
    contact_details VARCHAR(200) NOT NULL,
    reported_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'active'
);

-- Found items table
CREATE TABLE found_items (
    item_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
    item_name VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    image_data BYTEA,
    contact_details VARCHAR(200) NOT NULL,
    reported_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'active'
);

-- Create indexes for better performance
CREATE INDEX idx_lost_items_user_id ON lost_items(user_id);
CREATE INDEX idx_found_items_user_id ON found_items(user_id);
CREATE INDEX idx_lost_items_date ON lost_items(reported_date DESC);
CREATE INDEX idx_found_items_date ON found_items(reported_date DESC);

-- Sample data (optional)
-- INSERT INTO users (full_name, email, password_hash, phone) 
-- VALUES ('John Doe', 'john.doe@campus.edu', 'hashed_password_here', '1234567890');

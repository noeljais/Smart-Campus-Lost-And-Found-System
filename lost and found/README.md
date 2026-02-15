# Smart Campus Lost and Found System

A desktop application built with Java Swing and PostgreSQL (Supabase) for managing lost and found items on campus.

## Features

- **Authentication**: Signup and Login with campus email validation
- **Report Items**: Report lost or found items with images
- **View Items**: Browse all lost and found items
- **Report History**: View your own reported items
- **Image Upload**: Upload and preview images for items

## Technology Stack

- **Frontend**: Java Swing
- **Database**: PostgreSQL (Supabase)
- **Connection**: JDBC with PostgreSQL driver

## Project Structure

```
lost-and-found/
├── src/
│   ├── config/
│   │   └── DatabaseConfig.java
│   ├── model/
│   │   ├── User.java
│   │   ├── LostItem.java
│   │   └── FoundItem.java
│   ├── dao/
│   │   ├── UserDAO.java
│   │   ├── LostItemDAO.java
│   │   └── FoundItemDAO.java
│   ├── view/
│   │   ├── LoginFrame.java
│   │   ├── SignupFrame.java
│   │   ├── HomeFrame.java
│   │   ├── ReportLostItemFrame.java
│   │   ├── ReportFoundItemFrame.java
│   │   ├── ViewLostItemsFrame.java
│   │   ├── ViewFoundItemsFrame.java
│   │   └── ReportHistoryFrame.java
│   ├── controller/
│   │   ├── AuthController.java
│   │   └── ItemController.java
│   ├── util/
│   │   ├── ImageUtil.java
│   │   └── ValidationUtil.java
│   └── Main.java
├── lib/
│   └── postgresql-42.7.1.jar
├── resources/
│   └── images/
└── database/
    └── schema.sql
```

## Prerequisites

1. **Java Development Kit (JDK)** 11 or higher
2. **PostgreSQL JDBC Driver** (included in lib folder)
3. **Supabase Account** with PostgreSQL database

## Database Setup

### 1. Create Supabase Project

1. Go to [supabase.com](https://supabase.com)
2. Create a new project
3. Note down your database credentials

### 2. Run SQL Schema

Execute the SQL script in `database/schema.sql` in your Supabase SQL Editor.

### 3. Configure Database Connection

Edit `src/config/DatabaseConfig.java` and update:
- `DB_URL`: Your Supabase database URL
- `DB_USER`: Your database username
- `DB_PASSWORD`: Your database password

## Compilation and Running

### Option 1: Using Command Line

```bash
# Navigate to project directory
cd "d:\lost and found"

# Compile all Java files
javac -cp "lib\postgresql-42.7.1.jar" -d bin src\**\*.java

# Run the application
java -cp "bin;lib\postgresql-42.7.1.jar" Main
```

### Option 2: Using batch script (Windows)

```bash
# Compile
compile.bat

# Run
run.bat
```

## Usage

1. **First Time Setup**:
   - Run the application
   - Click "Sign Up" to create an account
   - Use a campus email (e.g., student@campus.edu)

2. **Login**:
   - Enter your email and password
   - Click "Login"

3. **Report Lost Item**:
   - Click "Report Lost Item" from home page
   - Fill in item details
   - Upload an image (optional)
   - Submit

4. **Report Found Item**:
   - Click "Report Found Item"
   - Fill in details and submit

5. **View Items**:
   - Browse all lost or found items
   - View images and contact details

6. **Report History**:
   - View all your reported items (both lost and found)

## Security Features

- Password hashing using SHA-256
- Prepared statements to prevent SQL injection
- Email validation for campus emails
- Session management for logged-in users

## UI Features

- Clean, modern interface
- Responsive layout using BorderLayout and GridBagLayout
- Image preview before upload
- Scrollable tables for item lists
- Proper error handling and user feedback

## License

MIT License

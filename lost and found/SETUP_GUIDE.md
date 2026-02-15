# Smart Campus Lost and Found System - Setup Guide

## Quick Start Guide

### Step 1: Database Setup (Supabase)

1. **Create a Supabase Account**
   - Go to https://supabase.com
   - Sign up for a free account
   - Create a new project

2. **Get Database Credentials**
   - In your Supabase project dashboard, go to Settings → Database
   - Note down:
     - Host (looks like: db.xxxxxxxxxxxxx.supabase.co)
     - Database name (usually: postgres)
     - Port (usually: 5432)
     - User (usually: postgres)
     - Password (the one you set during project creation)

3. **Run Database Schema**
   - In Supabase dashboard, go to SQL Editor
   - Copy the contents of `database/schema.sql`
   - Paste and run it in the SQL Editor
   - You should see success messages for table creation

### Step 2: Configure Database Connection

1. Open `src/config/DatabaseConfig.java`
2. Update the following lines with your Supabase credentials:

```java
private static final String DB_URL = "jdbc:postgresql://db.YOUR_PROJECT_REF.supabase.co:5432/postgres";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "your_password_here";
```

Example:
```java
private static final String DB_URL = "jdbc:postgresql://db.abcdefghijklmnop.supabase.co:5432/postgres";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "MySecurePassword123";
```

### Step 3: Install PostgreSQL JDBC Driver

The PostgreSQL JDBC driver is required to connect to the database.

**Option 1: Download Manually**
1. Download from: https://jdbc.postgresql.org/download/
2. Get version 42.7.1 or later
3. Save as `lib/postgresql-42.7.1.jar`

**Option 2: Direct Download**
```bash
curl -o "lib/postgresql-42.7.1.jar" https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.1/postgresql-42.7.1.jar
```

### Step 4: Compile the Application

**Windows:**
```bash
compile.bat
```

**Manual Compilation:**
```bash
javac -cp "lib\postgresql-42.7.1.jar" -d bin src\**\*.java
```

### Step 5: Run the Application

**Windows:**
```bash
run.bat
```

**Manual Run:**
```bash
java -cp "bin;lib\postgresql-42.7.1.jar" Main
```

## First Time Usage

1. **Sign Up**
   - Click "Create Account" on the login screen
   - Enter your details
   - Use a campus email (.edu or .ac.in)
   - Password must be at least 6 characters

2. **Login**
   - Enter your email and password
   - Click "Login"

3. **Start Using**
   - Report lost or found items
   - Browse all items
   - View your report history

## Troubleshooting

### Connection Error
- **Problem**: "Failed to connect to database"
- **Solution**: 
  - Check your internet connection
  - Verify database credentials in DatabaseConfig.java
  - Ensure Supabase project is running
  - Check if your IP is allowed in Supabase (Settings → Database → Connection Pooling)

### Compilation Error
- **Problem**: "Class not found" or compilation fails
- **Solution**:
  - Ensure PostgreSQL JDBC driver is in `lib/` folder
  - Check Java version (JDK 11 or higher required)
  - Verify all source files are present

### Driver Not Found Error
- **Problem**: "PostgreSQL JDBC Driver not found"
- **Solution**:
  - Download postgresql-42.7.1.jar
  - Place it in the `lib/` directory
  - Recompile the application

### Email Validation Error
- **Problem**: "Please use a campus email"
- **Solution**:
  - Use an email ending with .edu or .ac.in
  - Example: student@university.edu

## Features

✅ User Authentication (Signup/Login)
✅ Report Lost Items with Images
✅ Report Found Items with Images
✅ View All Lost Items
✅ View All Found Items
✅ View Personal Report History
✅ Image Upload and Preview
✅ Secure Password Hashing
✅ SQL Injection Protection
✅ Modern UI Design

## System Requirements

- **Java**: JDK 11 or higher
- **Database**: PostgreSQL (via Supabase)
- **OS**: Windows, macOS, or Linux
- **Internet**: Required for database connection

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Verify database connection settings
3. Ensure all dependencies are installed
4. Check console output for error messages

## License

MIT License - Free to use and modify

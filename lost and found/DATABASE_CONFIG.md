# Database Configuration Instructions

## Supabase Setup (Recommended)

### Step 1: Create Supabase Project
1. Visit https://supabase.com
2. Sign up or log in
3. Click "New Project"
4. Fill in project details:
   - Name: lost-and-found (or your choice)
   - Database Password: (choose a strong password - save this!)
   - Region: Choose closest to you
   - Pricing Plan: Free tier is sufficient

### Step 2: Get Connection Details
1. Go to Project Settings (gear icon)
2. Click "Database" in the sidebar
3. Scroll to "Connection Info"
4. Note down:
   - **Host**: db.xxxxxxxxxxxxx.supabase.co
   - **Database name**: postgres
   - **Port**: 5432
   - **User**: postgres
   - **Password**: (the one you set in Step 1)

### Step 3: Run Database Schema
1. In Supabase dashboard, click "SQL Editor" (left sidebar)
2. Click "New Query"
3. Open the file `database/schema.sql` from this project
4. Copy all contents
5. Paste into the SQL Editor
6. Click "Run" or press Ctrl+Enter
7. You should see success messages for:
   - DROP TABLE statements (may show errors if tables don't exist - this is OK)
   - CREATE TABLE statements (should succeed)
   - CREATE INDEX statements (should succeed)

### Step 4: Configure Application
1. Open `src/config/DatabaseConfig.java`
2. Find these lines:
```java
private static final String DB_URL = "jdbc:postgresql://db.YOUR_PROJECT_REF.supabase.co:5432/postgres";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "your_password_here";
```

3. Replace with your actual values:
```java
private static final String DB_URL = "jdbc:postgresql://db.abcdefghijklmnop.supabase.co:5432/postgres";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "YourActualPassword123";
```

### Step 5: Test Connection
After updating the configuration:
1. Compile the application: `compile.bat`
2. Run the application: `run.bat`
3. If you see "Database connection established successfully!" in the console, you're good!
4. If you see connection errors, double-check your credentials

## Alternative: Local PostgreSQL

If you prefer to use a local PostgreSQL installation:

### Step 1: Install PostgreSQL
1. Download from https://www.postgresql.org/download/
2. Install with default settings
3. Remember the password you set for the postgres user

### Step 2: Create Database
```sql
-- Connect to PostgreSQL using pgAdmin or psql
CREATE DATABASE lost_and_found;
```

### Step 3: Run Schema
```bash
psql -U postgres -d lost_and_found -f database/schema.sql
```

### Step 4: Configure Application
Update `src/config/DatabaseConfig.java`:
```java
private static final String DB_URL = "jdbc:postgresql://localhost:5432/lost_and_found";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "your_postgres_password";
```

## Troubleshooting

### Connection Timeout
**Problem**: Application hangs when connecting to database
**Solution**: 
- Check internet connection (for Supabase)
- Verify Supabase project is active
- Check firewall settings

### Authentication Failed
**Problem**: "password authentication failed for user postgres"
**Solution**:
- Double-check password in DatabaseConfig.java
- Ensure no extra spaces in password
- Try resetting database password in Supabase

### SSL Error
**Problem**: SSL-related connection errors
**Solution**: Add SSL parameter to connection URL:
```java
private static final String DB_URL = "jdbc:postgresql://db.xxxxx.supabase.co:5432/postgres?sslmode=require";
```

### IP Whitelist (Supabase)
**Problem**: Connection refused
**Solution**:
- Supabase free tier allows all IPs by default
- If restricted, go to Settings → Database → Connection Pooling
- Add your IP address to whitelist

## Verifying Database Setup

After running the schema, verify tables exist:

1. In Supabase SQL Editor, run:
```sql
SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'public';
```

2. You should see:
   - users
   - lost_items
   - found_items

3. Check table structure:
```sql
SELECT column_name, data_type 
FROM information_schema.columns 
WHERE table_name = 'users';
```

## Sample Data (Optional)

To test the application with sample data:

```sql
-- Insert a test user (password is 'test123' hashed with SHA-256)
INSERT INTO users (full_name, email, password_hash, phone) 
VALUES ('Test User', 'test@university.edu', 
        'ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae', 
        '1234567890');

-- Insert a test lost item
INSERT INTO lost_items (user_id, item_name, description, contact_details)
VALUES (1, 'Blue Backpack', 'Lost near library on Monday', 'test@university.edu');

-- Insert a test found item
INSERT INTO found_items (user_id, item_name, description, contact_details)
VALUES (1, 'Black Wallet', 'Found in cafeteria', 'test@university.edu');
```

Login with:
- Email: test@university.edu
- Password: test123

## Security Notes

⚠️ **Important Security Reminders**:
1. Never commit DatabaseConfig.java with real credentials to public repositories
2. Use environment variables for production deployments
3. Regularly rotate database passwords
4. Use strong passwords (12+ characters, mixed case, numbers, symbols)
5. Enable 2FA on your Supabase account

## Need Help?

- Supabase Documentation: https://supabase.com/docs
- PostgreSQL Documentation: https://www.postgresql.org/docs/
- JDBC Documentation: https://jdbc.postgresql.org/documentation/

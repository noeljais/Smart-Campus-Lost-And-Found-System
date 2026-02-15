# 🎓 Smart Campus Lost and Found System - Complete Guide

## 📋 Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
3. [System Requirements](#system-requirements)
4. [Installation](#installation)
5. [Usage Guide](#usage-guide)
6. [Screenshots & UI](#screenshots--ui)
7. [Troubleshooting](#troubleshooting)
8. [Technical Details](#technical-details)

---

## 🚀 Quick Start

### 1. Setup Database (5 minutes)
```
1. Go to https://supabase.com and create a free account
2. Create a new project and note your database password
3. Go to SQL Editor and run the contents of database/schema.sql
4. Update src/config/DatabaseConfig.java with your credentials
```

### 2. Compile & Run
```bash
# Windows
compile.bat
run.bat

# Manual
javac -cp "lib\postgresql-42.7.1.jar" -d bin src\**\*.java
java -cp "bin;lib\postgresql-42.7.1.jar" Main
```

### 3. First Login
```
1. Click "Create Account"
2. Use a campus email (.edu or .ac.in)
3. Set password (min 6 characters)
4. Login and start using!
```

---

## ✨ Features

### 🔐 Authentication System
- ✅ Secure user registration with email validation
- ✅ Campus email verification (.edu, .ac.in domains)
- ✅ Password hashing using SHA-256
- ✅ Session management
- ✅ Secure login/logout

### 📝 Lost Item Management
- ✅ Report lost items with detailed descriptions
- ✅ Upload images for lost items
- ✅ Image preview before submission
- ✅ Contact information management
- ✅ View all reported lost items
- ✅ Search and browse functionality

### 🎯 Found Item Management
- ✅ Report found items with descriptions
- ✅ Upload images for found items
- ✅ Image preview functionality
- ✅ Contact details for item return
- ✅ View all reported found items
- ✅ Browse and search capabilities

### 📊 Personal Dashboard
- ✅ View your report history
- ✅ Track both lost and found items
- ✅ Manage your submissions
- ✅ User-friendly interface

### 🎨 Modern UI/UX
- ✅ Clean, professional design
- ✅ Color-coded sections (Lost = Red, Found = Green)
- ✅ Responsive layouts
- ✅ Intuitive navigation
- ✅ Smooth user experience
- ✅ Custom styled components

### 🔒 Security Features
- ✅ SQL injection prevention (prepared statements)
- ✅ Password encryption
- ✅ Input validation
- ✅ Secure database connections
- ✅ Session management

---

## 💻 System Requirements

### Required
- **Java**: JDK 11 or higher
- **Internet**: For database connection (Supabase)
- **OS**: Windows, macOS, or Linux
- **RAM**: 512 MB minimum
- **Disk Space**: 50 MB

### Included
- ✅ PostgreSQL JDBC Driver (42.7.1)
- ✅ All source code
- ✅ Database schema
- ✅ Compilation scripts

---

## 📦 Installation

### Step 1: Verify Java Installation
```bash
java -version
# Should show Java 11 or higher
```

### Step 2: Database Setup (Supabase)
See [DATABASE_CONFIG.md](DATABASE_CONFIG.md) for detailed instructions.

**Quick version:**
1. Create Supabase account at https://supabase.com
2. Create new project
3. Run `database/schema.sql` in SQL Editor
4. Update `src/config/DatabaseConfig.java`:
   ```java
   private static final String DB_URL = "jdbc:postgresql://db.YOUR_REF.supabase.co:5432/postgres";
   private static final String DB_PASSWORD = "your_password";
   ```

### Step 3: Compile Application
```bash
# Windows
compile.bat

# Linux/Mac
javac -cp "lib/postgresql-42.7.1.jar" -d bin src/**/*.java
```

### Step 4: Run Application
```bash
# Windows
run.bat

# Linux/Mac
java -cp "bin:lib/postgresql-42.7.1.jar" Main
```

---

## 📖 Usage Guide

### Creating an Account
1. Launch the application
2. Click **"Create Account"**
3. Fill in your details:
   - Full Name
   - Campus Email (must end with .edu or .ac.in)
   - Password (minimum 6 characters)
   - Confirm Password
   - Phone Number (optional)
4. Click **"Create Account"**
5. Return to login screen

### Logging In
1. Enter your campus email
2. Enter your password
3. Click **"Login"**

### Reporting a Lost Item
1. From home screen, click **"Report Lost Item"**
2. Fill in the form:
   - Item Name (e.g., "Blue Backpack")
   - Description (detailed description)
   - Contact Details (how to reach you)
3. (Optional) Click **"Choose Image"** to upload a photo
4. Preview the image
5. Click **"Submit Report"**

### Reporting a Found Item
1. From home screen, click **"Report Found Item"**
2. Fill in the form:
   - Item Name
   - Description (where found, when, etc.)
   - Contact Details
3. (Optional) Upload an image
4. Click **"Submit Report"**

### Viewing Lost Items
1. Click **"View Lost Items"**
2. Browse the table of all lost items
3. Double-click any row to see full details
4. View images if available
5. Contact the person who lost the item

### Viewing Found Items
1. Click **"View Found Items"**
2. Browse all found items
3. Double-click for details
4. Contact the finder if you recognize your item

### Checking Your Report History & Deleting Items
1. Click **"My Report History"**
2. See all your reports (both lost and found)
3. Review submission dates
4. Track your items
5. **To Delete an Item**:
   - Select the item's row in the table
   - Click the **"Delete Item"** button
   - Confirm the deletion in the popup dialog
   - *Note: Deletion is permanent and should be done after the item reaches its owner.*

---

## 🎨 Screenshots & UI

### Color Scheme
- **Lost Items**: Red theme (#EA4335)
- **Found Items**: Green theme (#34A853)
- **View Items**: Blue theme (#4361EE)
- **History**: Purple theme (#9C27B0)
- **Primary**: Blue (#4361EE)
- **Background**: Light gray (#F5F5FA)

### UI Components
- Modern flat design
- Custom styled buttons with hover effects
- Professional table layouts
- Image preview functionality
- Responsive forms
- Clean typography (Segoe UI)

---

## 🔧 Troubleshooting

### Database Connection Issues

**Problem**: "Failed to connect to database"
```
Solutions:
1. Check internet connection
2. Verify Supabase project is active
3. Confirm database credentials in DatabaseConfig.java
4. Ensure no typos in connection URL
5. Check if Supabase allows your IP (usually automatic)
```

**Problem**: "Driver not found"
```
Solutions:
1. Verify postgresql-42.7.1.jar exists in lib/ folder
2. Re-download if missing:
   curl -o lib/postgresql-42.7.1.jar https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.1/postgresql-42.7.1.jar
3. Recompile the application
```

### Compilation Errors

**Problem**: "Class not found" errors
```
Solutions:
1. Ensure all source files are present in src/
2. Check Java version (must be 11+)
3. Verify JDBC driver is in lib/
4. Clean and recompile:
   - Delete bin/ folder
   - Run compile.bat again
```

### Login Issues

**Problem**: "Invalid email or password"
```
Solutions:
1. Ensure you've created an account first
2. Check for typos in email/password
3. Verify email ends with .edu or .ac.in
4. Password is case-sensitive
```

**Problem**: "Please use a campus email"
```
Solutions:
1. Use email ending with .edu (e.g., student@university.edu)
2. Or use .ac.in (e.g., student@college.ac.in)
3. Other domains are not accepted
```

### Image Upload Issues

**Problem**: Image not displaying
```
Solutions:
1. Use supported formats: JPG, PNG, GIF, BMP
2. Ensure image file size is reasonable (<5MB)
3. Check file permissions
4. Try a different image
```

---

## 🛠 Technical Details

### Architecture
- **Pattern**: MVC (Model-View-Controller)
- **Database**: PostgreSQL via Supabase
- **UI Framework**: Java Swing
- **Connection**: JDBC

### Project Structure
```
src/
├── Main.java                    # Entry point
├── config/                      # Configuration
│   └── DatabaseConfig.java
├── model/                       # Data models
│   ├── User.java
│   ├── LostItem.java
│   └── FoundItem.java
├── dao/                         # Data access
│   ├── UserDAO.java
│   ├── LostItemDAO.java
│   └── FoundItemDAO.java
├── controller/                  # Business logic
│   ├── AuthController.java
│   └── ItemController.java
├── util/                        # Utilities
│   ├── ValidationUtil.java
│   └── ImageUtil.java
└── view/                        # UI components
    ├── LoginFrame.java
    ├── SignupFrame.java
    ├── HomeFrame.java
    ├── ReportLostItemFrame.java
    ├── ReportFoundItemFrame.java
    ├── ViewLostItemsFrame.java
    ├── ViewFoundItemsFrame.java
    └── ReportHistoryFrame.java
```

### Database Schema
```sql
users (user_id, full_name, email, password_hash, phone, created_at)
lost_items (item_id, user_id, item_name, description, image_data, contact_details, reported_date, status)
found_items (item_id, user_id, item_name, description, image_data, contact_details, reported_date, status)
```

### Security Measures
1. **Password Hashing**: SHA-256 encryption
2. **SQL Injection Prevention**: Prepared statements
3. **Input Validation**: All inputs validated
4. **Email Verification**: Campus email only
5. **Session Management**: Secure user sessions

---

## 📚 Additional Resources

- **Setup Guide**: See [SETUP_GUIDE.md](SETUP_GUIDE.md)
- **Database Config**: See [DATABASE_CONFIG.md](DATABASE_CONFIG.md)
- **Project Structure**: See [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md)

---

## 🤝 Support

For issues or questions:
1. Check this guide's troubleshooting section
2. Review the setup guides
3. Verify database connection
4. Check console output for errors

---

## 📄 License

MIT License - Free to use and modify

---

## 🎉 You're All Set!

Your Smart Campus Lost and Found System is ready to use. Help your campus community reunite with their lost items!

**Happy Finding! 🔍**

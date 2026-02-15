# 🚀 Quick Reference Card

## ⚡ Quick Commands

### Compile
```bash
compile.bat
```

### Run
```bash
run.bat
```

### Manual Compile
```bash
javac -cp "lib\postgresql-42.7.1.jar" -d bin src\**\*.java
```

### Manual Run
```bash
java -cp "bin;lib\postgresql-42.7.1.jar" Main
```

---

## 🔑 Default Test Account (After adding sample data)

**Email**: test@university.edu  
**Password**: test123

---

## 📝 Database Connection Template

```java
// In src/config/DatabaseConfig.java

private static final String DB_URL = "jdbc:postgresql://db.YOUR_PROJECT_REF.supabase.co:5432/postgres";
private static final String DB_USER = "postgres";
private static final String DB_PASSWORD = "your_password_here";
```

---

## 🎨 UI Color Reference

| Feature | Color | Hex Code |
|---------|-------|----------|
| Primary | Blue | #4361EE |
| Lost Items | Red | #EA4335 |
| Found Items | Green | #34A853 |
| History | Purple | #9C27B0 |
| Found Accent | Yellow | #FBBC05 |
| Background | Light Gray | #F5F5FA |

---

## 📊 Database Tables

### users
- user_id (PK)
- full_name
- email (unique)
- password_hash
- phone
- created_at

### lost_items
- item_id (PK)
- user_id (FK)
- item_name
- description
- image_data
- contact_details
- reported_date
- status

### found_items
- item_id (PK)
- user_id (FK)
- item_name
- description
- image_data
- contact_details
- reported_date
- status

---

## 🔧 Common Issues & Quick Fixes

### Connection Error
```
✓ Check internet
✓ Verify Supabase credentials
✓ Ensure project is active
```

### Compilation Error
```
✓ Check Java version (11+)
✓ Verify JDBC driver in lib/
✓ Delete bin/ and recompile
```

### Login Failed
```
✓ Use campus email (.edu or .ac.in)
✓ Check password (case-sensitive)
✓ Create account first
```

---

## 📚 Documentation Files

| File | Purpose |
|------|---------|
| README.md | Project overview |
| SETUP_GUIDE.md | Setup instructions |
| DATABASE_CONFIG.md | Database setup |
| USER_GUIDE.md | User manual |
| PROJECT_STRUCTURE.md | Architecture docs |
| SUMMARY.md | Project summary |
| QUICK_REFERENCE.md | This file |

---

## 🎯 Feature Checklist

- [x] User Authentication
- [x] Report Lost Items
- [x] Report Found Items
- [x] View Lost Items
- [x] View Found Items
- [x] Report History
- [x] Image Upload
- [x] Image Preview
- [x] Modern UI
- [x] Security Features

---

## 📞 Campus Email Formats

**Accepted**:
- student@university.edu ✅
- faculty@college.edu ✅
- admin@institute.ac.in ✅

**Not Accepted**:
- user@gmail.com ❌
- user@yahoo.com ❌
- user@outlook.com ❌

---

## 🔐 Password Requirements

- Minimum 6 characters
- Case-sensitive
- No special requirements (but recommended)

---

## 📁 Project Structure (Quick View)

```
lost-and-found/
├── src/                    # Source code
│   ├── Main.java          # Entry point
│   ├── config/            # Configuration
│   ├── model/             # Data models
│   ├── dao/               # Database access
│   ├── controller/        # Business logic
│   ├── util/              # Utilities
│   └── view/              # UI components
├── database/              # SQL schema
├── lib/                   # JDBC driver
├── resources/             # Resources
├── compile.bat           # Compile script
└── run.bat               # Run script
```

---

## ⚙️ System Requirements

- **Java**: JDK 11+
- **RAM**: 512 MB+
- **Disk**: 50 MB
- **Internet**: Required
- **OS**: Windows/Mac/Linux

---

## 🎓 First Time Setup (3 Steps)

1. **Database** → Create Supabase project & run schema
2. **Config** → Update DatabaseConfig.java
3. **Run** → compile.bat → run.bat

---

## 💡 Pro Tips

✨ Double-click table rows for full details  
✨ Use refresh button to see new items  
✨ Images are optional but recommended  
✨ Keep contact details up to date  
✨ Check report history regularly  

---

## 🆘 Need Help?

1. Check USER_GUIDE.md
2. Review SETUP_GUIDE.md
3. See DATABASE_CONFIG.md
4. Check console for errors

---

**Happy Finding! 🔍**

# Project Structure

```
lost-and-found/
│
├── README.md                          # Project overview and documentation
├── SETUP_GUIDE.md                     # Detailed setup instructions
├── compile.bat                        # Windows compilation script
├── run.bat                           # Windows run script
│
├── database/
│   └── schema.sql                    # PostgreSQL database schema
│
├── lib/
│   └── postgresql-42.7.1.jar         # PostgreSQL JDBC driver
│
├── resources/
│   ├── images/                       # Application images (if any)
│   └── README.md                     # Resources directory info
│
├── src/
│   ├── Main.java                     # Application entry point
│   │
│   ├── config/
│   │   └── DatabaseConfig.java       # Database connection configuration
│   │
│   ├── model/
│   │   ├── User.java                 # User entity model
│   │   ├── LostItem.java             # Lost item entity model
│   │   └── FoundItem.java            # Found item entity model
│   │
│   ├── dao/
│   │   ├── UserDAO.java              # User data access object
│   │   ├── LostItemDAO.java          # Lost item data access object
│   │   └── FoundItemDAO.java         # Found item data access object
│   │
│   ├── controller/
│   │   ├── AuthController.java       # Authentication controller
│   │   └── ItemController.java       # Item management controller
│   │
│   ├── util/
│   │   ├── ValidationUtil.java       # Input validation utilities
│   │   └── ImageUtil.java            # Image processing utilities
│   │
│   └── view/
│       ├── LoginFrame.java           # Login screen
│       ├── SignupFrame.java          # Registration screen
│       ├── HomeFrame.java            # Main dashboard
│       ├── ReportLostItemFrame.java  # Report lost item form
│       ├── ReportFoundItemFrame.java # Report found item form
│       ├── ViewLostItemsFrame.java   # View all lost items
│       ├── ViewFoundItemsFrame.java  # View all found items
│       └── ReportHistoryFrame.java   # User's report history
│
└── bin/                              # Compiled .class files (created after compilation)
```

## File Descriptions

### Configuration Files
- **DatabaseConfig.java**: Manages PostgreSQL database connections via JDBC

### Model Classes (MVC - Model)
- **User.java**: Represents user accounts with authentication details
- **LostItem.java**: Represents lost item reports with images
- **FoundItem.java**: Represents found item reports with images

### Data Access Objects (DAO)
- **UserDAO.java**: CRUD operations for users (create, authenticate, check email)
- **LostItemDAO.java**: CRUD operations for lost items
- **FoundItemDAO.java**: CRUD operations for found items

### Controllers (MVC - Controller)
- **AuthController.java**: Handles user registration, login, logout, session management
- **ItemController.java**: Handles lost/found item reporting and retrieval

### Utilities
- **ValidationUtil.java**: Email validation, password hashing (SHA-256), input validation
- **ImageUtil.java**: Image conversion, resizing, thumbnail generation, validation

### Views (MVC - View)
- **LoginFrame.java**: User login interface
- **SignupFrame.java**: New user registration interface
- **HomeFrame.java**: Main dashboard with navigation buttons
- **ReportLostItemFrame.java**: Form to report lost items with image upload
- **ReportFoundItemFrame.java**: Form to report found items with image upload
- **ViewLostItemsFrame.java**: JTable displaying all lost items
- **ViewFoundItemsFrame.java**: JTable displaying all found items
- **ReportHistoryFrame.java**: User's personal report history

## Architecture Pattern

This application follows the **MVC (Model-View-Controller)** architecture:

- **Model**: User, LostItem, FoundItem (data structures)
- **View**: All *Frame.java classes (UI components)
- **Controller**: AuthController, ItemController (business logic)
- **DAO**: Separate data access layer for database operations

## Database Schema

### Tables
1. **users**: Stores user account information
2. **lost_items**: Stores lost item reports
3. **found_items**: Stores found item reports

### Relationships
- lost_items.user_id → users.user_id (Foreign Key)
- found_items.user_id → users.user_id (Foreign Key)

## Security Features

1. **Password Hashing**: SHA-256 encryption
2. **SQL Injection Prevention**: Prepared statements throughout
3. **Email Validation**: Campus email verification (.edu, .ac.in)
4. **Session Management**: Current user tracking
5. **Input Validation**: All user inputs validated before processing

## UI Design Principles

1. **Modern Color Scheme**: Custom color palette with vibrant accents
2. **Responsive Layout**: BorderLayout and GridBagLayout for flexibility
3. **User Feedback**: Clear error messages and success notifications
4. **Intuitive Navigation**: Consistent back buttons and clear labels
5. **Visual Hierarchy**: Proper use of fonts, sizes, and spacing

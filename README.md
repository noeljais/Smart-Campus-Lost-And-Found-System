Smart Campus Lost and Found System

Team Members

* **Noel Jais Manuel**
* **Bharath**

---

Problem Statement

In a large campus environment, students and staff frequently lose personal belongings such as ID cards, wallets, mobile phones, keys, and books. Currently, there is no centralized digital system to report and track lost and found items. This results in:

* Difficulty in locating lost items
* Time-consuming manual reporting
* Lack of proper communication between finder and owner
* Low recovery rate of lost belongings

Therefore, there is a need for a **centralized digital Lost and Found System** to efficiently manage lost and found items within the campus.

---

Objective

The main objective of this project is to develop a **desktop-based Smart Campus Lost and Found System** that allows users to:

* Register and login securely
* Report lost items
* Report found items
* Upload item images
* View all reported items
* Contact the person who reported the item

The system aims to improve item recovery efficiency and provide a user-friendly platform.

---

Features

##User Features

* User Registration
* User Login Authentication
* Secure password storage

---

Item Management Features

* Report Lost Items
* Report Found Items
* Upload Item Image
* Add Description and Contact Details
* View All Lost Items
* View All Found Items

---

GUI Features

* Simple and user-friendly interface
* Built using Java Swing
* Interactive forms
* Dashboard navigation

---

Database Features

* PostgreSQL database integration
* Secure data storage
* Image storage using BYTEA
* Efficient data retrieval using indexes

---

Technologies Used

| Technology               | Purpose               |
| ------------------------ | --------------------- |
| Java                     | Programming Language  |
| Java Swing               | GUI Development       |
| PostgreSQL               | Database              |
| JDBC                     | Database Connectivity |
| SQL                      | Database Queries      |
| IntelliJ IDEA / NetBeans | IDE                   |

---

Project Structure

```
Smart_Campus_Lost_And_Found_System/
README.md

src/
Main.java

DB.java

LoginSignupFrame.java

HomeFrame.java

ReportFrame.java

ViewItemsFrame.java

database/
database.sql


```

---

Steps to Run the Program

Step 1: Install Requirements

Install:

* Java JDK 8 or above
* PostgreSQL
* IDE (IntelliJ / NetBeans recommended)

---

Step 2: Create Database

Open PostgreSQL and run:

```sql
CREATE DATABASE campus_lost_found;
```

---

Step 3: Run Database Script

Execute:

```
database.sql
```

This creates required tables.

---

Step 4: Configure Database Connection

Open **DB.java**

Edit:

```java
String url = "jdbc:postgresql://localhost:5432/campus_lost_found";
String user = "postgres";
String password = "your_password";
```

---

Step 5: Compile the Program

Open terminal in project folder:

```
javac *.java
```

---

Step 6: Run the Program

```
java Main
---

#  Sample Inputs and Outputs / Test Cases

---

## Test Case 1: User Registration

**Input:**

Full Name: Noel Jais Manuel

Email: [nl@email.com](mailto:nl@email.com)

Password: 123456

Phone: 9876543210

**Output:**

User registered successfully

---

## Test Case 2: User Login

**Input:**

Email: [nl@email.com](mailto:nl@email.com)

Password: 123456

**Output:**

Login successful

Home screen opens

---

## Test Case 3: Report Lost Item

**Input:**

Item Name: Wallet

Description: Black leather wallet

Contact: 9876543210

**Output:**

Lost item reported successfully

Item saved in database

---

## Test Case 4: View Items

**Action:**

Click View Items

**Output:**

List of lost and found items displayed

---

## Test Case 5: Database Verification

**Query:**

```sql
SELECT * FROM lost_items;
```

**Output:**

Shows reported items

---

System Workflow

```
User
 ↓
Login / Signup
 ↓
Home Screen
 ↓
Report Item / View Items
 ↓
Database Storage
 ↓
Item Recovery
```

---

Security Features

* Secure login system
* Password stored securely
* PreparedStatement prevents SQL Injection
* Database constraints

---

Future Enhancements

* Search functionality
* Filter items
* Claim item feature
* Email notification
* Admin panel
* Mobile app version
* Web-based system

---

Conclusion

The Smart Campus Lost and Found System provides an efficient and user-friendly solution for managing lost and found items in a campus environment. It simplifies reporting, improves communication, and increases the chances of recovering lost items.

---

License

This project is developed for educational purposes.

---

Thank You

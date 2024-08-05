

# Java Project Hub

This repository showcases a collection of Java projects that demonstrate various aspects of Java programming, including GUI development, socket programming, database management, file handling, and more. Each project is designed to solve a specific problem or implement a particular functionality, with detailed explanations provided for each.

## Table of Contents

1. [Chat Messenger using Sockets](#chat-messenger-using-sockets)
2. [Clone DBMS Application](#clone-dbms-application)
3. [Packer-Unpacker (File Packer Unpacker)](#packer-unpacker-file-packer-unpacker)

---

## Chat Messenger using Sockets

### Overview

This Java application demonstrates a simple chat server and client using Swing for the graphical user interface (GUI) and sockets for communication between them.

### Features

- **ServerGUI:**
  - Opens a GUI window displaying chat messages.
  - Allows the server to send messages to connected clients.
  - Includes a log button to save the chat log.

- **ClientGUI:**
  - Opens a GUI window for chatting with the server.
  - Displays messages from the server in different colors.
  - Allows chat log saving with a log button.

### How to Run

1. **Compile the Code:**
   ```bash
   javac ServerGUI.java
   javac ClientGUI.java
   ```

2. **Run the Server:**
   ```bash
   java ServerGUI
   ```

3. **Run the Client:**
   ```bash
   java ClientGUI
   ```

### What You'll Learn

- **Java GUI Development:** Gain practical experience with Swing for GUI development.
- **Networking:** Learn the fundamentals of socket programming for communication between applications.
- **Multithreading:** Understand how to handle concurrent tasks efficiently using threads.
- **File Operations:** Explore file I/O operations, including saving chat logs.
- **Event-Driven Programming:** Delve into event handling in Java applications.

---

## Clone DBMS Application

### Overview

This project is a Java-based database management system (DBMS) application designed to manage student records. It provides functionalities for inserting, selecting, updating, and deleting records, along with aggregation and sorting operations.

### Features

- **Insert Records:** Add new student records.
- **Select Records:** Retrieve student records based on criteria or list all records.
- **Update Records:** Modify existing student records.
- **Delete Records:** Remove student records.
- **Aggregate Functions:** Calculate maximum, minimum, average, and sum of student marks.
- **Sorting:** Sort student records by age or marks.
- **File Storage:** Save and load records from a CSV file.

### Versions

- **Version 1:** Basic implementation with core DBMS functionalities.
- **Version 2:** Added functionalities for updating, deleting, and sorting records. Records are stored in `student.csv`.
- **Version 3:** Improvements include duplicate record prevention using `hashCode` and enhanced code with Stream API.
- **Final Version:** Integrates all improvements from previous versions.

### How to Run

1. **Compile the Code:**
   ```bash
   javac DbmsApplicationMain.java
   ```

2. **Run the Application:**
   ```bash
   java DbmsApplicationMain
   ```

3. **Interact with the DBMS:**
   - Insert, select, update, delete, sort records, and perform aggregate functions using custom commands.

### Knowledge Gained

- **Database Management:** Understanding basic DBMS operations.
- **File Handling:** Managing data persistence using CSV files.
- **Code Optimization:** Preventing duplicate records and enhancing code efficiency with Stream API.

---

## Packer-Unpacker (File Packer Unpacker)

### Overview

The **Packer-Unpacker** is a Java-based desktop application that allows users to pack and unpack files. It features a user-friendly graphical interface built using Swing, designed for secure and efficient file management.

### Features

- **User Authentication:** Secure login system with username and password validation.
- **File Packing:** Pack files with specific extensions into a single output file.
- **File Unpacking:** Extract files from a packed file with format verification.
- **Security Features:** Password strength validation and login attempt limitations.

### How to Run

1. **Clone the Repository:** Download or clone the repository to your local machine.
2. **Compile the Code:**
   ```bash
   javac *.java
   ```
3. **Create and Run the JAR File:**
   ```bash
   jar -cvfm packer.jar MANIFEST.MF *.class
   java -jar packer.jar
   ```

### Knowledge Gained

- **Swing Framework:** Building GUI applications in Java.
- **Event Handling:** Implementing event listeners and handling user interactions.
- **File Handling:** Practical knowledge of file I/O operations.
- **Threading and Exception Handling:** Implementing multithreading and error handling in Java.

### Future Enhancements

- **Encryption:** Implement file encryption for enhanced security.
- **User Management:** Support multiple users with different roles.
- **File Compression:** Add file compression functionality.

---

Each project in this repository offers a unique learning experience, from networking and GUI development to database management and file handling. Explore the projects to enhance your Java programming skills!

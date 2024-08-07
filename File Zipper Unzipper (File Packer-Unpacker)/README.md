

# Packer-Unpacker

## Project Overview

The **Packer-Unpacker** is a Java-based desktop application that allows users to pack and unpack files. This application features a user-friendly graphical interface built using Swing, making it simple to navigate and use. The main goal of this project is to provide a secure and efficient way to manage file packaging and unpackaging processes, which can be particularly useful for developers and users who need to manage multiple files as a single entity.

## Features

- **User Authentication:** Secure login system with username and password validation.
- **File Packing:** Allows packing of files with specific extensions (e.g., `.txt`, `.c`, `.java`, `.cpp`) into a single output file.
- **File Unpacking:** Extracts files from a packed file, verifying the file format for integrity.
- **Password Strength Validation:** Checks for weak passwords during login.
- **Attempts Limitation:** Limits the number of login attempts to enhance security.
- **Customizable User Interface:** Aesthetic and functional UI with options to pack and unpack files easily.

## Advantages

- **Security:** Password validation and limited login attempts protect against unauthorized access.
- **Efficiency:** Packing multiple files into a single file makes file management more convenient.
- **User-Friendly:** Intuitive graphical interface that requires no prior technical knowledge to use.
- **Real-Time Feedback:** Features like CAPS LOCK warnings and password strength indicators enhance user experience.
- **Reusability:** Components of the project can be reused or extended for other Java-based applications.

## How to Run the Project

### Prerequisites

- **Java Development Kit (JDK):** Ensure you have JDK 8 or higher installed.
- **IDE:** You can use any Java-compatible IDE such as IntelliJ IDEA, Eclipse, or NetBeans.

### Steps to Run

1. **Clone the Repository:**
   - Download or clone the repository to your local machine.
   - Ensure all the Java files (`PackerMain.java`, `Template.java`, `PackerNextPage.java`, `PackFront.java`, `Packer.java`, `UnpackFront.java`, `Unpack.java`) are in the same directory.

2. **Compile the Java Files:**
   - Open your command prompt or terminal.
   - Navigate to the directory where your Java files are located.
   - Compile the files using the following command:
     ```bash
     javac *.java
     ```

3. **Run the Command**
   - Make sure MANIFEST.MF file should be present
  
   - 1.
     ```bash
       jar -cvfm packer.jar MANIFEST.MF *.class
     ```
     
   - 2.
     ```bash
       jar -tf packer.jar
     ```

   - 3.
      ```bash
       java -jar packer.jar
      ```

   - 4. Or Directly click on executable file
        
   - screen shot of terminal

![Command prompt](Images/Command_Prompt.png)

4. **Using the Application:**
   - **Login:** Use the credentials `Admin` for both username and password.
   - **Pack Files:** Navigate through the options to select and pack files.
   - **Unpack Files:** Extract files from the packed archive.
  


### 1. Launch the Application
Include a screenshot showing the initial login screen of your application.
- **Description:** The initial login screen where users enter their credentials.


![Login Screen](Images/Login_screen.png)


### 2. Successful Login
Show a screenshot after the user has successfully logged in.
- **Description:** Successful login leading to the main application interface.


![Main Interface](Images/Main_Interface.png)


### 3. Packing Files
Capture a screenshot demonstrating the process of packing files.
- **Description:** The interface where users can select files to pack into an archive.


![Packing Files](Images/Packer_Packing_portal.png)


### 4. Unpacking Files
Include a screenshot of the unpacking process.
- **Description:** The interface where users can select an archive to unpack.


![Unpacking Files](Images/Unpacking.png)

## Knowledge Gained

- **Swing Framework:** Understanding of building GUI applications in Java using Swing components like `JFrame`, `JPanel`, `JButton`, `JLabel`, etc.
- **Event Handling:** Implementing event listeners and handling user interactions within a GUI application.
- **File Handling:** Practical knowledge of file I/O operations in Java, including reading, writing, and manipulating files.
- **Threading:** Basics of multithreading in Java for background tasks.
- **Exception Handling:** Implementing custom exceptions and handling errors effectively in Java.
- **Java Design Patterns:** Understanding the use of patterns like Template Method and Singleton.

## Future Enhancements

- **Encryption:** Implement file encryption to enhance the security of packed files.
- **User Management:** Expand the authentication system to support multiple users with different roles.
- **File Compression:** Add functionality to compress files to reduce the size of the packed archive.
- **Logging:** Implement logging to track user actions and system events for audit purposes.


# Packer-Unpacker

## Demo

### 1. Launch the Application
![Login Screen](Images/Login_screen.png)

### 2. Successful Login
![Main Interface](Images/Main_Interface.png)

### 3. Packing Files
![Packing Files](Images/Packer_Packing_portal.png)

### 4. Unpacking Files
![Unpacking Files](Images/Unpacking.png)



  


## Conclusion

The **Packer-Unpacker** project is a practical example of using Java to create a functional, user-friendly application. It serves as a valuable learning tool for understanding the fundamentals of GUI development, file handling, and user interaction in Java. This project can be expanded and enhanced to meet more complex requirements, making it a solid foundation for further development.



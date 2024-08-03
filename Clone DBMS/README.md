

# Clone DBMS Application

## Overview

This project is a Java-based database management system (DBMS) application designed to manage student records. It provides functionalities for inserting, selecting, updating, and deleting records, along with aggregation and sorting operations. The project is structured in multiple versions, each enhancing the previous one with additional features and improvements.

## Features

- **Insert Records**: Add new student records.
- **Select Records**: Retrieve student records based on criteria or list all records.
- **Update Records**: Modify existing student records.
- **Delete Records**: Remove student records.
- **Aggregate Functions**: Calculate maximum, minimum, average, and sum of student marks.
- **Sorting**: Sort student records by age or marks.
- **File Storage**: Save and load records from a CSV file.

## Versions

### Version 1: `DbmsApplication1.java`
- Basic implementation with core DBMS functionalities.

### Version 2: `DbmsApplication2.java`
- Added functionalities:
  - Update records
  - Delete records
  - Sort records
- **File Storage**: Records are stored in `student.csv`.

### Version 3: `DbmsApplication3.java`
- Improvements:
  - Duplicate record prevention using `hashCode`
  - Enhanced code with Stream API

### Final Version: `DbmsApplicationMain.java`
- Integrates all improvements from previous versions.


## Code Links

- [Version 1 - DbmsApplication1.java](src/DbmsApplication1.java)
- [Version 2 - DbmsApplication2.java](src/DbmsApplication2.java)
- [Version 3 - DbmsApplication3.java](src/DbmsApplication3.java)
- [Final Version - DbmsApplicationMain.java](src/DbmsApplicationMain.java)

## How to Run

1. **Compile the Code**:
   ```bash
   javac DbmsApplicationMain.java
   ```

2. **Run the Application**:
   ```bash
   java DbmsApplicationMain
   ```

3. **Interact with the DBMS**:
   - **Insert a Record**:
     ```
     insert into student values 'John' 20 85
     ```
   - **Select All Records**:
     ```
     select * from student
     ```
   - **Select Records by Roll Number**:
     ```
     select * from student where Rno = 1
     ```
   - **Select Records by Name**:
     ```
     select * from student where Name = 'John'
     ```
   - **Update a Record**:
     ```
     update student set Name = 'John Doe', Marks = 90 where Rno = 1
     ```
   - **Delete a Record**:
     ```
     delete from student where Rno = 1
     ```
   - **Sort Records**:
     ```
     select sort by age
     ```
   - **Aggregate Functions**:
     ```
     select MAX marks from student
     select MIN marks from student
     select AVG marks from student
     select SUM marks from student
     ```

4. **Exit the DBMS**:
   ```
   exit
   ```

## Sample Input and Output

- **Input**:
  ```
  insert into student values 'Alice' 22 75
  select * from student
  select MAX marks from student
  exit
  ```

- **Output**:
  ```
  DBMS started successfully...
  Table Schema created successfully...
  Record inserted successfully.
  Records from the student table are:
  1 Alice 22 75
  Maximum marks are: 75
  Thank you for using DBMS...
  ```

## Knowledge Gained

Through this project, I have gained a deeper understanding of database management systems, including fundamental operations such as inserting, updating, deleting, and querying records. I have learned to handle file I/O for data persistence using CSV files, implement record uniqueness using hashing, and enhance code efficiency with the Stream API. This project has strengthened my skills in Java programming, particularly in the context of data management and application design, and provided practical insights into the development and optimization of DBMS applications.


## Notes

- After the second version, student records are stored in `student.csv` file.
- The `Generator` static variable is used to ensure unique roll numbers for new records.
- The DBMS application does not support complex SQL queries but provides a fundamental understanding of DBMS operations.



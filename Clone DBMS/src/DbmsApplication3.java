import java.io.*;
import java.util.*;

public class DbmsApplication3 {
    public static void main(String[] args) {
        DBMS obj = new DBMS();
        obj.StartDBMS();

     
    }
}

class Student {
    public int Rno;
    public String Name;
    public int Age;
    public int Marks;

    public static int Generator;

    static {
        Generator = 0;
    }

    public Student(String str, int X, int Y) {
        this.Rno = ++Generator;
        this.Name = str;
        this.Age = X;
        this.Marks = Y;
    }

    public String toCSV() {
        return Rno + "," + Name + "," + Age + "," + Marks;
    }

    public static Student fromCSV(String csv) {
        String[] tokens = csv.split(",");
        Student student = new Student(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        student.Rno = Integer.parseInt(tokens[0]);
        Generator = Math.max(Generator, student.Rno);
        return student;
    }

    public void Display() {
        System.out.println(this.Rno + " " + this.Name + " " + this.Age + " " + this.Marks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Rno == student.Rno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rno);
    }
}

class DBMS {
    public LinkedList<Student> lobj;
    private final String FILE_PATH = "students.txt";

    public DBMS() {
        lobj = new LinkedList<>();
        LoadFromFile();
    }

    public void StartDBMS() {
        System.out.println("DBMS started successfully...");
        System.out.println("Table Schema created successfully...");

        String Query;
        String[] Tokens;

        Scanner sobj = new Scanner(System.in);
        while (true) {
            System.out.print("DBMS : ");
            Query = sobj.nextLine().trim();
            Tokens = Query.split("\\s+");

            if ("exit".equalsIgnoreCase(Tokens[0])) {
                SaveToFile();
                System.out.println("Thank you for using DBMS...");
                break;
            } else if (Tokens.length == 7 && "insert".equalsIgnoreCase(Tokens[0]) && "into".equalsIgnoreCase(Tokens[1]) && "student".equalsIgnoreCase(Tokens[2]) && "values".equalsIgnoreCase(Tokens[3])) {
                // Handle insert command
                String name = Tokens[4];
                int age = Integer.parseInt(Tokens[5]);
                int marks = Integer.parseInt(Tokens[6]);
                InsertIntoTable(name, age, marks);
            } else if (Tokens.length == 4 && "select".equalsIgnoreCase(Tokens[0]) && "*".equalsIgnoreCase(Tokens[1]) && "from".equalsIgnoreCase(Tokens[2]) && "student".equalsIgnoreCase(Tokens[3])) {
                SelectFrom();
            } else if (Tokens.length == 8 && "select".equalsIgnoreCase(Tokens[0]) && "*".equalsIgnoreCase(Tokens[1]) && "from".equalsIgnoreCase(Tokens[2]) && "student".equalsIgnoreCase(Tokens[3]) && "where".equalsIgnoreCase(Tokens[4])) {
                String columnName = Tokens[5];
                String columnValue = Tokens[7].replace("'", ""); // Remove quotes from the value
                if ("Rno".equalsIgnoreCase(columnName)) {
                    SelectFrom(Integer.parseInt(columnValue));
                } else if ("Name".equalsIgnoreCase(columnName)) {
                    SelectFrom(columnValue);
                }
            } else if (Tokens.length == 7 && "delete".equalsIgnoreCase(Tokens[0]) && "from".equalsIgnoreCase(Tokens[1]) && "student".equalsIgnoreCase(Tokens[2]) && "where".equalsIgnoreCase(Tokens[3])) {
                int rollNumber = Integer.parseInt(Tokens[6]);
                DeleteFrom(rollNumber);
            } else if (Tokens.length >= 7 && "update".equalsIgnoreCase(Tokens[0]) && "student".equalsIgnoreCase(Tokens[1]) && "set".equalsIgnoreCase(Tokens[2])) {
                // Extract the parts of the command
                int setIndex = Query.indexOf("set");
                int whereIndex = Query.indexOf("where");

                if (setIndex != -1 && whereIndex != -1) {
                    String setClause = Query.substring(setIndex + 4, whereIndex).trim(); // Extracts "Name = 'Raj', Marks = 75"
                    String whereClause = Query.substring(whereIndex + 5).trim(); // Extracts "Rno = 2"

                    // Parse the set clause
                    String[] setParts = setClause.split(",");
                    String namePart = setParts[0].trim();
                    String marksPart = setParts[1].trim();

                    String newName = namePart.split("=")[1].trim().replace("'", "");
                    int newMarks = Integer.parseInt(marksPart.split("=")[1].trim());

                    // Parse the where clause
                    String rollNoPart = whereClause.split("=")[1].trim();
                    int rollNumber = Integer.parseInt(rollNoPart);

                    // Call the update method
                    UpdateRecord(rollNumber, newName, newMarks);
                }
            } else if (Tokens.length == 4 && "select".equalsIgnoreCase(Tokens[0]) && "sort".equalsIgnoreCase(Tokens[1]) && "by".equalsIgnoreCase(Tokens[2])) {
                SortBy(Tokens[3]);
            } else if (Tokens.length == 5 && "select".equalsIgnoreCase(Tokens[0]) && Tokens[1].equalsIgnoreCase("MAX") && Tokens[2].equalsIgnoreCase("marks") && Tokens[3].equalsIgnoreCase("from") && Tokens[4].equalsIgnoreCase("student")) {
                System.out.println("Maximum marks are: " + Aggregate_Max());
            } else if (Tokens.length == 5 && "select".equalsIgnoreCase(Tokens[0]) && Tokens[1].equalsIgnoreCase("MIN") && Tokens[2].equalsIgnoreCase("marks") && Tokens[3].equalsIgnoreCase("from") && Tokens[4].equalsIgnoreCase("student")) {
                System.out.println("Minimum marks are: " + Aggregate_Min());
            } else if (Tokens.length == 5 && "select".equalsIgnoreCase(Tokens[0]) && Tokens[1].equalsIgnoreCase("AVG") && Tokens[2].equalsIgnoreCase("marks") && Tokens[3].equalsIgnoreCase("from") && Tokens[4].equalsIgnoreCase("student")) {
                System.out.println("Average of marks are: " + Aggregate_Avg());
            } else if (Tokens.length == 5 && "select".equalsIgnoreCase(Tokens[0]) && Tokens[1].equalsIgnoreCase("SUM") && Tokens[2].equalsIgnoreCase("marks") && Tokens[3].equalsIgnoreCase("from") && Tokens[4].equalsIgnoreCase("student")) {
                System.out.println("Sum of marks are: " + Aggregate_Sum());
            } else {
                System.out.println("Invalid query.");
            }
        }
        sobj.close();
    }

    public void InsertIntoTable(String name, int age, int marks) {
        // Check for duplicate records
        for (Student s : lobj) {
            if (s.Name.equalsIgnoreCase(name) && s.Age == age && s.Marks == marks) {
                System.out.println("Duplicate record found. Insertion aborted.");
                return;
            }
        }
        Student sobj = new Student(name, age, marks);
        lobj.add(sobj);
        SaveToFile();
        System.out.println("Record inserted successfully.");
    }

    public void SelectFrom() {
        System.out.println("Records from the student table are:");
        for (Student sref : lobj) {
            sref.Display();
        }
    }

    public void SelectFrom(int no) {
        System.out.println("Records from the student table are:");
        for (Student sref : lobj) {
            if (sref.Rno == no) {
                sref.Display();
                return;
            }
        }
        System.out.println("No record found with roll number: " + no);
    }

    public void SelectFrom(String name) {
        System.out.println("Records from the student table are:");
        for (Student sref : lobj) {
            if (sref.Name.equalsIgnoreCase(name)) {
                sref.Display();
                return;
            }
        }
        System.out.println("No record found with name: " + name);
    }

    public void DeleteFrom(int rollNo) {
        Iterator<Student> iterator = lobj.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.Rno == rollNo) {
                iterator.remove();
                SaveToFile();
                System.out.println("Record deleted successfully.");
                return;
            }
        }
        System.out.println("No record found with roll number: " + rollNo);
    }

    public void UpdateRecord(int rollNo, String name, int marks) {
        for (Student sref : lobj) {
            if (sref.Rno == rollNo) {
                sref.Name = name;
                sref.Marks = marks;
                SaveToFile();
                System.out.println("Record updated successfully.");
                return;
            }
        }
        System.out.println("No record found with roll number: " + rollNo);
    }

    public int Aggregate_Max() {
        return lobj.stream().mapToInt(s -> s.Marks).max().orElse(0);
    }

    public int Aggregate_Min() {
        return lobj.stream().mapToInt(s -> s.Marks).min().orElse(0);
    }

    public double Aggregate_Avg() {
        return lobj.stream().mapToInt(s -> s.Marks).average().orElse(0.0);
    }

    public int Aggregate_Sum() {
        return lobj.stream().mapToInt(s -> s.Marks).sum();
    }

    public void SortBy(String field) {
        if ("age".equalsIgnoreCase(field)) {
            lobj.sort(Comparator.comparingInt(s -> s.Age));
        } else if ("marks".equalsIgnoreCase(field)) {
            lobj.sort(Comparator.comparingInt(s -> s.Marks));
        }
        SelectFrom(); // Display sorted records
    }

    private void SaveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Student s : lobj) {
                pw.println(s.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void LoadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Student s = Student.fromCSV(line);
                    lobj.add(s);
                }
            } catch (IOException e) {
                System.out.println("Error loading from file: " + e.getMessage());
            }
        }
    }

    public void ExitDBMS() {
        SaveToFile();
    }
}

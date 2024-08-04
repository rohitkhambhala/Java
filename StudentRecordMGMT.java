import java.util.Scanner;

class Student {
    public int ID;
    private int age;
    private String Name;
    private String department;

    public Student() {
        ID = 0;
        age = 0;
        Name = "";
        department = "";
    }

    public Student(int ID, int age, String Name, String department) { // Perameterised Constructor to take input:
        this.ID = ID;
        this.Name = Name;
        this.age = age;
        this.department = department;
    }

    public int getID() {
        return ID;
    }

    public int getage() {
        return age;
    }

    public String getName() {
        return Name;
    }

    public String getdepartment() {
        return department;
    }

    public void display() {
        System.out.println("ID : " + ID + "Name : " + Name + "Age" + age + "Department" + department);
    }

}

class StudentRecord extends Student {
    Student[] students = new Student[2];
    private int count;

    public StudentRecord() {
        count = 0;
    }

    // Call superclass constructor with required parameters
    public StudentRecord(int ID, int age, String Name, String department) {
        super(ID, age, Name, department);
    }

    public void addStudent(int ID, int age, String Name, String department) {
        if (count < students.length) {
            students[count] = new Student(ID, age, Name, department);
            count++;
        } else {
            System.out.println("Student record is full.");
        }
    }

    public void displayStudents() {
        for (int i = 0; i < count; i++) {
            students[i].display();
        }
    }

    public void search(int iD) {
        if (iD == ID)
            for (int i = 0; i < count; i++) {
                students[i].display();
            }
        else {
            System.out.println("Student not found");
        }
    }
}

class StudentRecordMGMT extends StudentRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;

        StudentRecord studentRecord = new StudentRecord();
        while (true) {
            System.out.println("Enter 1: Add Student");
            System.out.println("Enter 2: Search Student");
            System.out.println("Enter 3: Display Student");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    int id, age;
                    String name, Department;
                    System.out.println("Enter Student ID");
                    id = sc.nextInt();
                    System.out.println("Enter Student Age");
                    age = sc.nextInt();
                    System.out.println("Enter Student Name");
                    name = sc.next();
                    System.out.println("Enter Student Department");
                    Department = sc.next();
                    studentRecord = new StudentRecord(id, age, name, Department);
                    studentRecord.addStudent(id, age, name, Department);
                    break;

                case 2:
                    int iD;
                    System.out.println("Enter Student ID to Search");
                    id = sc.nextInt();
                    studentRecord.search(id);
                    break;
                case 3:
                    studentRecord.displayStudents();
                    break;
                default:
                    System.exit(0);
            }
        }

    }
}
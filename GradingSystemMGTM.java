import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int maxStudents, int maxGrades) {
        students = new Student[maxStudents];
        grades = new Grade[maxGrades];
        courseCredits = new int[maxStudents];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        students[studentCount++] = student;
    }

    public void addGrade(Grade grade) {
        grades[gradeCount++] = grade;
    }

    public void addCourseCredits(int courseID, int credits) {
        courseCredits[courseID] = credits;
    }

    public double calculateGPA(int studentID) {
        int totalCredits = 0;
        int totalPoints = 0;

        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                int courseID = grades[i].getCourseID();
                char grade = grades[i].getGrade();
                int credits = courseCredits[courseID];
                totalCredits += credits;
                totalPoints += credits * gradeToPoints(grade);
            }
        }

        return totalCredits == 0 ? 0 : (double) totalPoints / totalCredits;
    }

    public int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: return 0;
        }
    }
}


public class GradingSystemMGTM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GradingSystem gradingSystem = new GradingSystem(100, 100); // assuming max 100 students and 100 grades

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    gradingSystem.addStudent(new Student(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int studentIdForGrade = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter grade: ");
                    char grade = scanner.next().charAt(0);
                    gradingSystem.addGrade(new Grade(studentIdForGrade, courseID, grade));
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    int courseIdForCredits = scanner.nextInt();
                    System.out.print("Enter credits: ");
                    int credits = scanner.nextInt();
                    gradingSystem.addCourseCredits(courseIdForCredits, credits);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    int studentIdForGPA = scanner.nextInt();
                    double gpa = gradingSystem.calculateGPA(studentIdForGPA);
                    System.out.println("GPA: " + gpa);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
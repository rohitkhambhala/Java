import java.util.Scanner;

class Course {
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment {
    private int[][] studentCourses;
    private int[] count;

    public Enrollment(int numStudents, int numCourses) {
        studentCourses = new int[numStudents][numCourses];
        count = new int[numStudents];
    }

    public void enroll(int studentID, int courseID) {
        studentCourses[studentID][count[studentID]++] = courseID;
    }

    public void drop(int studentID, int courseID) {
        for (int i = 0; i < count[studentID]; i++) {
            if (studentCourses[studentID][i] == courseID) {
                for (int j = i; j < count[studentID] - 1; j++) {
                    studentCourses[studentID][j] = studentCourses[studentID][j + 1];
                }
                count[studentID]--;
                break;
            }
        }
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) {
        System.out.println("Student " + studentID + " is enrolled in:");
        for (int i = 0; i < count[studentID]; i++) {
            int courseID = studentCourses[studentID][i];
            for (Course course : courseCatalog) {
                if (course.getCourseID() == courseID) {
                    System.out.println(course);
                }
            }
        }
    }
}


public class courseEnrollment {
    private Course[] courseCatalog;
    private Enrollment enrollment;

    public courseEnrollment(Course[] courseCatalog, int numStudents) {
        this.courseCatalog = courseCatalog;
        this.enrollment = new Enrollment(numStudents, courseCatalog.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define course catalog
        Course[] courses = {
            new Course(1, "Mathematics", 3),
            new Course(2, "Physics", 4),
            new Course(3, "Chemistry", 3)
        };

        courseEnrollment courseEnrollment = new courseEnrollment(courses, 10);

        while (true) {
            System.out.println("1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View enrolled courses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break;
            }

            System.out.print("Enter student ID: ");
            int studentID = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter course ID to enroll: ");
                    int courseIDToEnroll = scanner.nextInt();
                    courseEnrollment.enrollment.enroll(studentID, courseIDToEnroll);
                    break;
                case 2:
                    System.out.print("Enter course ID to drop: ");
                    int courseIDToDrop = scanner.nextInt();
                    courseEnrollment.enrollment.drop(studentID, courseIDToDrop);
                    break;
                case 3:
                    courseEnrollment.enrollment.getEnrolledCourses(studentID, courses);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
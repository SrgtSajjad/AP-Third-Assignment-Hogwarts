import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Educational {
    ArrayList<Teacher> positiveFeedback = new ArrayList<Teacher>();
    ArrayList<Teacher> negativeFeedback = new ArrayList<Teacher>();
    int score;

    public Student(String username, String password, String role) {
        super(username, password, role);
    }

    public static Student login() {
        Scanner scanner = new Scanner(System.in);
        Student student = null;
        System.out.print("Username: ");
        String username = scanner.next();
        for (Student student1 : Educational.students) {
            if (Objects.equals(student1.getUsername(), username)) {
                student = student1;
                break;
            }
        }
        if (student != null) {
            while (true) {
                System.out.print("Password: ");
                String password = scanner.next();
                if (student.validatePassword(password)) {
                    System.out.println("-Account Verified-\nWelcome " + student.getUsername());
                    break;
                } else if (Objects.equals(password, "exit"))
                    break;
                System.out.println("Error: Authentication failed,Please re-enter your password\n\nType \"exit\" to leave to menu");

            }
            return student;
        } else {
            System.out.println("Error: User not available, please try again");
        }
        System.out.println("Error: Login failed");
        return null;

    }

    public void viewTeachersList() {
        int i = 0;
        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.teacher.getUsername() + " teacher of " + course.title);
        }

    }

    public void takeCourses() {
        int i = 0;
        System.out.println("0. Exit");
        for (Course course : Course.courses) {
            i++;
            System.out.println(i + ". " + course.title + " Teacher: " + course.teacher.getUsername());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the course you would like to take: ");
            i = scanner.nextInt();
            if (i-1 < Course.courses.size() && i-1 >= 0) {
                Course course = Course.courses.get(i-1);
                getCoursesTaken().add(course);
                break;
            }
            else if (i == 0) {
                break;
            }
            System.out.print("Error: Course unavailable, please choose from the list above");
        }
    }

    public void scoreTeachers() {
        viewTeachersList();
        System.out.print("Choose a teacher's number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        Teacher teacher = getCoursesTaken().get(number - 1).teacher;
        boolean flag = true;
        while (flag) {
            System.out.println(teacher.getUsername() + "\n0. Cancel\n1. Give positive feedback\n2. Give negative feedback\n 3. retract feedback");
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    if (!teacher.positiveFeedback.contains(this)) {
                        teacher.positiveFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this teacher, retract first to change it");
                    break;
                case 2:
                    if (!teacher.negativeFeedback.contains(this)) {
                        teacher.negativeFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this teacher, retract first to change it");
                    break;
                case 3:
                    teacher.positiveFeedback.remove(this);
                    teacher.negativeFeedback.remove(this);
                    break;

            }

        }
    }

    public void menu() {
    }
}

import java.util.Objects;
import java.util.Scanner;

public class Hogwarts extends Account {

    Teacher hogwartsTeacher = new Teacher("Dumbledore", "1234", "teacher"); //  teacher account for accessing teacher's menu
    Student hogwartsStudent = new Student("Jake", "1234", "student"); // student account for accessing student's menu
    public Hogwarts() {
        super("Dumbledore", "VoldemortShouldDie", "Hogwarts");
    }

    public static Hogwarts login() {
        System.out.println("~~| Hogwarts Login |~~\n");
        Scanner scanner = new Scanner(System.in);
        Hogwarts hogwarts = new Hogwarts();
        System.out.print("Username: ");
        String username = scanner.next();
        if (Objects.equals(username, hogwarts.getUsername())) {
            while (true) {
                System.out.print("Password: ");
                String password = scanner.next();
                if (hogwarts.validatePassword(password)) {
                    System.out.println("-Account Verified-\nWelcome " + hogwarts.getUsername());
                    break;
                } else if (Objects.equals(password, "exit"))
                    return null;
                System.out.println("Error: Authentication failed,Please re-enter your password\n\nType \"exit\" to leave to menu");
            }
            return hogwarts;
        }
        else
            System.out.println("Error: Authentication failed,Please re-enter your username");
        return null;

    }
    public void viewAllTeachers() {
        int i = 0;
        for (Teacher teacher : Educational.getTeachers()) {
            i++;
            System.out.println(i + ". " + teacher.getFullName());
        }
    }

    public void viewAllStudents() {
        int i = 0;
        for (Student student : Educational.getStudents()) {
            i++;
            System.out.println(i + ". " + student.getFullName());
        }
    }

    public void viewAllCourses() {
        int i = 0;
        for (Course course : Course.getCourses()) {
            i++;
            System.out.println(i + ". " + course.getTitle());
        }
    }

    public void menu() {
    }
}

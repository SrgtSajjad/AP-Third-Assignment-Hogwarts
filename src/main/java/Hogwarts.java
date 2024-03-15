import java.util.Objects;
import java.util.Scanner;

public class Hogwarts extends Account {
    public Hogwarts() {
        super("Dumbledore", "VoldemortShouldDie", "Hogwarts");
    }

    public static Hogwarts login() {
        System.out.println("~~| Hogwarts Login |~~\n");
        Scanner scanner = new Scanner(System.in);
        Hogwarts hogwarts = new Hogwarts();
        System.out.print("Username: ");
        String username = scanner.next();
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

    // TODO: Define Attributes


    // TODO: Define Functionalities
    public void viewAllTeachers() {
        int i = 0;
        for (Teacher teacher : Educational.teachers) {
            i++;
            System.out.println(i + ". " + teacher.getFullName());
        }
    }

    public void viewAllStudents() {
        int i = 0;
        for (Student student : Educational.students) {
            i++;
            System.out.println(i + ". " + student.getFullName());
        }
    }

    public void viewAllCourses() {
        int i = 0;
        for (Course course : Course.courses) {
            i++;
            System.out.println(i + ". " + course.getTitle());
        }
    }

    public void menu() {
    }
}

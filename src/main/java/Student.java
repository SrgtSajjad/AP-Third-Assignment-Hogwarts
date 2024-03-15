import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Educational {
    ArrayList<Integer> scores = new ArrayList<Integer>();
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
                }
                System.out.println("Error: Authentication failed,Please re-enter your password");

            }
            return student;
        }
        else {
            System.out.println("Error: User not available, please try again");
        }
        System.out.println("Error: Login failed");
        return null;

    }


    public void menu() {
    }
}

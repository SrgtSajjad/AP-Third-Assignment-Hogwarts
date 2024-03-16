import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Assistant extends Account{
    static ArrayList<Assistant> assistants = new ArrayList<>();
    public Assistant(String username, String password, String role) {
        super(username, password, role);
    }

    public static Assistant login() {
        System.out.println("~~| Assistant Login |~~\n");
        Scanner scanner = new Scanner(System.in);
        Assistant assistant = null;
        System.out.print("Username: ");
        String username = scanner.next();
        for (Assistant assistant1 : Assistant.assistants) {
            if (Objects.equals(assistant1.getUsername(), username)) {
                assistant = assistant1;
                break;
            }
        }
        if (assistant != null) {
            while (true) {
                System.out.print("Password: ");
                String password = scanner.next();
                if (assistant.validatePassword(password)) {
                    System.out.println("-Account Verified-\nWelcome " + assistant.getUsername());
                    break;
                } else if (Objects.equals(password, "exit"))
                    break;
                System.out.println("Error: Authentication failed,Please re-enter your password\n\nType \"exit\" to leave to menu");

            }
            return assistant ;
        } else {
            System.out.println("Error: User not available, please try again");
        }
        System.out.println("Error: Login failed");
        return null;
    }

    public void createCourse() {
        System.out.println("~~| Course Creation |~~\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Title of course: ");
        String title = scanner.nextLine();
        System.out.println("Confirm creation of the course: " + title + "?\n1. Yes\n2.No");
        boolean flag = true;
        int confirmation = scanner.nextInt();
        while (flag) {
            switch (confirmation) {
                case 1:
                    Course course = new Course(title, null);
                    Course.getCourses().add(course);
                    flag = false;
                    break;
                case 2:
                    flag = false;
                    break;
                default:
                    System.out.println("Error: Please choose one of the options above");
                    break;
            }
        }

    }
    public void menu() {
    }
}

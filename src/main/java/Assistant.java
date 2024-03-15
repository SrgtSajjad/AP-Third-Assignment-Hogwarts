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

    public void menu() {
    }
}

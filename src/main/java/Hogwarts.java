import java.util.Objects;
import java.util.Scanner;

public class Hogwarts extends Administrator {

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
        } else
            System.out.println("Error: Authentication failed,Please re-enter your username");
        return null;

    }


    public void menu() {
        boolean flag = true;
        while (flag) {
            System.out.println("~~| Assistant Menu |~~\n" +
                    "\nUsername: " + getUsername() +
                    "\n1. View Account Requests" +
                    "\n2. View Course Requests" +
                    "\n3. Create a Course" +
                    "\n4. Remove Teacher" +
                    "\n5. Remove Student" +
                    "\n6. View Courses and Students Participating" +
                    "\n7. Check Teacher Profiles" +
                    "\n8. Check Student Profiles" +
                    "\n0. Exit");
            Scanner scanner = new Scanner(System.in);
            int command;
            System.out.print("Your command: ");
            command = scanner.nextInt();
            switch (command) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    removeTeacher();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    viewCoursesAndStudentsList();
                    break;
                case 7:
                    checkTeacherProfiles();
                    break;
                case 8:
                    checkStudentProfiles();
                    break;
                default:
                    System.out.println("Error: Option is not available, please choose from the list above");
            }
            System.out.println("--------------------------------");
        }
    }
}

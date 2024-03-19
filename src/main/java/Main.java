import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        createData();
        while (true) {
            System.out.println("1. Login\n2. Sign Up\n3. Exit");
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            if (command == 3)
                break;
            runMenu(command);
        }
    }

    private static void createData() {
        Teacher teacher1 = new Teacher("Snape", "1234", "teacher");
        teacher1.setSignedUp(true);
        Student student1 = new Student("Harry", "1234", "student");
        student1.setSignedUp(true);
        Course course1 = new Course("Magic", null);
        Course.getCourses().add(course1);
        Educational.getTeachers().add(teacher1);
        Educational.getStudents().add(student1);


    }

    public static void runMenu(int command) {
        switch (command) {
            case 1:
                login();
                break;
            case 2:
                signUp();
                break;
            default:
                System.out.println("Invalid Input!");
        }
    }

    public static void signUp() {
        int role;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Your role:\n0. Exit\n1. Teacher\n2. Student");
            role = scanner.nextInt();
            if (role >= 0 && role < 3) {
                break;
            }
            System.out.println("Error: Role is invalid, please choose an option from above");
        }
        String username;
        if (role == 1) {
            boolean accountable = true;
            while (true) {
                System.out.print("Username: ");
                username = scanner.next();
                if (Objects.equals(username, "exit")) {
                    accountable = false;
                    break;
                }
                for (Teacher teacher : Educational.getTeachers()) {
                    if (Objects.equals(username, teacher.getUsername())) {
                        accountable = false;
                        System.out.print("Error: There is an account with this username\n(Type \"exit\" to exit to menu");
                        break;
                    }
                }
                if (accountable)
                    break;
            }

            if (accountable) {
                System.out.print("Password: ");
                String password = scanner.next();
                System.out.println("Please wait for an admin to accept your request");
                SignUpRequest signUpRequest = new SignUpRequest(username, password, "teacher");
                Assistant.getSignUpRequests().add(signUpRequest);
            }
        } else if (role == 2) {
            boolean accountable = true;
            while (true) {
                System.out.print("Username: ");
                username = scanner.next();
                if (Objects.equals(username, "exit")) {
                    accountable = false;
                    break;
                }
                for (Student student : Educational.getStudents()) {
                    if (Objects.equals(username, student.getUsername())) {
                        accountable = false;
                        System.out.print("Error: There is an account with this username\n(Type \"exit\" to exit to menu");
                        break;
                    }
                }
                if (accountable)
                    break;
            }

            if (accountable) {
                System.out.print("Password: ");
                String password = scanner.next();
                System.out.println("Please wait for an admin to accept your request");
                SignUpRequest signUpRequest = new SignUpRequest(username, password, "student  ");
                Assistant.getSignUpRequests().add(signUpRequest);
            }
        }

    }

    public static void login() {
        int role;
        while (true) {
            System.out.println("Your role:\n0. Exit\n1. Teacher\n2. Student\n3. Assistant\n4. Hogwarts");
            Scanner scanner = new Scanner(System.in);
            role = scanner.nextInt();
            if (role >= 0 && role < 5) {
                break;
            }
            System.out.println("Error: Role is invalid, please choose an option from above");
        }
        if (role == 1) {
            Teacher teacher = Teacher.login();
            if (teacher != null) {
                if (teacher.isNotSignedUp())
                    teacher.signUp();
                teacher.menu();
            }
        } else if (role == 2) {
            Student student = Student.login();
            if (student != null) {
                if (student.isNotSignedUp())
                    student.signUp();
                student.menu();
            }
        } else if (role == 3) {
            Assistant assistant = Assistant.login();
            if (assistant != null) {
                assistant.menu();
            }
        } else if (role == 4) {
            Hogwarts hogwarts = Hogwarts.login();
            if (hogwarts != null) {
                hogwarts.menu();
            }
        }
    }
}

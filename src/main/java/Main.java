/*
    TODO: Import all the classes that you have defined and make use of them to build the program.
 */


import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            runMenu();
        }
    }

    public static void runMenu() {
        System.out.println("1. Login\n2. Sign Up");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1:
                login();
                break;
            case 2:
                signUp();
                break;
        }
        runMenu();
    }

    public static void signUp() {
        System.out.print("Your role: ");
        Scanner scanner = new Scanner(System.in);
        String role = scanner.next();
        if (Objects.equals(role, "teacher")) {
            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            System.out.println("Please wait for an admin to accept your request");
            Teacher teacher = new Teacher(username, password, role);
            Educational.teachers.add(teacher);
            //TODO admin_request
        }

    }

    public static void login() {
        int role;
        while (true) {
            System.out.println("Your role:\n1. Teacher\n2. Student");
            Scanner scanner = new Scanner(System.in);
            role = scanner.nextInt();
            if (role >= 0 && role < 6)
                break;
        }
        if (Objects.equals(role, 0)) {
            // do nothing to return to main menu
        } else if (Objects.equals(role, 1)) {
            Teacher teacher = Teacher.login();
            if (teacher != null) {
                if (!teacher.isSignedUp())
                    teacher.signUp();
                teacher.menu();
            }
        } else if (Objects.equals(role, 2)) {
            Student student = Student.login();
            if (student != null) {
                if (!student.isSignedUp())
                    student.signUp();
                student.menu();
            }
        }
    }
}

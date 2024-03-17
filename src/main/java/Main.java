/*
    TODO: Import all the classes that you have defined and make use of them to build the program.
 */


import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        createDataBase();
        while (true) {
            runMenu();
        }
    }

    private static void createDataBase() {
        Teacher teacher1 = new Teacher("Snape", "1234", "teacher");
        teacher1.setSignedUp(true);
        Student student1 = new Student("Harry", "1234", "student");
        student1.setSignedUp(true);
        Course course1 = new Course("Magic", null);
        Course.getCourses().add(course1);
        Educational.getTeachers().add(teacher1);
        Educational.getStudents().add(student1);


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
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        if (role == 1) {
            System.out.println("Please wait for an admin to accept your request");
            SignUpRequest signUpRequest = new SignUpRequest(username, password, "teacher");
            Assistant.getSignUpRequests().add(signUpRequest);
        } else if (role == 2) {
            System.out.println("Please wait for an admin to accept your request");
            SignUpRequest signUpRequest = new SignUpRequest(username, password, "student  ");
            Assistant.getSignUpRequests().add(signUpRequest);
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

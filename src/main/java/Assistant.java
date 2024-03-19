import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Assistant extends Administrator {
    static ArrayList<Assistant> assistants = new ArrayList<>();
    private static ArrayList<Message> courseRequests = new ArrayList<>();
    private static ArrayList<SignUpRequest> signUpRequests = new ArrayList<>();


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
            return assistant;
        } else {
            System.out.println("Error: User not available, please try again");
        }
        System.out.println("Error: Login failed");
        return null;
    }

    private void viewCourseRequests() {
        System.out.println("~~| Course Requests |~~\n");
        int i = 0;
        System.out.println("0. Exit");
        for (Message request : courseRequests) {
            i++;
            System.out.println(i + ". Title: " + request.text + " requested by " + request.writer.getUsername());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a requested course: ");
        while (true) {
            i = scanner.nextInt();
            if (i >= 0 && i <= courseRequests.size())
                break;
            System.out.println("Error: Please choose from the list above");
        }
        if (i != 0) {
            Message selectedRequest = courseRequests.get(i - 1);
            System.out.println("Selected course: " + selectedRequest.text + "\n0. Exit\n1. Approve\n2. Deny");
            while (true) {
                i = scanner.nextInt();
                if (i >= 0 && i < 3)
                    break;
                System.out.println("Error: Please choose from the list above");
            }

            switch (i) {
                case 0:
                    break;
                case 1:
                    Course course = new Course(selectedRequest.text, null);
                    Course.getCourses().add(course);
                    courseRequests.remove(selectedRequest);
                    System.out.println("Course added successfully");
                    break;
                case 2:
                    courseRequests.remove(selectedRequest);
                    System.out.println("Course request was denied");
                    break;

            }

        }

    }

    private void viewAccountRequests() {
        System.out.println("~~| Account Requests |~~\n");
        int i = 0;
        System.out.println("0. Exit");
        for (SignUpRequest request : signUpRequests) {
            i++;
            System.out.println(i + ". Username: " + request.username + ", Role: " + request.role);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose a request: ");
        while (true) {
            i = scanner.nextInt();
            if (i >= 0 && i <= signUpRequests.size())
                break;
            System.out.println("Error: Please choose from the list above");
        }
        if (i != 0) {
            SignUpRequest selectedRequest = signUpRequests.get(i - 1);
            System.out.println("Selected request: " + selectedRequest.username + " as a " + selectedRequest.role + "\n0. Exit\n1. Approve\n2. Deny");
            while (true) {
                i = scanner.nextInt();
                if (i >= 0 && i < 3)
                    break;
                System.out.println("Error: Please choose from the list above");
            }

            switch (i) {
                case 0:
                    break;
                case 1:
                    if (Objects.equals(selectedRequest.role, "teacher")) {
                        Teacher teacher = new Teacher(selectedRequest.username, selectedRequest.password, "teacher");
                        Educational.getTeachers().add(teacher);
                    } else if (Objects.equals(selectedRequest.role, "student")) {
                        Student student = new Student(selectedRequest.username, selectedRequest.password, "student");
                        Educational.getStudents().add(student);
                    } else if (Objects.equals(selectedRequest.role, "assistant")) {
                        Assistant assistant = new Assistant(selectedRequest.username, selectedRequest.password, "assistant");
                        Assistant.assistants.add(assistant);
                    }
                    signUpRequests.remove(selectedRequest);
                    System.out.println("Account created successfully");
                    break;
                case 2:
                    signUpRequests.remove(selectedRequest);
                    System.out.println("Account request was denied");
                    break;

            }

        }
    }

    public static ArrayList<Message> getCourseRequests() {
        return courseRequests;
    }

    public static ArrayList<SignUpRequest> getSignUpRequests() {
        return signUpRequests;
    }

    private void createCourse() {
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
                    System.out.println("Course created successfully");
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

    private void removeAssistant() {
        System.out.println("~~| Remove Assistant |~~\n");
        int i = 0;
        Assistant selectedAssistant = null;
        System.out.println("0. Exit");
        for (Assistant assistant : Assistant.assistants) {
            i++;
            System.out.println(i + ". " + assistant.getFullName());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            i = scanner.nextInt();
            if (i == 0) {
                break;
            } else if (i - 1 > 0 && i - 1 < Assistant.assistants.size()) {
                selectedAssistant = Assistant.assistants.get(i - 1);
                break;

            } else
                System.out.println("Error: Assistant unavailable, please choose from the list above");
        }

        if (selectedAssistant != null) {
            System.out.println("Confirm remove of the assistant: " + selectedAssistant.getUsername() + "?\n1. Yes\n2.No");
            boolean flag = true;
            int confirmation = scanner.nextInt();
            while (flag) {
                switch (confirmation) {
                    case 1:
                        Assistant.assistants.remove(selectedAssistant);
                        flag = false;
                        System.out.println("Assistant removed successfully");
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
                    viewAccountRequests();
                    break;
                case 2:
                    viewCourseRequests();
                    break;
                case 3:
                    createCourse();
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

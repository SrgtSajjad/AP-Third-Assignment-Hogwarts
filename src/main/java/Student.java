import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Educational {
    ArrayList<Account> positiveFeedback = new ArrayList<>();
    ArrayList<Account> negativeFeedback = new ArrayList<>();
    int score;

    public Student(String username, String password, String role) {
        super(username, password, role);
    }

    public static Student login() {
        System.out.println("~~| Student Login |~~\n");
        Scanner scanner = new Scanner(System.in);
        Student student = null;
        System.out.print("Username: ");
        String username = scanner.next();
        for (Student student1 : Educational.getStudents()) {
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
                } else if (Objects.equals(password, "exit"))
                    break;
                System.out.println("Error: Authentication failed,Please re-enter your password\n\nType \"exit\" to leave to menu");

            }
            return student;
        } else {
            System.out.println("Error: User not available, please try again");
        }
        System.out.println("Error: Login failed");
        return null;

    }

    public void viewTeachersList() {
        System.out.println("~~| Teacher's List |~~\n");
        int i = 0;
        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.getTeacher().getUsername() + " teacher of " + course.getTitle());
        }

    }

    public void takeCourses() {
        System.out.println("~~| Take Courses |~~\n");

        int i = 0;
        System.out.println("0. Exit");
        for (Course course : Course.getCourses()) {
            i++;
            String teacherName = "-Teacher unavailable-";
            if (course.getTeacher() != null)
                teacherName = course.getTeacher().getUsername();
            System.out.println(i + ". " + course.getTitle() + " Teacher: " + teacherName);
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the course you would like to take: ");
            i = scanner.nextInt();
            if (Course.getCourses().get(i - 1).participatingStudents.contains(this)) {
                System.out.print("You have already taken this course, please try another one");
            } else if (i - 1 < Course.getCourses().size() && i - 1 >= 0) {
                Course course = Course.getCourses().get(i - 1);
                getCoursesTaken().add(course);
                course.participatingStudents.add(this);
                break;
            } else if (i == 0) {
                break;
            } else
                System.out.print("Error: Course unavailable, please choose from the list above");
        }
    }

    public void scoreTeachers() {
        System.out.println("~~| Score Teachers |~~\n");
        viewTeachersList();
        System.out.print("Choose a teacher's number: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            number = scanner.nextInt();
            if (number - 1 >= 0 && number - 1 < getCoursesTaken().size())
                break;
            System.out.print("Error: Teacher not available, please choose from the list above ");

        }

        Teacher teacher = getCoursesTaken().get(number - 1).getTeacher();
        boolean flag = true;
        while (flag) {
            System.out.println(teacher.getUsername() + ":\n0. Exit\n1. Give positive feedback\n2. Give negative feedback\n3. retract feedback");
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    if (!(teacher.positiveFeedback.contains(this) || teacher.negativeFeedback.contains(this))) {
                        teacher.positiveFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this teacher, retract first to change it");
                    break;
                case 2:
                    if (!(teacher.positiveFeedback.contains(this) || teacher.negativeFeedback.contains(this))) {
                        teacher.negativeFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this teacher, retract first to change it");
                    break;
                case 3:
                    teacher.positiveFeedback.remove(this);
                    teacher.negativeFeedback.remove(this);
                    break;
                default:
                    System.out.println("Error: Please choose an option from above");
                    break;
            }

        }
    }

    public void viewAssignments() {
        System.out.println("~~| View Assignments |~~\n");
        int i = 0;
        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.getTitle() + " Assignment: " + course.getAssignment());
        }
    }

    public void commentTeachers() {
        System.out.println("~~| Comment Teachers |~~\n");
        viewTeachersList();
        System.out.print("Choose a teacher's number: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            number = scanner.nextInt();
            if (number - 1 >= 0 && number - 1 < getCoursesTaken().size())
                break;
            System.out.print("Error: Teacher not available, please choose from the list above ");

        }

        Teacher teacher = getCoursesTaken().get(number - 1).getTeacher();
        boolean hasCommented = false;
        int commentIndex = 0;
        for (Message comment : teacher.getComments()) {
            if (comment.writer == this) {
                hasCommented = true;
                commentIndex = teacher.getComments().indexOf(comment);
                break;
            }
        }
        boolean flag = true;
        while (flag) {
            System.out.println(teacher.getUsername() + ":\n0. Exit\n1. Set/Edit comment\n2. Delete comment");
            if (hasCommented)
                System.out.println("Your current comment: \"" + teacher.getComments().get(commentIndex).text + "\"");
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    if (hasCommented)
                        teacher.getComments().remove(commentIndex);
                    System.out.print("Your comment: ");
                    teacher.getComments().add(new Message(scanner.nextLine(), this));
                    break;
                case 2:
                    if (hasCommented) {
                        teacher.getComments().remove(commentIndex);
                        System.out.println("Comment removed Successfully");
                    } else
                        System.out.println("You have no comments to delete");
                    break;
                default:
                    System.out.println("Error: Please choose an option from above");
                    break;

            }

        }
    }

    public void menu() {
        boolean flag = true;
        while (flag) {
            score = positiveFeedback.size() - negativeFeedback.size();
            System.out.println("~~| Student Menu |~~\n" +
                    "\nUsername: " + getUsername() +
                    "\nHouse: " + getHouse() +
                    "\nScore: " + score +
                    "\n1. Change Account Credentials" +
                    "\n2. Take Course" +
                    "\n3. View Teacher's List" +
                    "\n4. Score Teachers" +
                    "\n5. View Courses Taken" +
                    "\n6. View Assignments" +
                    "\n7. Comment Teachers" +
                    "\n8. View Comments" +
                    "\n9. View Profile" +
                    "\n0. Exit");
            Scanner scanner = new Scanner(System.in);
            int command;
            System.out.print("Your command: ");
            command = scanner.nextInt();
            if (command > 2 && command < 9 && getCoursesTaken().isEmpty()) {
                System.out.println("You haven't taken any courses, please take a course first");
                continue;
            }
            switch (command) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    changeAccountCredentials();
                    break;
                case 2:
                    takeCourses();
                    break;
                case 3:
                    viewTeachersList();
                    break;
                case 4:
                    scoreTeachers();
                    break;
                case 5:
                    viewCoursesTaken();
                    break;
                case 6:
                    viewAssignments();
                    break;
                case 7:
                    commentTeachers();
                    break;
                case 8:
                    viewComments();
                    break;
                case 9:
                    viewProfile();
                    break;
                default:
                    System.out.println("Error: Option is not available, please choose from the list above");
            }
            System.out.println("--------------------------------");
        }
    }
}

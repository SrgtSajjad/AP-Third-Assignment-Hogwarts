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

    public void commentTeachers() {
        System.out.println("~~| Comment Teachers |~~\n");
        int i = 0;
        for (Teacher teacher : Educational.getTeachers()) {
            i++;
            System.out.print(i + ". " + teacher.getUsername());
        }
        System.out.print("Choose a teacher's number: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            number = scanner.nextInt();
            if (number - 1 >= 0 && number - 1 < Educational.getTeachers().size())
                break;
            System.out.print("Error: Teacher not available, please choose from the list above ");

        }

        Teacher teacher = Educational.getTeachers().get(number - 1);
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

    public void scoreTeachers() {
        System.out.println("~~| Score Teachers |~~\n");
        int i = 0;
        for (Teacher teacher : Educational.getTeachers()) {
            i++;
            System.out.print(i + ". " + teacher.getUsername());
        }
        System.out.print("Choose a teacher's number: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            number = scanner.nextInt();
            if (number - 1 >= 0 && number - 1 < Educational.getTeachers().size())
                break;
            System.out.print("Error: Teacher not available, please choose from the list above ");

        }

        Teacher teacher = Educational.getTeachers().get(number - 1);
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

    public void scoreStudents() {
        System.out.println("~~| Score Students |~~\n");
        Scanner scanner = new Scanner(System.in);
        int number;
        int i = 0;
        for (Student student : Educational.getStudents()) { // display students participating in the specific class
            i++;
            System.out.println(i + "." + student.getUsername());
        }


        System.out.println("Choose a student from the list above: ");
        while (true) { // get number of student in the participating student's list and handle errors
            number = scanner.nextInt();
            if (number - 1 <= Educational.getStudents().size()) {
                break;
            } else {
                System.out.println("Error: Student unavailable, please select a number from above");
            }
        }

        Student student = Educational.getStudents().get(number - 1);
        boolean flag = true;
        while (flag) {
            System.out.println(student.getUsername() + ":\n0. Exit\n1. Give positive feedback\n2. Give negative feedback\n3. retract feedback");
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    if (!(student.positiveFeedback.contains(this) || student.negativeFeedback.contains(this))) {
                        student.positiveFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this student, retract first to change it");
                    break;
                case 2:
                    if (!(student.positiveFeedback.contains(this) || student.negativeFeedback.contains(this))) {
                        student.negativeFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this student, retract first to change it");
                    break;
                case 3:
                    student.positiveFeedback.remove(this);
                    student.negativeFeedback.remove(this);
                    break;
                default:
                    System.out.println("Error: Please choose an option from above");
                    break;

            }
        }


    }
    public void requestCourse() {
        System.out.println("~~| Request a Course |~~\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a title for your course: ");
        String title = scanner.nextLine();
        Message request = new Message(title, this);
        Assistant.getCourseRequests().add(request);
        System.out.println("Request sent successfully");
    }

    public void menu() {
        boolean flag = true;
        while (flag) {
            System.out.println("~~| Hogwarts Central Management Menu |~~\n" +
                    "\nUsername: " + getUsername() +
                    "\n1. Comment Teachers" +
                    "\n2. Score Teachers" +
                    "\n3. Score Students" +
                    "\n4. Request Course" +
                    "\n5. Remove Teacher" +
                    "\n6. Remove Student" +
                    "\n7. View Courses and Students Participating" +
                    "\n8. Check Teacher Profiles" +
                    "\n9. Check Student Profiles" +
                    "\n10. Enter Student's Menu" +
                    "\n11. Enter Teacher's Menu" +
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
                    commentTeachers();
                    break;
                case 2:
                    scoreTeachers();
                    break;
                case 3:
                    scoreStudents();
                    break;
                case 4:
                    requestCourse();
                    break;
                case 5:
                    removeTeacher();
                    break;
                case 6:
                    removeStudent();
                    break;
                case 7:
                    viewCoursesAndStudentsList();
                    break;
                case 8:
                    checkTeacherProfiles();
                    break;
                case 9:
                    checkStudentProfiles();
                    break;
                case 10:
                    hogwartsTeacher.menu();
                    break;
                case 11:
                    hogwartsStudent.menu();
                    break;
                default:
                    System.out.println("Error: Option is not available, please choose from the list above");
            }
            System.out.println("--------------------------------");
        }
    }
}

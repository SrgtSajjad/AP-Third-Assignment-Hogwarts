import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Teacher extends Educational {
    ArrayList<Student> positiveFeedback = new ArrayList<>();
    ArrayList<Student> negativeFeedback = new ArrayList<>();
    int score;

    public Teacher(String username, String password, String role) {
        super(username, password, role);
    }

    public static Teacher login() {
        System.out.println("~~| Teacher Login |~~\n");

        Scanner scanner = new Scanner(System.in);
        Teacher teacher = null;
        System.out.print("Username: ");
        String username = scanner.next();
        for (Teacher teacher1 : Educational.teachers) {
            if (Objects.equals(teacher1.getUsername(), username)) {
                teacher = teacher1;
                break;
            }
        }
        if (teacher != null) {
            while (true) {
                System.out.print("Password: ");
                String password = scanner.next();
                if (teacher.validatePassword(password)) {
                    System.out.println("-Account Verified-\nWelcome " + teacher.getUsername());
                    break;
                } else if (Objects.equals(password, "exit"))
                    break;
                System.out.println("Error: Authentication failed,Please re-enter your password\n\nType \"exit\" to leave to menu");

            }
            return teacher;
        } else {
            System.out.println("Error: User not available, please try again");
        }
        System.out.println("Error: Login failed");
        return null;
    }

    public void teach() {
        System.out.println("~~| Teach |~~\n");
        int i = 0;
        System.out.println("0. Exit");
        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + ". " + course.getTitle());
        }
        Scanner scanner = new Scanner(System.in);
        Course course = null;

        while (true) {
            System.out.print("Enter the course you would like to teach: ");
            i = scanner.nextInt();
            if (i == 0) {
                break;
            } else if (i - 1 < Course.courses.size() && i - 1 >= 0) {
                course = Course.courses.get(i - 1);
                break;
            }
            System.out.print("Error: Course unavailable, please choose from the list above");

        }

        if (course != null) {

        Scanner microphone = new Scanner(System.in);
        while (true) {
            String lecture = microphone.nextLine();
            if (Objects.equals(lecture, "exit")) {
                break;
            }
            System.out.println("Course: " + course.getTitle());
            System.out.println("Teacher: " + getUsername());
            System.out.println("Lecture: " + lecture);
        }
        System.out.println("Lecture has ended.");
        }
    }

    public void takeCourses() {
        System.out.println("~~| Take Courses |~~\n");
        int i = 0;
        System.out.println("0. Exit");
        for (Course course : Course.courses) {
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
            if (i == 0) {
                break;
            } else if (Course.courses.get(i - 1).getTeacher() != null) {
                System.out.print("This course already has a teacher, please choose another one");
            } else if (i - 1 < Course.courses.size() && i - 1 >= 0) {
                Course course = Course.courses.get(i - 1);
                course.setTeacher(this);
                getCoursesTaken().add(course);
                break;
            }

            System.out.print("Error: Course unavailable, please choose from the list above");
        }
    }

    public void scoreStudents() {
        System.out.println("~~| Score Students |~~\n");
        Scanner scanner = new Scanner(System.in);
        int number;
        int i = 0;
        System.out.println("Choose a course: ");

        for (Course course : getCoursesTaken()) { // display courses taken by teacher
            i++;
            System.out.println(i + "." + course.getTitle());
        }

        while (true) { // get number of a course and handle errors
            number = scanner.nextInt();
            if (number - 1 <= getCoursesTaken().size()) {
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }
        Course course = getCoursesTaken().get(number - 1); // create a reference to the selected course
        System.out.println("Students participating in " + getCoursesTaken().get(number - 1).getTitle() + ":");
        i = 0;
        for (Student student : getCoursesTaken().get(number - 1).participatingStudents) { // display students participating in the specific class
            i++;
            System.out.println(i + "." + student.getUsername());
        }


        System.out.println("Choose a student from the list above: ");
        while (true) { // get number of student in the participating student's list and handle errors
            number = scanner.nextInt();
            if (number - 1 <= course.participatingStudents.size()) {
                break;
            } else {
                System.out.println("Error: Student unavailable, please select a number from above");
            }
        }

        Student student = course.participatingStudents.get(number - 1);
        boolean flag = true;
        while (flag) {
            System.out.println(student.getUsername() + ":\n0. Exit\n1. Give positive feedback\n2. Give negative feedback\n3. retract feedback");
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    flag = false;
                    break;
                case 1: // teacher goes to the student's positive feedback's list
                    if (!(student.positiveFeedback.contains(this) || student.negativeFeedback.contains(this))) {
                        student.positiveFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this student, retract first to change it");
                    break;
                case 2: // teacher goes to the student's negative feedback's list
                    if (!(student.positiveFeedback.contains(this) || student.negativeFeedback.contains(this))) {
                        student.negativeFeedback.add(this);
                        System.out.println("Thanks for your feedback");
                    } else
                        System.out.println("You have already gave a feedback for this student, retract first to change it");
                    break;
                case 3: // teacher retracts their feedback and get removed from any feedback list
                    student.positiveFeedback.remove(this);
                    student.negativeFeedback.remove(this);
                    break;
                default:
                    System.out.println("Error: Please choose an option from above");
                    break;

            }
        }


    }

    public void viewStudentsList() {
        System.out.println("~~| View Students List |~~\n");
        int i = 0;
        System.out.println("Choose a course: ");

        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.getTitle());
        }

        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            number = scanner.nextInt();
            if (number - 1 <= getCoursesTaken().size()) {
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }

        System.out.println("Students participating in " + getCoursesTaken().get(number - 1).getTitle());
        i = 0;
        for (Student student : getCoursesTaken().get(number - 1).participatingStudents) {
            i++;
            System.out.println(i + "." + student.getUsername());
        }


    }

    public void commentStudent() {
        System.out.println("~~| Comment Students |~~\n");
        Scanner scanner = new Scanner(System.in);
        int number;
        int i = 0;
        System.out.println("Choose a course: ");

        for (Course course : getCoursesTaken()) { // display courses taken by teacher
            i++;
            System.out.println(i + "." + course.getTitle());
        }

        while (true) { // get number of a course and handle errors
            number = scanner.nextInt();
            if (number - 1 <= getCoursesTaken().size()) {
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }
        Course course = getCoursesTaken().get(number - 1); // create a reference to the selected course
        System.out.println("Students participating in " + getCoursesTaken().get(number - 1).getTitle() + ":");
        i = 0;
        for (Student student : getCoursesTaken().get(number - 1).participatingStudents) { // display students participating in the specific class
            i++;
            System.out.println(i + "." + student.getUsername());
        }


        System.out.println("Choose a student from the list above: ");
        while (true) { // get number of student in the participating student's list and handle errors
            number = scanner.nextInt();
            if (number - 1 <= course.participatingStudents.size()) {
                break;
            } else {
                System.out.println("Error: Student unavailable, please select a number from above");
            }
        }

        Student student = course.participatingStudents.get(number - 1);
        boolean hasCommented = false;
        int commentIndex = 0;
        for (Comment comment : student.getComments()) {
            if (comment.commentor == this) {
                hasCommented = true;
                commentIndex = student.getComments().indexOf(comment);
                break;
            }
        }
        boolean flag = true;
        while (flag) {
            System.out.println(student.getUsername() + ":\n0. Exit\n1. Set/Edit comment\n2. Delete comment");
            if (hasCommented)
                System.out.println("Your current comment: \"" + student.getComments().get(commentIndex).comment + "\"");
            number = scanner.nextInt();
            switch (number) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    if (hasCommented)
                        student.getComments().remove(commentIndex);
                    System.out.print("Your comment: ");
                    student.getComments().add(new Comment(scanner.nextLine(), this));
                    break;
                case 2:
                    if (hasCommented) {
                        student.getComments().remove(commentIndex);
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

    public void setAssignments() {
        System.out.println("~~| Set Assignments |~~\n");
        viewCoursesTaken();
        Scanner scanner = new Scanner(System.in);
        int number;
        System.out.println("Choose a course: ");

        while (true) { // get number of a course and handle errors
            number = scanner.nextInt();
            if (number - 1 <= getCoursesTaken().size()) {
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }
        Course course = getCoursesTaken().get(number - 1);
        System.out.print("Set an assignment for this course: ");
        String assignment = scanner.nextLine();
        course.setAssignment(assignment);
        System.out.print("Assignment set successfully");

    }

    public void menu() {
        boolean flag = true;
        while (flag) {
            score = positiveFeedback.size() - negativeFeedback.size();
            System.out.println("~~| Teacher Menu |~~\n" +
                    "\nUsername: " + getUsername() +
                    "\nHouse: " + getHouse() +
                    "\nScore: " + score +
                    "\n1. Change Account Credentials" +
                    "\n2. Take Course" +
                    "\n3. View Student's List" +
                    "\n4. Score Students" +
                    "\n5. View Courses Taken" +
                    "\n6. Teach a Course" +
                    "\n7. Set Assignment" +
                    "\n8. Comment a Student" +
                    "\n9. View Your Comments" +
                    "\n0. Exit");
            Scanner scanner = new Scanner(System.in);
            int command;
            System.out.print("Your command: ");
            command = scanner.nextInt();
            if (command > 2 && command < 10 && getCoursesTaken().isEmpty()) {
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
                    viewStudentsList();
                    break;
                case 4:
                    scoreStudents();
                    break;
                case 5:
                    viewCoursesTaken();
                    break;
                case 6:
                    teach();
                    break;
                case 7:
                    setAssignments();
                    break;
                case 8:
                    commentStudent();
                    break;
                case 9:
                    viewComments();
                    break;
                default:
                    System.out.println("Error: Option is not available, please choose from the list above");
            }
        }
    }


}


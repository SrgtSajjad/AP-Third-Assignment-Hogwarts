import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Educational {

    public Teacher(String username, String password, String role) {
        super(username, password, role);
    }

    int score;


    public void teach(Course course) {
        Scanner microphone = new Scanner(System.in);
        while (true) {
            String lecture = microphone.nextLine();
            if (lecture == "exit") {
                break;
            }
            System.out.println("Teacher: " + getUsername());
            System.out.println("Lecture: " + lecture);
        }
        System.out.println("Lecture has ended.");
    }


    public void scoreStudents(Course course) {
        System.out.println("Choose a student from the list above: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            number = scanner.nextInt();
            if (number - 1 <= course.participatingStudents.size()) {
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }
        int newScore;
        int index = course.participatingStudents.get(number - 1).coursesTaken.indexOf(course);
        System.out.println("Changing score for: " + course.participatingStudents.get(number - 1).getUsername() + "\nScore: " + course.participatingStudents.get(number - 1).scores.get(index) + "/100");
        while (true) {
            newScore = scanner.nextInt();
            if (newScore <= 100) {
                course.participatingStudents.get(number - 1).scores.set(index, newScore);
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }

    }

    public void viewStudentsList() {
        int i = 0;
        System.out.println("Choose a course: ");

        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.title);
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

        System.out.println("Students participating in " + getCoursesTaken().get(number - 1).title);
        i = 0;
        for (Student student : getCoursesTaken().get(number - 1).participatingStudents) {
            i++;
            System.out.println(i + "." + student.getUsername());
        }

        System.out.println("Type \"score\" to enter scoring menu");
        if (scanner.next() == "score") {
            scoreStudents(getCoursesTaken().get(number - 1));
        }

    }

}


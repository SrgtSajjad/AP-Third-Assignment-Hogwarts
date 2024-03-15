import java.util.ArrayList;
import java.util.Scanner;

public class Educational extends Account {

    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> coursesTaken = new ArrayList<Course>();

    private String house;
    private boolean sortingQuizCapability;

    public Educational(String username, String password, String role) {
        super(username, password, role);
        sortingQuizCapability = true;
    }

    public void signUp() {
        if (!isSignedUp()) {
            System.out.println("~~| Sign Up |~~\n");
            takeSortingQuiz();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Your full name: ");
            setFullName(scanner.nextLine());
            System.out.print("Your age: ");
            setAge(scanner.nextInt());
            System.out.print("Your speciality: ");
            setSpeciality(scanner.next());
            setSignedUp(true);
            System.out.println("-Sign Up Completed-");
        }
        else {
            System.out.println("You have already completed Sign Up");
        }
    }


    public void viewCoursesTaken() {
        System.out.println("~~| View Courses Taken |~~\n");
        int i = 0;
        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.getTitle());
        }
    }

    public void takeSortingQuiz() {
        System.out.println("~~| Sorting Quiz |~~\n");
        if (sortingQuizCapability) {
            System.out.println("Which house would you fit in best?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Gryffindor\n2. Ravenclaw\n3. Slytherin\n4. Hufflepuff");
            switch (scanner.nextInt()) {
                case 1:
                    house = "Gryffindor";
                    break;
                case 2:
                    house = "Ravenclaw";
                    break;
                case 3:
                    house = "Slytherin";
                    break;
                case 4:
                    house = "Hufflepuff";
                    break;
            }
            sortingQuizCapability = false;
        }
        else {
            System.out.println("You have already been sorted!\nYour house: " + house);
        }
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }


    public static ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCoursesTaken() {
        return coursesTaken;
    }

    public String getHouse() {
        return house;
    }

}

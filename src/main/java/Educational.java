import java.util.ArrayList;
import java.util.Scanner;

public class Educational extends Account {

    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Message> comments = new ArrayList<>();

    private ArrayList<Course> coursesTaken = new ArrayList<>();

    private String house;
    private boolean sortingQuizCapability;

    public Educational(String username, String password, String role) {
        super(username, password, role);
        sortingQuizCapability = true;
    }

    public void signUp() {
        if (isNotSignedUp()) {
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
        } else {
            System.out.println("You have already completed Sign Up");
        }
    }

    public void viewProfile() {
        System.out.println("~~| Profile |~~\n");
        System.out.println("Name: " + this.getFullName());
        System.out.println("Username: " + this.getUsername());
        System.out.println("Age: " + this.getAge());
        System.out.println("Speciality: " + this.getSpeciality());
        System.out.println("House: " + this.getHouse());
    }

    public void changeAccountCredentials() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose which you'd like to change:\n1. Username\n2. Password\n0. Exit");
        boolean flag = true;
        while (flag) {
            switch (scanner.nextInt()) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    System.out.print("New Username: ");
                    changeUsername(scanner.next());
                    flag = false;
                    break;
                case 2:
                    System.out.print("New Password: ");
                    changePassword(scanner.next());
                    flag = false;
                    break;
                default:
                    System.out.println("Error: House unavailable, please choose correctly from the options above");
                    break;
            }
        }

    }

    void viewComments() {
        System.out.println("~~| View Comments |~~\n");
        int i = 0;
        for (Message comment : comments) {
            i++;
            System.out.println(i + "." + comment.text);
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

    private void takeSortingQuiz() {
        System.out.println("~~| Sorting Quiz |~~\n");
        if (sortingQuizCapability) {
            System.out.println("Which house would you fit in best?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Gryffindor\n2. Ravenclaw\n3. Slytherin\n4. Hufflepuff");
            boolean flag = true;
            while (flag) {
                switch (scanner.nextInt()) {
                    case 1:
                        house = "Gryffindor";
                        flag = false;
                        break;
                    case 2:
                        house = "Ravenclaw";
                        flag = false;
                        break;
                    case 3:
                        house = "Slytherin";
                        flag = false;
                        break;
                    case 4:
                        house = "Hufflepuff";
                        flag = false;
                        break;
                    default:
                        System.out.println("Error: House unavailable, please choose correctly from the options above");
                        break;
                }
            }
            sortingQuizCapability = false;
        } else {
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

    public ArrayList<Message> getComments() {
        return comments;
    }

    public String getHouse() {
        return house;
    }

}

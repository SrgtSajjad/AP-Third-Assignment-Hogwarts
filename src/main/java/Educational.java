import java.util.ArrayList;
import java.util.Scanner;

public class Educational extends Account {

    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> coursesTaken = new ArrayList<Course>();

    private String house;

    public Educational(String username, String password, String role) {
        super(username, password, role);
    }

    public void signUp() {

    }



    public void viewCoursesTaken() {
        int i = 0;
        for (Course course : getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.title);
        }
    }

    public void takeSortingQuiz() {
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

    public void setHouse(String house) {
        this.house = house;
    }
}

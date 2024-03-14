import java.util.ArrayList;
import java.util.Scanner;

public class Educational extends Account {

    private ArrayList<String> courses = new ArrayList<String>();
    private ArrayList<String> students = new ArrayList<String>();
    private String house;

    public Educational(String username, String password, String role) {
        super(username, password, role);
    }

    public void signUp() {

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

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}

import java.util.ArrayList;

public class Student extends Educational {

    ArrayList<Course> coursesTaken = new ArrayList<Course>();
    ArrayList<Integer> scores = new ArrayList<Integer>();
    public Student(String username, String password, String role) {
        super(username, password, role);
    }



}

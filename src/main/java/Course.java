import java.util.ArrayList;
import java.util.UUID;

public class Course {
    String title;
    Teacher teacher;
    UUID courseID;
    private String assignment;
    ArrayList<Student> participatingStudents = new ArrayList<Student>();

    public Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
        assignment = "No assignments";
        courseID = UUID.randomUUID();
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
}

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    String title;
    Teacher teacher;
    UUID courseID;
    ArrayList<Student> participatingStudents = new ArrayList<Student>();

    public Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
        courseID = UUID.randomUUID();
    }
}

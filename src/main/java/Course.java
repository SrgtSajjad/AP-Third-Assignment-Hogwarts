import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private String title;
    private Teacher teacher;
    UUID courseID;
    private String assignment;
    static ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Student> participatingStudents = new ArrayList<>();


    public Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
        assignment = "No assignments";
        courseID = UUID.randomUUID();
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

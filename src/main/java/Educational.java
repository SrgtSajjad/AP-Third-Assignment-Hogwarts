import java.util.ArrayList;

public abstract class Educational extends Account {

    private ArrayList<String> courses = new ArrayList<String>();
    private ArrayList<String> students = new ArrayList<String>();

    public Educational(String username, String password, String role) {
        super(username, password, role);
    }

    public abstract void signUp();
    public abstract void takeCourses();
    public abstract void takeSortingQuiz();

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
}

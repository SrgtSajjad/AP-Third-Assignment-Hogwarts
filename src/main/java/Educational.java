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

}

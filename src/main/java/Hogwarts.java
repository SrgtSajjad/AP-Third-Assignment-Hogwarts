public class Hogwarts extends Account {
    public Hogwarts(String username, String password, String role) {
        super(username, password, role);
    }

    // TODO: Define Attributes


    // TODO: Define Functionalities
    public void viewAllTeachers() {
        int i = 0;
        for (Teacher teacher : Educational.teachers) {
            i++;
            System.out.println(i + ". " + teacher.getFullName());
        }
    }

    public void viewAllStudents() {
        int i = 0;
        for (Student student : Educational.students) {
            i++;
            System.out.println(i + ". " + student.getFullName());
        }
    }

    public void viewAllCourses() {
        int i = 0;
        for (Course course : Course.courses) {
            i++;
            System.out.println(i + ". " + course.getTitle());
        }
    }
}

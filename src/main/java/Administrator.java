import java.util.Scanner;

public class Administrator extends Account {
    public Administrator(String username, String password, String role) {
        super(username, password, role);
    }

    public void removeTeacher() {
        System.out.println("~~| Remove Teacher |~~\n");
        int i = 0;
        Teacher selectedTeacher = null;
        System.out.println("0. Exit");
        for (Teacher teacher : Educational.getTeachers()) {
            i++;
            System.out.println(i + ". " + teacher.getFullName());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            i = scanner.nextInt();
            if (i == 0) {
                break;
            } else if (i - 1 > 0 && i - 1 < Educational.getTeachers().size()) {
                selectedTeacher = Educational.getTeachers().get(i - 1);
                break;

            } else
                System.out.println("Error: Teacher unavailable, please choose from the list above");
        }

        if (selectedTeacher != null) {
            System.out.println("Confirm remove of the teacher: " + selectedTeacher.getUsername() + "?\n1. Yes\n2.No");
            boolean flag = true;
            int confirmation = scanner.nextInt();
            while (flag) {
                switch (confirmation) {
                    case 1:
                        Educational.getTeachers().remove(selectedTeacher);
                        for (Course course : selectedTeacher.getCoursesTaken()) {
                            course.setTeacher(null);
                        }
                        flag = false;
                        System.out.println("Teacher removed successfully");
                        break;
                    case 2:
                        flag = false;
                        break;
                    default:
                        System.out.println("Error: Please choose one of the options above");
                        break;
                }
            }
        }

    }

    public void removeStudent() {
        System.out.println("~~| Remove Student |~~\n");
        int i = 0;
        Student selectedStudent = null;
        System.out.println("0. Exit");
        for (Student student : Educational.getStudents()) {
            i++;
            System.out.println(i + ". " + student.getFullName());
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            i = scanner.nextInt();
            if (i == 0) {
                break;
            } else if (i - 1 > 0 && i - 1 < Educational.getStudents().size()) {
                selectedStudent = Educational.getStudents().get(i - 1);
                break;

            } else
                System.out.println("Error: Student unavailable, please choose from the list above");
        }

        if (selectedStudent != null) {
            System.out.println("Confirm remove of the teacher: " + selectedStudent.getUsername() + "?\n1. Yes\n2.No");
            boolean flag = true;
            int confirmation = scanner.nextInt();
            while (flag) {
                switch (confirmation) {
                    case 1:
                        Educational.getStudents().remove(selectedStudent);
                        for (Course course : selectedStudent.getCoursesTaken()) {
                            course.participatingStudents.remove(selectedStudent);
                        }
                        flag = false;
                        System.out.println("Student removed successfully");
                        break;
                    case 2:
                        flag = false;
                        break;
                    default:
                        System.out.println("Error: Please choose one of the options above");
                        break;
                }
            }
        }

    }

    public void viewCoursesAndStudentsList() {
        System.out.println("~~| Courses and Students Participating |~~\n");
        Scanner scanner = new Scanner(System.in);
        int number;
        int i = 0;
        System.out.println("Choose a course: ");
        System.out.println("0.Exit");
        for (Course course : Course.getCourses()) {
            i++;
            System.out.println(i + "." + course.getTitle());
        }

        while (true) { // get number of a course and handle errors
            number = scanner.nextInt();
            if (number - 1 <= Course.getCourses().size()) {
                break;
            } else {
                System.out.println("Error: Course unavailable, please select a number from above");
            }
        }
        if (number == 0)
            return;
        Course course = Course.getCourses().get(number - 1); // create a reference to the selected course
        System.out.println("Students participating in " + Course.getCourses().get(number - 1).getTitle() + ":");
        i = 0;
        for (Student student : Course.getCourses().get(number - 1).participatingStudents) { // display students participating in the selected course
            i++;
            System.out.println(i + "." + student.getUsername());
        }



    }
    public void checkStudentProfiles() {
        System.out.println("~~| Check Student Profiles |~~\n");
        int number;
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        System.out.println("0.Exit");
        for (Student student: Educational.getStudents()) {
            i++;
            System.out.println(i + "." + student.getUsername());
        }
        System.out.println("Choose a student from the list above: ");
        while (true) { // get number of student in the participating student's list and handle errors
            number = scanner.nextInt();
            if (number - 1 <= Educational.getStudents().size()) {
                break;
            } else {
                System.out.println("Error: Student unavailable, please select a number from above");
            }
        }
        if (number == 0)
            return;

        Student student = Educational.getStudents().get(number - 1);

        System.out.println("Student's Profile: ");
        System.out.println("Name: " + student.getFullName());
        System.out.println("Username: " + student.getUsername());
        System.out.println("Score: " + student.getScore());
        System.out.println("Age: " + student.getAge());
        System.out.println("Speciality: " + student.getSpeciality());
        System.out.println("House: " + student.getHouse());
        System.out.println("Courses Taken: ");
        i = 0;
        for (Course course:student.getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.getTitle() + " by " + course.getTeacher().getUsername());
        }
    }
    public void checkTeacherProfiles() {
        System.out.println("~~| Check Teacher Profiles |~~\n");
        int number;
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        System.out.println("0.Exit");
        for (Teacher teacher: Educational.getTeachers()) {
            i++;
            System.out.println(i + "." + teacher.getUsername());
        }
        System.out.println("Choose a teacher from the list above: ");
        while (true) { // get number of student in the participating student's list and handle errors
            number = scanner.nextInt();
            if (number - 1 <= Educational.getTeachers().size()) {
                break;
            } else {
                System.out.println("Error: Teacher unavailable, please select a number from above");
            }
        }
        if (number == 0)
            return;

        Teacher teacher = Educational.getTeachers().get(number - 1);

        System.out.println("Teacher's Profile: ");
        System.out.println("Name: " + teacher.getFullName());
        System.out.println("Username: " + teacher.getUsername());
        System.out.println("Score: " + teacher.getScore());
        System.out.println("Age: " + teacher.getAge());
        System.out.println("Speciality: " + teacher.getSpeciality());
        System.out.println("House: " + teacher.getHouse());
        System.out.println("Courses Taken: ");
        i = 0;
        for (Course course:teacher.getCoursesTaken()) {
            i++;
            System.out.println(i + "." + course.getTitle());
        }
    }

}

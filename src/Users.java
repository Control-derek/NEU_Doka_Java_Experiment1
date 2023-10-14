import java.util.Vector;

public class Users {
    static User admin = new User("NEU_Doka", "123456");

    static Vector<Student> studentList = new Vector<Student>();

    static Vector<Teacher> teacherList = new Vector<Teacher>();

    public static void showStudents() {
        System.out.println("学号 名字 班级");
        for (Student s: studentList) {
            s.show();
        }
    }
    public static void showTeachers() {
        System.out.println("工号 名字 职称");
        for (Teacher t: teacherList) {
            t.show();
        }
    }
}

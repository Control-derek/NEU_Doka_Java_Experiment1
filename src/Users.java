import java.util.Scanner;
import java.util.Vector;

public class Users {
    static User admin = new User("NEU_Doka", "123456");

    static Vector<Student> studentList = new Vector<Student>();

    static Vector<Teacher> teacherList = new Vector<Teacher>();

    public static void addStudents() {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        while (true) {  // 调用添加一门课程的方法，用户可选择继续添加课程
            System.out.println("请输入第" + (i++) + "个学生信息");
            System.out.println("请输入学号：");
            int id = sc.nextInt();
            System.out.println("请输入密码：");
            String pass = sc.next();
            System.out.println("请输入姓名：");
            String name = sc.next();
            System.out.println("请输入班级：");
            String Class = sc.next();
            studentList.add(new Student(name, pass, id, Class));
            System.out.print("是否继续添加学生? y/n");
            if (!sc.next().equals("y"))
                break;
        }
    }

    public static void addTeachers() {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        while (true) {  // 调用添加一门课程的方法，用户可选择继续添加课程
            System.out.println("请输入第" + (i++) + "个老师信息");
            System.out.println("请输入工号：");
            int id = sc.nextInt();
            System.out.println("请输入密码：");
            String pass = sc.next();
            System.out.println("请输入姓名：");
            String name = sc.next();
            System.out.println("请输入职称：");
            String level = sc.next();
            teacherList.add(new Teacher(name, pass, id, level));
            System.out.print("是否继续添加老师? y/n");
            if (!sc.next().equals("y"))
                break;
        }
    }

    public static void deleteStudents() {
        System.out.println("请输入需要删除的学生学号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (int i=0; i<studentList.size(); ++i) {
            if (studentList.get(i).id == id) {
                studentList.remove(i);  // 删除学生信息
                break;
            }
        }
    }

    public static void deleteTeachers() {
        System.out.println("请输入需要删除的老师工号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (int i=0; i<teacherList.size(); ++i) {
            if (teacherList.get(i).workId == id) {
                teacherList.remove(i);  // 删除老师信息
                break;
            }
        }
    }

    public static void recoverStudentsPass() {
        System.out.println("请输入需要初始化密码的学生学号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (int i=0; i<studentList.size(); ++i) {
            if (studentList.get(i).id == id) {
                studentList.get(i).setPass("123456");  // 初始化密码
                break;
            }
        }
    }

    public static void recoverTeachersPass() {
        System.out.println("请输入需要初始化密码的老师工号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (int i=0; i<teacherList.size(); ++i) {
            if (teacherList.get(i).workId == id) {
                teacherList.get(i).setPass("123456");  // 初始化密码
                break;
            }
        }
    }

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

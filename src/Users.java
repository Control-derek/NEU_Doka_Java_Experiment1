import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Users {
    static User admin = new User("NEU_Doka", "123456");

    static Vector<Student> studentList = new Vector<Student>();

    static Vector<Teacher> teacherList = new Vector<Teacher>();

    public static void init() {  // 初始化学生、老师列表
        String queryst = "Select sid, sclass, sname, pass \n" +
                "From student";
        String queryt = "Select tid, tlevel, tname, pass \n" +
                "From teacher";
        // 从数据库查出所有学生信息
        List<HashMap<String, Object>> selectst = database.dbstmt.Select(queryst);
        for (HashMap<String, Object> c: selectst) {
            studentList.add(new Student((String)c.get("sname"), (String)c.get("pass"),
                    (int)c.get("sid"), (String)c.get("sclass")));
        }
        // 从数据库查出所有老师信息
        List<HashMap<String, Object>> selectt = database.dbstmt.Select(queryt);
        for (HashMap<String, Object> c: selectt) {
            teacherList.add(new Teacher((String)c.get("tname"), (String)c.get("pass"),
                    (int)c.get("tid"), (String)c.get("tlevel")));
        }

    }

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
            // 同步数据库
            String insSql = database.stInsSql(id, Class, name, pass);
            database.dbstmt.Insert(insSql);
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
            // 同步数据库
            String insSql = database.tInsSql(id, level, name, pass);
            database.dbstmt.Insert(insSql);
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
                // 同步数据库
                String delSql = database.stDelSql(id);
                database.dbstmt.Delete(delSql);
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
                // 同步数据库
                String delSql = database.tDelSql(id);
                database.dbstmt.Delete(delSql);
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
                // 同步数据库
                String updSql = database.stpassSql(id, "123456");
                database.dbstmt.Update(updSql);
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
                // 同步数据库
                String updSql = database.tpassSql(id, "123456");
                database.dbstmt.Update(updSql);
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

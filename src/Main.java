import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        Vector<Course> clist = new Vector<Course>();
//        clist.add(new Course(1, "数学分析", 0,
//                new Teacher("韩老师", 1, "教授"), 20));
//        clist.add(new Course(2, "高等代数", 0,
//                new Teacher("李老师", 2, "教授"), 15));
//        clist.add(new Course(3, "Java", 1,
//                new Teacher("吴老师", 3, "副教授"), 23));
//        clist.add(new Course(4, "深度学习", 0,
//                new Teacher("李老师", 4, "教授"), 50));
//        User user = new User();
//        Courses.addCourses();
//        Courses.SortCourseList();
//        Courses.showCourses();
//        Courses.readCourses();
        menu();
//        Courses.saveCourse();
//        StringBuffer s1 = new StringBuffer("124");
//        s1.append(266);
//        s1.append("223");
//        System.out.println(s1);

    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        Vector loginInfo = login();
        int identity = (int)loginInfo.get(0);
        if (identity == -1) {
            return;
        }
        database.init();  // 初始化
        if (identity == 0) {  // 管理员菜单
            MainLoop:
            while (true) {
                System.out.println("请选择操作:\n1.新建课程\n" +
                        "2.删除课程\n3.设置课程教师\n" +
                        "4.查找老师讲课课程 \n5.显示课程列表 \n" +
                        "6.按照选课人数进行排序\n7.显示学生列表\n" +
                        "8.显示教师列表\n9.添加学生\n" +
                        "10.添加老师\n11.删除学生\n" +
                        "12.删除老师\n13.初始化学生密码\n" +
                        "14.初始化老师密码\n0.退出");
                int choice = sc.nextInt();
                switch (choice) {
                    case 0:  //退出系统
                        database.dbstmt.close();  // 关数据库
                        break MainLoop;
                    case 1:  // 添加课程
                        Courses.addCourses();
                        break;
                    case 2:  // 删除课程
                        Courses.deleteCourse();
                        break;
                    case 3:  // 设置课程教师
                        Courses.setCourseTeacher();
                        break;
                    case 4:  // 根据授课教师查找课程
                        Courses.SearchCourseByTeacher();
                        break;
                    case 5:  // 显示课程信息
                        Courses.showCourses();
                        break;
                    case 6:  // 按照选课人数排序
                        Courses.SortCourseList();
                        break;
                    case 7:  // 显示所有学生
                        Users.showStudents();
                        break;
                    case 8:  // 显示所有教师
                        Users.showTeachers();
                        break;
                    case 9:  // 添加学生
                        Users.addStudents();
                        break;
                    case 10:  // 添加老师
                        Users.addTeachers();
                        break;
                    case 11:  // 删除学生
                        Users.deleteStudents();
                        break;
                    case 12:  // 删除老师
                        Users.deleteTeachers();
                        break;
                    case 13:  // 初始化学生密码
                        Users.recoverStudentsPass();
                        break;
                    case 14:  // 初始化老师密码
                        Users.recoverTeachersPass();
                        break;
                    default:  // 非法输入
                        System.out.println("输入错误！！！");
                }
            }
        }
        else if (identity == 1) { // 教师菜单
            Teacher loadingTeacher = (Teacher)loginInfo.get(1);
            MainLoop:
            while (true) {
                System.out.println("请选择操作:\n1.修改登录密码\n" +
                        "2.查看自己所授课程\n3.查看课程学生名单\n" +
                        "0.退出");
                int choice = sc.nextInt();
                switch (choice) {
                    case 0 -> {database.dbstmt.close(); break MainLoop;}  // 退出系统
                    case 1 -> loadingTeacher.settPassWord();  // 修改登录密码
                    case 2 -> loadingTeacher.teachingCourse();  // 查看所授课程
                    case 3 -> loadingTeacher.studentList();  // 查看所授课程学生名单
                }
            }
        }
        else {
            Student loadingStudent = (Student)loginInfo.get(1);
            MainLoop:
            while (true) { // 学生菜单
                System.out.println("请选择操作:\n1.修改登录密码\n" +
                        "2.查看自己所上课程\n3.选课\n" +
                        "0.退出");
                int choice = sc.nextInt();
                switch (choice) {
                    case 0 -> {database.dbstmt.close(); break MainLoop;}  // 退出系统
                    case 1 -> loadingStudent.setstPassWord();  // 修改登录密码
                    case 2 -> loadingStudent.learningCourse();  // 查看自己所上课程
                    case 3 -> loadingStudent.choiceCourse();  // 选课
                }
            }
        }
    }
    public static Vector login() {  // 登录用
        Vector res = new Vector();  // result
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入登陆身份：\n0.管理员\n" +
                "1.教师\n" +
                "2.学生");
        int identity = sc.nextInt();
        switch (identity) {
            case 0 -> System.out.println("请输入管理员密码：");
            case 1 -> System.out.println("请输入教师账号、密码：");
            case 2 -> System.out.println("请输入学生账号、密码：");
        }
        if (identity == 0) {
            String pass = sc.next();
            if (!pass.equals(Users.admin.getPass())) {
                System.out.println("密码错误！系统退出！");
            }
            res.add(identity);
            return res;
        }
        else if (identity == 1) {
            String name = sc.next();
            String pass = sc.next();
            StringBuffer queryt = new StringBuffer("Select * \n" +
                    "From teacher where tname = ");
            queryt.append("'").append(name).append("'");
            queryt.append(" and pass = ");
            queryt.append("'").append(pass).append("'");
            String queryst = new String(queryt);
            List<HashMap<String, Object>> selectt = database.dbstmt.Select(queryst);
            if (selectt.size() == 0) {  // 姓名或密码错误
                System.out.println("用户名或密码错误！系统退出！");
                res.add(-1);
                return res;
            }
            res.add(identity);
            res.add(new Teacher((String)selectt.get(0).get("tname"), (String)selectt.get(0).get("pass"),
                    (int)selectt.get(0).get("tid"), (String)selectt.get(0).get("tlevel")));
            return res;
        }
        else if (identity == 2){
            String name = sc.next();
            String pass = sc.next();
            StringBuffer queryt = new StringBuffer("Select * \n" +
                    "From student where sname = ");
            queryt.append("'").append(name).append("'");
            queryt.append(" and pass = ");
            queryt.append("'").append(pass).append("'");
            String queryst = new String(queryt);
            List<HashMap<String, Object>> selectt = database.dbstmt.Select(queryst);
            if (selectt.size() == 0) {  // 姓名或密码错误
                System.out.println("用户名或密码错误！系统退出！");
                res.add(-1);
                return res;
            }
            res.add(identity);
            res.add(new Student((String)selectt.get(0).get("sname"), (String)selectt.get(0).get("pass"),
                    (int)selectt.get(0).get("sid"), (String)selectt.get(0).get("sclass")));
            return res;
        }

        System.out.println("输入错误！系统退出！");
        res.add(-1);
        return res;
    }
}
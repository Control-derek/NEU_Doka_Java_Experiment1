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
        Courses.readCourses();
        menu();
        Courses.saveCourse();
//        StringBuffer s1 = new StringBuffer("124");
//        s1.append(266);
//        s1.append("223");
//        System.out.println(s1);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员密码：");
        String pass = sc.next();
        if (!pass.equals(Users.admin.getPass())) {
            System.out.println("密码错误！系统退出！");
            return;
        }
        MainLoop:
        while(true) {
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
}
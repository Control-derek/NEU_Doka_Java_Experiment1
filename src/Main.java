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
        Courses.addCourses();
        Courses.SortCourseList();
        Courses.showCourses();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员密码:");
        String pass = sc.next();
    }
}
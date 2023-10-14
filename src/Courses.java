import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class Courses {
    static Vector<Course> clist = new Vector<Course>();



    public static void addCourse() {
        Courses.clist.add(Course.inputCourse(Courses.clist.size()+1));
    }

    public static void addCourses() {  // 添加多门课程信息
        int i = 1;
        Scanner sc = new Scanner(System.in);
        while (true) {  // 调用添加一门课程的方法，用户可选择继续添加课程
            System.out.println("请输入第" + (i++) + "门课程信息");
            Courses.addCourse();
            System.out.print("是否继续添加课程? y/n");
            if (!sc.next().equals("y"))
                break;
        }
    }

    public static void showCourses() {
        System.out.println("编号  课程  类型  教师  选课人数");
        for (Course c: Courses.clist) {
            c.show();
        }
    }

    public static void SortCourseList() {
        Comparator<Course> cmp = new CourseComparator();  // 初始化比较器
        Collections.sort(Courses.clist, cmp);
    }

    public static void SearchCourseByTeacher () {
        System.out.println("输入要查找的教师名");
        Scanner sc = new Scanner(System.in);
        String tname = sc.next();
        for (Course c:clist) {
            if (tname.equals(c.teacher.name)) {
                c.show();
            }
        }
    }
}

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//        Course c = new Course();
//        Scanner sc = new Scanner(System.in);
//        c.id = 1;
//        c.name = "java 面向对象编程";
//        c.type = 0;
//        c.show();
//        Course c1 = new Course();
//        c1.id = sc.nextInt();
//        c1.name = sc.next();
//        c1.type = sc.nextInt();
//        c1.show();
//        Course c2 = new Course(2, "编译原理", 0);
//        Vector clist = new Vector();
//        clist.add(c);
//        clist.add(c1);
//        clist.add(c2);
//        for (int i = 0; i < clist.size(); ++i) {
//            Course ci = (Course)clist.get(i);
//            ci.show();
//        }
//        User admin = new User("admin", "123456");
//        Student stu = new Student("liu", "654321", 20180101, "计1801");
//        Teacher thr = new Teacher("ljl", "000000", 7480, "教授");
//
//        Vector<User> ulist = new Vector<User>();
//        ulist.add(admin);
//        ulist.add(stu);
//        ulist.add(thr);
//
//        for (User u:ulist) {
//            u.show();
//        }
        Scanner sc = new Scanner(System.in);
        Vector<Course> clist = new Vector<Course>();
        clist.add(new Course(1, "数学分析", 0,
                new Teacher("韩老师", 1, "教授"), 20));
        clist.add(new Course(2, "高等代数", 0,
                new Teacher("李老师", 2, "教授"), 15));
        clist.add(new Course(3, "Java", 1,
                new Teacher("吴老师", 3, "副教授"), 23));
        clist.add(new Course(4, "深度学习", 0,
                new Teacher("李老师", 4, "教授"), 50));
//        for (int i=0; i<4; ++i) {
//            System.out.println("输入课程号");
//            int id = sc.nextInt();
//            System.out.println("输入课程名");
//            String name = sc.next();
//            System.out.println("输入课程类型：0必修，1选修");
//            int type = sc.nextInt();
//            System.out.println("输入教师名、工号、职称");
//            String tname = sc.next();
//            int tid = sc.nextInt();
//            String level = sc.next();
//            System.out.println("输入上课人数");
//            int num = sc.nextInt();
//            clist.add(new Course(id, name, type,
//                    new Teacher(tname, tid, level), num));
//        }
        Comparator<Course> cmp = new CourseComparator();
        Collections.sort(clist, cmp);
        for (Course c:clist) {
            c.show();
        }
        System.out.println("输入教师名");
        String tname = sc.next();
        for (Course c:clist) {
            if (tname.equals(c.teacher.name)) {
                c.show();
            }
        }
    }
}
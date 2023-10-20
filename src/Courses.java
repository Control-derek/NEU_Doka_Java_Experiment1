import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class Courses {
    static Vector<Course> clist = new Vector<Course>();

    public Courses() {
        System.out.println("!!!!!!!!!!!!!!!");
    }

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

    public static void deleteCourse() {
        System.out.println("请输入需要删除的课程名：");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        for (int i=0; i<Courses.clist.size(); ++i) {
            if (Courses.clist.get(i).name.equals(name)) {
                Courses.clist.remove(i);  // 删除课程
                break;
            }
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

    public static void setCourseTeacher() {
        System.out.println("请输入需要设置教师的课程名：");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("输入教师名、工号、职称");
        String tname = sc.next();
        int tid = sc.nextInt();
        String level = sc.next();
        for (int i=0; i<Courses.clist.size(); ++i) {
            if (Courses.clist.get(i).name.equals(name)) {
                Courses.clist.get(i).teacher = new Teacher(tname, tid, level);  // 修改课程教师名
                break;
            }
        }
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

    public static void saveCourse() {
        File file = new File("./data/Courses.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (Course c: Courses.clist) {
                out.write(c.toString()+"\r\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCourses() {  // 读文件得课程
        try {
            BufferedReader br = new BufferedReader
                    (new InputStreamReader(new FileInputStream("./data/Courses.txt")));
            String data = null;
            while ((data = br.readLine())!=null) {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String name = ps[1];
                int type = Integer.parseInt(ps[2]);
                String tname = ps[3];
                int tid = Integer.parseInt(ps[4]);
                String level = ps[5];
                int num_people = Integer.parseInt(ps[6]);
                if (type == 0) {
                    int credit = Integer.parseInt(ps[7]);
                    Courses.clist.add(new RequiredCourse(id, name, new Teacher(tname, tid, level), num_people, credit));
                }
                else {
                    int maxstu = Integer.parseInt(ps[7]);
                    Courses.clist.add(new OptionalCourse(id, name, new Teacher(tname, tid, level), num_people, maxstu));
                }
            }
            br.close();  // 关文件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

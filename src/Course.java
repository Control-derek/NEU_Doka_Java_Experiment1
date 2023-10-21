import java.util.*;

public class Course {
    int id;  // 课程编号
    String name;  // 课程名称
    int type;  // 0必修，1选修
    Teacher teacher;  // 上课教师
    int num_people;  // 选课人数

    public Course(int id, String name, int type, Teacher t, int num) {
        this(id, name, type);
        this.teacher = t;
        this.num_people = num;
    }
    
    public Course(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Course() {}

    public static Course inputCourse(int id) {
        Scanner sc = new Scanner(System.in);
//        System.out.println("输入课程号");
//        int id = sc.nextInt();
        System.out.println("输入课程名");
        String name = sc.next();
        System.out.println("输入课程类型：0必修，1选修");
        int type = sc.nextInt();
        System.out.println("输入教师名、工号、职称");
        String tname = sc.next();
        int tid = sc.nextInt();
        String level = sc.next();
        System.out.println("输入上课人数");
        int num = sc.nextInt();
        Course c;
        StringBuffer queryt = new StringBuffer("Select tid, tlevel, tname, pass \n" +
                "From teacher where tid = ");
        queryt.append(tid);
        String queryst = new String(queryt);
        List<HashMap<String, Object>> selectt = database.dbstmt.Select(queryst);
        if (selectt.size() == 0) {  // 课程老师不在老师表里 插入 密码默认为123456
            String inssql = database.tInsSql(tid, level, tname, "123456");
            database.dbstmt.Insert(inssql);
        }
        if (type == 0) {
            System.out.println("输入学分：");
            int credit = sc.nextInt();
            c = new RequiredCourse(id, name, new Teacher(tname, tid, level), num, credit);
            // 同步数据库
            String insSql = database.rsInsSql(id, name, type, tid, num, credit);
            database.dbstmt.Insert(insSql);
        }
        else {  // 选修课
            System.out.println("输入最大选课人数：");
            int maxNum = sc.nextInt();
            c = new OptionalCourse(id, name, new Teacher(tname, tid, level), num, maxNum);
            // 同步数据库
            String insSql = database.osInsSql(id, name, type, tid, num, maxNum);
            database.dbstmt.Insert(insSql);
        }
        return c;
    }

    public void show() {  // 显示课程信息
        System.out.print(id);
        System.out.print(" " + name);
        switch (type) {
            case 0 -> System.out.print(" 必修");
            case 1 -> System.out.print(" 选修");
            default -> System.out.print(" 其他");
        }
        System.out.print(" 上课教师：");
        teacher.show();
        System.out.println(" 选课人数：" + num_people);
    }

    public String toString() {
        return id + " " + name + " " + type + " " + teacher.toString() + " " + num_people;
    }
}

class CourseComparator implements Comparator<Course> {
    public int compare(Course a, Course b) {
        if (a.num_people < b.num_people) return 1;
        else if (a.num_people > b.num_people) return -1;
        return 0;
    }
}
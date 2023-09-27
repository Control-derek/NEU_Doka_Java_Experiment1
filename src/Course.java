import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

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
}

class CourseComparator implements Comparator<Course> {
    public int compare(Course a, Course b) {
        if (a.num_people < b.num_people) return 1;
        else if (a.num_people > b.num_people) return -1;
        return 0;
    }
}
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    int id; // 学号
    String Class; // 班级
    public Student(String name, String pass,
                   int id, String Class) {
        this.name = name;
        this.setPass(pass);
        this.id = id;
        this.Class = Class;
    }
    public void show() {
        System.out.println(id + " "+ name + " " + Class);
    }
    public int login() {  // 登录方法
        System.out.println("请输入学号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.println("请输入密码：");
        String pass = sc.next();
        if (id==this.id&&pass.equals(this.getPass())) {
            System.out.println(Class + " "+ name + " 你好！");
            return 1;  // 登录成功
        }
        return 0;
    }
    public void setstPassWord() {  // 修改学生密码
        String pass = setPassWord();
        // 同步数据库
        String updSql = database.stpassSql(id, pass);
        database.dbstmt.Update(updSql);
    }

    public void learningCourse() {
        String queSql = database.scQueSql(this.id);
        List<HashMap<String, Object>> selectsc = database.dbstmt.Select(queSql);  // 查询sc
        System.out.println("课号 名字 类型(0必修1选修) 教师名称 学生数量 类型0学分/1最大选课人数");
        for (HashMap<String, Object> c: selectsc) {
            System.out.println((int)c.get("cid") + " " + (String)c.get("cname") + " "
                    + (int)c.get("ctype") + " " + (String)c.get("tname")
                    + " " + (int)c.get("num") + " " + (int)c.get("credit"));
        }
    }
    public void choiceCourse() {  // 选课
        System.out.println("编号  课程  类型  教师  选课人数  最大选课人数");
        for (Course c: Courses.clist) {
            if (c.type == 1)
                c.show();
        }
        while (true) {
            System.out.println("请选择课程(输入课程的课程号,输入-1退出):\n");
            Scanner sc = new Scanner(System.in);
            int id;
            try {
                id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("请输入正确的课程号！");
                continue;
            }
            if (id == -1) {
                System.out.println("退出选课界面！！");
                break;
            }
            int index = -1;  // 索引
            for (int i=0; i<Courses.clist.size(); ++i) {
                if (Courses.clist.get(i).id == id) {
                    index = i;
                    break;
                }
            }
            OptionalCourse course = (OptionalCourse)Courses.clist.get(index);
            if (index == -1) {
                System.out.println("该课程不存在！");
                continue;
            }
            if (course.num_people + 1 > course.getmaxNum()) {
                System.out.println("该课程已选满！");
                continue;
            }
            // 先查询是否选过了
            String queSql = database.scQueSql(id, this.id);
            List<HashMap<String, Object>> selectsc = database.dbstmt.Select(queSql);  // 查询sc
            if (selectsc.size() != 0) {  // 选过这门课了
                System.out.println("已选过此课，不能重复选择！");
                continue;
            }
            // 选课成功！ 更新oc 插入sc
            String insSql = database.scInsSql(id, this.id);
            database.dbstmt.Insert(insSql);  // 插入sc
            String updSql = database.ocUpdSql(id);
            database.dbstmt.Update(updSql);  // 插入sc
            System.out.println("该课程选课成功！！");
        }
    }
}


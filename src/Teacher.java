import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Teacher extends User {
    int workId; // 工号
    String level; // 职称：教授、副教授、讲师

    public Teacher(String name, String pass,
                   int id, String level) {
        this.name = name;
        this.setPass(pass);
        this.workId = id;
        this.level = level;
    }

    public Teacher(String name,
                   int id, String level) {
        this.name = name;
        this.workId = id;
        this.level = level;
    }

    public String toString() {
        return name + " " + workId + " " + level;
    }

    public void show() {
        System.out.print(workId + " " + name + " " + level);
    }
    public int login() {  // 管理员登录方法
        System.out.println("请输入工号：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.println("请输入密码：");
        String pass = sc.next();
        if (id==this.workId&&pass.equals(this.getPass())) {
            System.out.println(name + " "+ level + " 您好！");
            return 1;  // 登录成功
        }
        return 0;
    }

    public void settPassWord() {  // 修改教师密码
        String pass = setPassWord();
        // 同步数据库
        String updSql = database.tpassSql(workId, pass);
        database.dbstmt.Update(updSql);
    }

    public void teachingCourse() {  // 查看自己所授课程
//        String queSql1 = "SELECT * FROM requiredcourse\n" +
//                "where requiredcourse.tid=3 \n" +
//                "union\n" +
//                "SELECT * FROM optionalcourse \n" +
//                "where optionalcourse.tid=3";
        String queSql = database.tcQueSql(workId);
        // 从数据库查出所有课程信息
        List<HashMap<String, Object>> selectst = database.dbstmt.Select(queSql);
        System.out.println("课号 名字 类型(0必修1选修) 教师id 学生数量 类型0学分/1最大选课人数");
        for (HashMap<String, Object> c: selectst) {
            System.out.println((int)c.get("cid") + " " + (String)c.get("cname") + " "
                    + (int)c.get("ctype") + " " + (int)c.get("tid")
                    + " " + (int)c.get("num") + " " + (int)c.get("credit"));
        }
    }

    public void studentList() {  // 查看自己所授课程的学生
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查看学生名单的课程");
        int cid = sc.nextInt();
        String queSql = database.ctQueSql(workId, cid);
        List<HashMap<String, Object>> select = database.dbstmt.Select(queSql);
        if (select.size() == 0) {  // 不是ta教的课
            System.out.println("此课程非您所授！");
            return;
        }
        String queSql1 = database.stQueSql(cid);
        List<HashMap<String, Object>> selectst = database.dbstmt.Select(queSql1);
        System.out.println("学号   班级   姓名");
        for (HashMap<String, Object> c: selectst) {
            System.out.println((int)c.get("sid") + " " + (String)c.get("sclass") + " "
                    + (String)c.get("sname"));
        }
    }
}

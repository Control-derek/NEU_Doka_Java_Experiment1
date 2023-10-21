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
        System.out.println(workId + " " + name + " " + level);
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
}

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
    public int login() {  // 管理员登录方法
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
}


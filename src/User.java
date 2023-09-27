import java.util.Scanner;

public class User {
    String name;  // 用户名
    private String pass;  //

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    public User() {}
    public void show() {
        System.out.println("用户名：" + name);
    }
    public void setPassWord() {  // 修改用户密码
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("请输入新密码（6位）：");
            String xpass1 = sc.next();
            System.out.print("请再次输入密码（6位）");
            String xpass2 = sc.next();
            if (xpass1.equals(xpass2) && xpass1.length() == 6) {
                // 两次密码需一致
                this.pass = xpass1;
                break;
            }
            else continue;
        }
    }
    public int login() {  // 管理员登录方法
        System.out.println("请输入管理员密码：");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equals(this.pass))
            return 1; // 登录成功
        return 0;
    }
    public String getPass() {  // 获取密码
        return pass;
    }
    public void setPass(String pass) {  // 设定密码
        if (pass.length() == 6) {
            this.pass = pass;
        }
    }
}


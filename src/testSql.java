import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testSql {
    public static Connection connect() {
        Connection c = null;  // 初始化
        try {
            String url = "jdbc:postgresql://localhost:5432/Enrolled";
            String username = "postgres";
            String password = "212151";
            Class.forName("org.postgresql.Driver");  // 注册driver
            c = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public static void main(String[] args) {
        Connection c = connect();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "SELECT * FROM student";  // 查询
            ResultSet rs = stmt.executeQuery(sql);  // 执行查询
            while (rs.next()) {
                String sno = rs.getString(1);
                String sname = rs.getString(2);
                String ssex = rs.getString(3);
                int sage = rs.getInt(4);
                String sdept = rs.getString(5);
                System.out.println(sno+"　"+sname+" "+ssex+" "+sage+" "+sdept);
            }
            // 依次关流
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}



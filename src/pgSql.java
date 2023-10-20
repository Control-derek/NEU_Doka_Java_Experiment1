import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class pgSql {
    private final Connection connect;  //
    private final String userName;  // 用户名
    private final String passWord;  // 密码
    private final String ipAddress;  // ip地址
    private final String databaseName;  // 数据库名字
    private final String port;  // 数据库服务接口 默认为5432

    public pgSql(String userName, String passWord, String ipAddress, String databaseName, String port) {
        this.userName = userName;
        this.passWord = passWord;
        this.ipAddress = ipAddress;
        this.databaseName = databaseName;
        this.port = port;
        this.connect = this.Connect();
    }

    // 建立链接
    private Connection Connect() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");  // 自动注册driver
            c = DriverManager
                    .getConnection("jdbc:postgresql://" + this.ipAddress + ":" + this.port + "/" + this.databaseName,
                            this.userName, this.passWord);  // 构建连接
        } catch (Exception e) {  // 异常处理
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    // 关流操作
    public void close() {
        Connection c = this.connect;
        try {
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // 查询操作
    public List<HashMap<String, Object>> Select(String sql) {
        //得到之前建立的connection
        Connection c = this.connect;
        //创建statement
        Statement stmt = null;
        //创建返回最终查询的数据集合
        List<HashMap<String, Object>> list = new ArrayList<>();
        try {
            //初始化操作对象
            stmt = c.createStatement();
            //执行sql语句
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData(); // 获取元数据
            int columnCount = metaData.getColumnCount(); // 列的数量

            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    // 获取列名
                    String name = metaData.getColumnName(i);
                    // 获取对应的元素
                    Object object = rs.getObject(i);
                    map.put(name, object);
                }
                list.add(map);
            }
            //关流操作
            rs.close();
            stmt.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    // 插入操作
    public Boolean Insert(String sql) {
        //得到之前建立的connection
        Connection connect = this.connect;
        //创建statement
        Statement stmt = null;
        int count = 0;
        try {
            // 初始化创建对象
            stmt = connect.createStatement();
            // 取消自动提交
            connect.setAutoCommit(false);
            // 执行添加操作
            count = stmt.executeUpdate(sql);

            // 关流
            stmt.close();
            // 手动提交事务
            connect.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                // 回滚事务  这里其实没有必要 一般将多个语句封装为一个事务
                connect.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return count != 0;
    }

    // 更新操作
    public Boolean Update(String sql) {
        //得到之前建立的connection
        Connection connect = this.connect;
        //创建statement
        Statement stmt = null;
        int count = 0;
        try {
            // 初始化创建对象
            stmt = connect.createStatement();
            // 取消自动提交
            connect.setAutoCommit(false);
            // 执行添加操作
            count = stmt.executeUpdate(sql);

            // 关流
            stmt.close();
            // 手动提交事务
            connect.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                // 回滚事务  这里其实没有必要 一般将多个语句封装为一个事务
                connect.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return count != 0;
    }

    //删除
    public void Delete(String sql) {
        //得到之前建立的connection
        Connection c = this.Connect();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();

            stmt.executeUpdate(sql);

            stmt.close();
            // 手动提交事务
            c.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                // 回滚事务
                c.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}



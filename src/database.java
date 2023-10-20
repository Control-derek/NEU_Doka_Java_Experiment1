public class database {
    // 用来调用数据库的类
    // 方便在全局调用数据库
    // 不用重复连接
    static pgSql dbstmt = new pgSql("postgres", "212151",
            "localhost", "cs_system", "5432");  // 实例化数据库操作对象
    static String stInsSql(int id, String Class, String name, String pass) {
        StringBuffer stInsSql = new StringBuffer("insert into Student values(");
        stInsSql.append(id);
        stInsSql.append(", ");
        stInsSql.append("'").append(Class).append("'");
        stInsSql.append(", ");
        stInsSql.append("'").append(name).append("'");
        stInsSql.append(", ");
        stInsSql.append("'").append(pass).append("'").append(")");
        String insSql = new String(stInsSql);
        return insSql;
    }
    static String tInsSql(int id, String level, String name, String pass) {
        StringBuffer tInsSql = new StringBuffer("insert into teacher values(");
        tInsSql.append(id);
        tInsSql.append(", ");
        tInsSql.append("'").append(level).append("'");
        tInsSql.append(", ");
        tInsSql.append("'").append(name).append("'");
        tInsSql.append(", ");
        tInsSql.append("'").append(pass).append("'").append(")");
        String insSql = new String(tInsSql);
        return insSql;
    }
    static String stDelSql(int id) {
        StringBuffer delSql = new StringBuffer("delete from Student where sid = ");
        delSql.append(id);
        String sql = new String(delSql);
        return sql;
    }

    static String tDelSql(int id) {
        StringBuffer delSql = new StringBuffer("delete from teacher where tid = ");
        delSql.append(id);
        String sql = new String(delSql);
        return sql;
    }
    static String stpassSql(int id) {
        StringBuffer updSql = new StringBuffer("update Student set pass = '123456' where sid = ");
        updSql.append(id);
        String sql = new String(updSql);
        return sql;
    }

    static String tpassSql(int id) {
        StringBuffer updSql = new StringBuffer("update teacher set pass = '123456' where tid = ");
        updSql.append(id);
        String sql = new String(updSql);
        return sql;
    }
}

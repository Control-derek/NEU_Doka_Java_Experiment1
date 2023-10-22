public class database {
    // 用来调用数据库的类
    // 方便在全局调用数据库
    // 不用重复连接
    static pgSql dbstmt = new pgSql("postgres", "212151",
            "localhost", "cs_system", "5432");  // 实例化数据库操作对象
    static void init() {  // 初始化所有信息
        Users.init();  // 初始化学生、老师列表
        Courses.init();  // 初始化必修、选修课程列表

    }
    static String scQueSql(int cid, int sid) {
        StringBuffer scQueSql = new StringBuffer("select * from sc where cid = ");
        scQueSql.append(cid);
        scQueSql.append("and sid = ");
        scQueSql.append(sid);
        String queSql = new String(scQueSql);
        return queSql;
    }
    static String scQueSql(int sid) {  // 查看学生选了什么课
        StringBuffer scQueSql = new StringBuffer("select s.cid, s.cname, s.ctype, teacher.tname, s.num, s.credit from \n" +
                "(SELECT requiredcourse.*, sc.sid FROM requiredcourse, sc\n" +
                "where requiredcourse.cid = sc.cid\n" +
                "union\n" +
                "SELECT optionalcourse.*, sc.sid FROM optionalcourse, sc\n" +
                "where optionalcourse.cid = sc.cid) as s, teacher\n" +
                "where teacher.tid = s.tid and s.sid =");
        scQueSql.append(sid);
        String queSql = new String(scQueSql);
        return queSql;
    }
    static String stQueSql(int cid) {
        StringBuffer stQueSql = new StringBuffer("SELECT student.* FROM student, sc\n" +
                "where student.sid = sc.sid and sc.cid = ");
        stQueSql.append(cid);
        String queSql = new String(stQueSql);
        return queSql;
    }
    static String tcQueSql(int tid) {  // 查看教师所授课程
        StringBuffer queSqlb = new StringBuffer("SELECT * FROM requiredcourse \n" +
                "where requiredcourse.tid= ");
        queSqlb.append(tid);
        queSqlb.append(" union\n" +
                "SELECT * FROM optionalcourse \n" +
                "where optionalcourse.tid= ");
        queSqlb.append(tid);
        String queSql = new String(queSqlb);
        return queSql;
    }
    static String ctQueSql(int tid, int cid) {
        StringBuffer queSqlb = new StringBuffer("SELECT * FROM requiredcourse \n" +
                "where requiredcourse.tid = ");
        queSqlb.append(tid);
        queSqlb.append("and requiredcourse.cid = ").append(cid);
        queSqlb.append(" union\n" +
                "SELECT * FROM optionalcourse \n" +
                "where optionalcourse.tid= ");
        queSqlb.append(tid);
        queSqlb.append("and optionalcourse.cid = ").append(cid);
        String queSql = new String(queSqlb);
        return queSql;
    }

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
    static String scInsSql(int cid, int sid) {
        StringBuffer scInsSql = new StringBuffer("insert into sc(sid, cid) values(");
        scInsSql.append(sid).append(", ");
        scInsSql.append(cid).append(")");
        String insSql = new String(scInsSql);
        return insSql;
    }
    static String rsInsSql(int id, String name, int type, int tid, int num, int credit) {
        StringBuffer rsInsSql = new StringBuffer("insert into requiredcourse values(");
        rsInsSql.append(id);
        rsInsSql.append(", ");
        rsInsSql.append("'").append(name).append("'");
        rsInsSql.append(", ");
        rsInsSql.append("'").append(type).append("'");
        rsInsSql.append(", ");
        rsInsSql.append("'").append(tid).append("'");
        rsInsSql.append(", ");
        rsInsSql.append("'").append(num).append("'");
        rsInsSql.append(", ");
        rsInsSql.append("'").append(credit).append("'").append(")");
        String insSql = new String(rsInsSql);
        return insSql;
    }
    static String osInsSql(int id, String name, int type, int tid, int num, int maxnum) {
        StringBuffer osInsSql = new StringBuffer("insert into optionalcourse values(");
        osInsSql.append(id);
        osInsSql.append(", ");
        osInsSql.append("'").append(name).append("'");
        osInsSql.append(", ");
        osInsSql.append("'").append(type).append("'");
        osInsSql.append(", ");
        osInsSql.append("'").append(tid).append("'");
        osInsSql.append(", ");
        osInsSql.append("'").append(num).append("'");
        osInsSql.append(", ");
        osInsSql.append("'").append(maxnum).append("'").append(")");
        String insSql = new String(osInsSql);
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
    static String cDelSql(String name) {
        StringBuffer delSql = new StringBuffer("delete from optionalcourse where cname = ");
        delSql.append("'").append(name).append("';");
        delSql.append("delete from requiredcourse where cname = ");
        delSql.append("'").append(name).append("';");
        String sql = new String(delSql);
        return sql;
    }
    static String stpassSql(int id, String password) {
        StringBuffer updSql = new StringBuffer("update Student set pass = ");
        updSql.append("'").append(password).append("' ");
        updSql.append("where sid = ");
        updSql.append(id);
        String sql = new String(updSql);
        return sql;
    }

    static String tpassSql(int id, String password) {
        StringBuffer updSql = new StringBuffer("update teacher set pass = ");
        updSql.append("'").append(password).append("' ");
        updSql.append("where tid = ");
        updSql.append(id);
        String sql = new String(updSql);
        return sql;
    }
    static String ocUpdSql(int cid) {
        StringBuffer ocUpdSql = new StringBuffer("update optionalcourse set num = num + 1 where cid = ");
        ocUpdSql.append(cid);
        String updSql = new String(ocUpdSql);
        return updSql;
    }
    static String cUpdSql(String name, int tid) {
        StringBuffer updSql = new StringBuffer("update optionalcourse set tid = ");
        updSql.append("'").append(tid).append("' ");
        updSql.append("where cname = ");
        updSql.append("'").append(name).append("' ; ");
        updSql.append("update requiredcourse set tid = ");
        updSql.append("'").append(tid).append("' ");
        updSql.append("where cname = ");
        updSql.append("'").append(name).append("' ");
        String sql = new String(updSql);
        return sql;
    }
}

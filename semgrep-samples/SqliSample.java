package semgrepsamples;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;

public class SqliSample {
    public ResultSet lookup(Connection conn, HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        String sql = "SELECT * FROM users WHERE name = '" + name + "'";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }

    public void update(Connection conn, HttpServletRequest req) throws Exception {
        String id = req.getParameter("id");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM users WHERE id = " + id);
    }
}

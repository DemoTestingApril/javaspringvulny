package test;
import java.sql.*;
public class VulnTest {
    public void run(Connection c, String userInput) throws Exception {
        Statement s = c.createStatement();
        s.executeQuery("SELECT * FROM users WHERE name='" + userInput + "'");
    }
}

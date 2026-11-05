package hawk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportQueryController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/api/reports/by-owner")
    public List<String> reportsByOwner(@RequestParam("owner") String owner) throws Exception {
        List<String> names = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "select name from ITEM where owner = '" + owner + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        }
        return names;
    }
}

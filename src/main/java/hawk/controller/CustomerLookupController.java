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

/**
 * Deliberate SQL injection fixture for TestingSlice PR comment policy.
 * Do not merge.
 */
@RestController
public class CustomerLookupController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/api/customers/lookup")
    public List<String> lookupByEmail(@RequestParam("email") String email) throws Exception {
        List<String> results = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "select name from users where email = '" + email + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                results.add(rs.getString("name"));
            }
        }
        return results;
    }
}

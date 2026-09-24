package hawk.reports;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLXML;

/** Hop 3 of 6 — reads the stored XML payload back out of the archive table. */
@Repository
public class ArchiveRepository {

    public SQLXML fetchDocument(Connection connection, String reportId) throws Exception {
        PreparedStatement statement =
                connection.prepareStatement("select payload from REPORT_ARCHIVE where id = ?");
        statement.setString(1, reportId);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }
        return resultSet.getSQLXML("payload");
    }
}

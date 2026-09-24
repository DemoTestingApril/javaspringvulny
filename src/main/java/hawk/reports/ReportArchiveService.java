package hawk.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLXML;

/**
 * Hop 2 of 6 — opens the H2 archive connection.
 *
 * The {@link DriverManager#getConnection} call below is the taint source for
 * CVE-2021-23463: everything read back through this connection is attacker
 * influenceable, because report payloads are user submitted.
 */
@Service
public class ReportArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Autowired
    private DocumentPipeline documentPipeline;

    public String renderArchivedReport(String reportId) throws Exception {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:h2:mem:reports;DB_CLOSE_DELAY=-1", "sa", "")) {
            SQLXML storedDocument = archiveRepository.fetchDocument(connection, reportId);
            return documentPipeline.process(storedDocument);
        }
    }
}

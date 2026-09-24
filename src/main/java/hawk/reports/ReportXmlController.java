package hawk.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Hop 1 of 6 — HTTP entry point for the archived-report renderer. */
@RestController
public class ReportXmlController {

    @Autowired
    private ReportArchiveService reportArchiveService;

    @GetMapping("/api/reports/archived")
    public String renderArchivedReport(@RequestParam("id") String reportId) throws Exception {
        return reportArchiveService.renderArchivedReport(reportId);
    }
}

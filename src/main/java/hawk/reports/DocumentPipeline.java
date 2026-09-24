package hawk.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLXML;

/** Hop 4 of 6 — pipeline stage that hands the payload to the normalizer. */
@Component
public class DocumentPipeline {

    @Autowired
    private DocumentNormalizer documentNormalizer;

    public String process(SQLXML storedDocument) throws Exception {
        if (storedDocument == null) {
            return "";
        }
        return documentNormalizer.normalize(storedDocument);
    }
}

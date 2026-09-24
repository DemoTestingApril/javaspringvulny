package hawk.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLXML;

/** Hop 5 of 6 — normalizes the archived document before rendering. */
@Component
public class DocumentNormalizer {

    @Autowired
    private XmlSourceExtractor xmlSourceExtractor;

    public String normalize(SQLXML storedDocument) throws Exception {
        return xmlSourceExtractor.extractRootName(storedDocument);
    }
}

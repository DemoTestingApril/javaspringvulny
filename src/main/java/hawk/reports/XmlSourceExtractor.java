package hawk.reports;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.xml.transform.dom.DOMSource;
import java.sql.SQLXML;

/**
 * Hop 6 of 6 — taint sink for CVE-2021-23463.
 *
 * H2 parses this XML with external entities enabled, so an attacker who can
 * influence the stored payload can read local files or reach internal network
 * resources.
 */
@Component
public class XmlSourceExtractor {

    public String extractRootName(SQLXML storedDocument) throws Exception {
        DOMSource domSource = storedDocument.getSource(DOMSource.class);
        Node rootNode = domSource.getNode();
        return rootNode == null ? "" : rootNode.getNodeName();
    }
}

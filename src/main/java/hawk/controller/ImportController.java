package hawk.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class ImportController {

    @PostMapping(value = "/api/import/inventory", consumes = MediaType.APPLICATION_XML_VALUE)
    public String importInventory(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setExpandEntityReferences(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        return document.getDocumentElement().getNodeName();
    }
}

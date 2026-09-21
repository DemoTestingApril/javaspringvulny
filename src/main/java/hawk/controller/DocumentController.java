package hawk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DocumentController {

    private static final String DOCUMENT_ROOT = "/var/app/documents/";

    @GetMapping("/api/documents/raw")
    public String readDocument(@RequestParam("name") String name) throws Exception {
        Path path = Paths.get(DOCUMENT_ROOT + name);
        return new String(Files.readAllBytes(path));
    }
}

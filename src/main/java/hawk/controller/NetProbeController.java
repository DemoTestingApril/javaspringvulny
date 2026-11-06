package hawk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Deliberate command injection fixture for TestingSlice PR comment policy demo.
 * Do not merge.
 */
@RestController
public class NetProbeController {

    @GetMapping("/api/net/probe")
    public String probe(@RequestParam("target") String target) throws Exception {
        // HIGH/ERROR: untrusted input into Runtime.exec
        Process process = Runtime.getRuntime().exec("ping -c 1 " + target);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }
            process.waitFor();
            return output.toString();
        }
    }
}

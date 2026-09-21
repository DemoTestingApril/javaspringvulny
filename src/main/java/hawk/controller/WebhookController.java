package hawk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebhookController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/webhooks/preview")
    public String preview(@RequestParam("callbackUrl") String callbackUrl) {
        return restTemplate.getForObject(callbackUrl, String.class);
    }
}

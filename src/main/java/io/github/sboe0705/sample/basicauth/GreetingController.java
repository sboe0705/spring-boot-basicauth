package io.github.sboe0705.sample.basicauth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @RequestMapping("/greet")
    public String greet(Principal principal) {
        String username = principal.getName();
        return String.format("Hallo, %s!", username);
    }

}

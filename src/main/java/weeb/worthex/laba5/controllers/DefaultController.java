package weeb.worthex.laba5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping
    public String index() {
        return "index";
    }
}

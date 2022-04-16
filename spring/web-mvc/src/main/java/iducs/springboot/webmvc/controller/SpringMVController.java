package iducs.springboot.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringMVController {
    @GetMapping("/")
    public String get_home() {
        return "index";
    }
}

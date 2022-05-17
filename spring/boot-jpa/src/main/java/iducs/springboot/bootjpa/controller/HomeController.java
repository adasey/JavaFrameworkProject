package iducs.springboot.bootjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String getMembersIndex() {
        return "members/index";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}

package iducs.springboot.bootjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String getHome() {
        return "index";
    }

    @GetMapping("members/simple") // url - localhost:8888/members/simple
    public String getSimple() {
        return "member";
    }
}

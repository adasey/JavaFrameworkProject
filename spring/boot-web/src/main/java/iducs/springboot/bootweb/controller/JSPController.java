package iducs.springboot.bootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
public class JSPController {
    @GetMapping("/")
    public String get_home(){
        // return "<h1>index Home Page</h1>"; rest
        return "index"; // view의 이름 controller 에서 view로 넘어감.
    }

package iducs.springboot.boothello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {

    @GetMapping("/")
    public String home() {
        return "<h2>spring boot home</h2> \n <a href=\'/boot\'>Boot</a>";
    }

    @GetMapping("/boot")
    public String boot() {
        return "<h3>spring boot</h3>";
    }
}

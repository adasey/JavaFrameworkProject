package iducs.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StartApplication {
    @GetMapping("/spring")
    public String spring() {
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
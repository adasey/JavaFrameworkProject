package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

// 모든 페이지는 controll을 통해 접근
@Controller
public class MemoController {
    @Autowired
    MemberService memberService;

    @GetMapping("/tables") // default setting 대신 /index를 통해서 접근하도록 설정 기본은 그냥 localhost:8888에서 index로 접근
    public String getIndex(Model model) {
        List<MemberEntity> list = memberService.readAll();
        model.addAttribute("members", list);
        return "tables";
    }

    @GetMapping("/{idx}") // default setting 대신 /index를 통해서 접근하도록 설정 기본은 그냥 localhost:8888에서 index로 접근
    public String getMember(@PathVariable("idx") Long seq, Model model) {
        Optional<MemberEntity> member = memberService.readById(seq);
        MemberEntity mem = member.get();
        model.addAttribute("member", mem);
        return "index";
    }

    @GetMapping("/header")
    public String getHeader(){
        return "header";
    }

    @GetMapping("/tables")
    public String getTables(){
        return "tables";
    }

    @GetMapping("/charts")
    public String getCharts(){
        return "charts";
    }

    @GetMapping("/404")
    public String getFourOFour(){
        return "404";
    }

    @GetMapping("/blank")
    public String getBlank(){
        return "blank";
    }

    @GetMapping("/buttons")
    public String getButtons(){
        return "buttons";
    }

    @GetMapping("/cards")
    public String getCards(){
        return "cards";
    }

    @GetMapping("/forgot-password")
    public String getForgotPassword(){
        return "forgot-password";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("/utilities-animation")
    public String getUtilitiesAnimation(){
        return "utilities-animation";
    }

    @GetMapping("/utilities-border")
    public String getUtilitiesBorder(){
        return "utilities-border";
    }

    @GetMapping("/utilities-color")
    public String getUtilitiesColor(){
        return "utilities-color";
    }

    @GetMapping("/utilities-other")
    public String getUtilitiesOther(){
        return "utilities-other";
    }
}

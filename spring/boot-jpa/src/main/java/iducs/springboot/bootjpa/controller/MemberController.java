package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 모든 페이지는 controll을 통해 접근
@Controller
@RequestMapping("/members")
public class MemberController {
    final MemberService memberService;

    // -> 위의 구문 = MemberService ms = new MemberServiceImpl() 와 같음 spring의 기능
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/regform")
    public String getRegform(Model model) {
        model.addAttribute("member", Member.builder().build());
        return "members/regform";
    }

    @PostMapping("")
    public String postMember(@ModelAttribute("member") Member member, Model model) {
        memberService.create(member);
        model.addAttribute("member", member);
        return "redirect:members/home"; // 해당 방식으로 리턴에 getMember 함수 호출 시 URI mapping 적용안됨. 이 post 함수 이후로 members에 접근이 가능하나
        // URI 상 members와 members/info의 차이가 있으므로 주의
    }

    @GetMapping("/home")
    public String getHome() {
        return "members/home";
    }

    @GetMapping("/member")
    public String getMembers(Model model) {
        List<Member> groupMember = memberService.readAll();
        model.addAttribute("members", groupMember);
        return "members/member";
    }

    @GetMapping("/{idx}")
    public String getMember(@PathVariable("idx") Long seq, Model model) {
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "members/info";
    }

    @GetMapping("/{idx}/upform")
    public String getUpform(@PathVariable("idx") Long seq, Model model) {
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "members/upform";
    }

    @PutMapping("/{idx}")
    public String putMember(@ModelAttribute("member") Member member, Model model) {
        memberService.update(member);
        model.addAttribute("member", member);
        return "members/member";
    }

    @GetMapping("/{idx}/delform")
    public String getDelform(@PathVariable("idx") Long seq, Model model) {
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "members/delform";
    }

    @DeleteMapping("/{idx}")
    public String delMember(@ModelAttribute("member") Member member, Model model) {
        memberService.delete(member);
        model.addAttribute("member", member);
        return "redirect:members/member"; // members/member에 새롭게
    }
}
//    @GetMapping("/{idx}")
//    public String getMember(@PathVariable("idx") Long mno, Model model) {
//        Member member = memberService.readById(mno);
//        model.addAttribute("member", member);
//        return "/members/member";
//    }

//    @GetMapping("/{idx}/upform")
//    public String getUpform(@PathVariable("idx") Long mno, Model model) {
//        Member member = memberService.readById(mno);
//        model.addAttribute("member", member);
//        return "/members/upform";
//    }

//    @PutMapping("/{idx}")
//    public String putMember(@ModelAttribute("member") Member member, Model model) {
//        memberService.update(member);
//        model.addAttribute("member", member);
//        return "/members/member";
//    }

//
//    @GetMapping("/th")
//    public String getThymeleaf(){
//        return "thymeleaf";
//    }
//


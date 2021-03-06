package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.service.MemberPageService;
import iducs.springboot.bootjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 모든 페이지는 controll을 통해 접근
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberPageService memberPageService;

    // -> 위의 구문 = MemberService ms = new MemberServiceImpl() 와 같음 spring의 기능
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/registerForm")
    public String getRegform(Model model) {
        model.addAttribute("member", Member.builder().build());
        return "members/registerForm";
    }

    @PostMapping("")
    public String postMember(@ModelAttribute("member") Member member, Model model) {
        memberService.create(member);
        model.addAttribute("member", member);
        return "redirect:/members/lists"; // 해당 방식으로 리턴에 getMember 함수 호출 시 URI mapping 적용안됨. 이 post 함수 이후로 members에 접근이 가능하나
        // URI 상 members와 members/info의 차이가 있으므로 주의
    }

    @GetMapping("")
    public String getIndex() {
        return "members/index";
    }

    @GetMapping("/lists")
    public String getMembers(PageRequestDTO pageRequestDTO, Model model) {
        log.info("controller take object : {}", pageRequestDTO);
        model.addAttribute("list", memberPageService.readListBy(pageRequestDTO));
        return "members/lists";
    }

    @GetMapping("/{idx}")
    public String getMember(@PathVariable("idx") Long seq, Model model) {
        Member member = memberService.readById(seq);
        if (member == null) {
            return "members/lists";
        }
        model.addAttribute("member", member);
        return "members/info";
    }

    @GetMapping("/{idx}/updateForm")
    public String getUpform(@PathVariable("idx") Long seq, Model model) {
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "members/updateForm";
    }

    @PutMapping("/{idx}")
    public String putMember(@ModelAttribute("member") Member member, Model model) {
        memberService.update(member);
        // 전체 값을 넘겨서 update 할 시 문제점 : seq가 오류나 html에서 넘어가지 않으면 새로 insert하게 됨. 아예 업데이트가 이루어지지 않아야함.
        model.addAttribute("member", member);
        return "redirect:members/lists";
    }

    @DeleteMapping("/{idx}")
    public String delMember(@ModelAttribute("idx") Long seq) {
        memberService.delete(memberService.readById(seq));
        return "redirect:/members/lists"; // members/member에 새롭게
    }
}
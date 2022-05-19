package iducs.springboot.bootjpa.controller;


import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.Memo;
import iducs.springboot.bootjpa.entity.MemoEntity;
import iducs.springboot.bootjpa.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 모든 페이지는 controll을 통해 접근
@Controller
@RequestMapping("/memos")
public class MemoController {
    final MemoService memoService;
    public MemoController(MemoService memoService) { this.memoService = memoService; }

    @GetMapping("/regform")
    public String getRegform(Model model) {
        model.addAttribute("memo", Memo.builder().build());
        return "memos/regform";
    }

    @PostMapping("")
    public String postMemo(@ModelAttribute("memo") Memo memo, Model model) {
        memoService.create(memo);
        model.addAttribute("memo", memo);
        return "memos/memo";
    }

    @GetMapping("/memo")
    public String getMemos(Model model) {
        List<Memo> memos = memoService.readAll();
        model.addAttribute("list", memos);
        return "memos/home";
    }

    @GetMapping("/{idx}")
    public String getMemo(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno);
        model.addAttribute("memo", memo);
        return "memos/memo";
    }

    @GetMapping("/{idx}/upform")
    public String getUpform(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno);
        model.addAttribute("memo", memo);
        return "memos/upform";
    }

    @PutMapping("/{idx}")
    public String putMemo(@ModelAttribute("memo") Memo memo, Model model) {
        memoService.update(memo);
        model.addAttribute("memo", memo);
        return "memos/memo";
    }

    @GetMapping("/{idx}/delform")
    public String getDelform(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno);
        model.addAttribute("memo", memo);
        return "memos/delform";
    }

    @DeleteMapping("/{idx}")
    public String delMemo(@ModelAttribute("memo") Memo memo, Model model) {
        memoService.delete(memo);
        model.addAttribute("memo", memo);
        return "redirect:memos/memo"; // memos/info에 새롭게
    }
}

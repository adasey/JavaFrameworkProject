package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.service.BoardPageService;
import iducs.springboot.bootjpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardPageService boardPageService;

    @GetMapping("")
    public String getBoards(PageRequestDTO pageRequestDTO, Model model) {
        // ?page=nn&size=mm : mm 페이지 단위로 nn 페이지 접근. 만일 mm = 5, nn = 3 -> 11 ~ 15 레코드 접근
        // new PageRequestDTO(3, 5) : 초기화
        model.addAttribute("list", boardService.getList(pageRequestDTO));
        return "/boards/lists";
    }

    @GetMapping("/registerForm")
    public String getRegForm(Model model) {
        model.addAttribute("dto", Board.builder().build()); // board 객체 생성 후 전달.
        return "/boards/registerForm";
    }

    @PostMapping
    public String postRegForm(@ModelAttribute("board") Board board) {
        boardService.register(board);
        return "redirect:/boards/lists";
    }

    @GetMapping("/{bor_id}")
    public String getInfo(@RequestParam("bor_id") Long bor_id, Model model) {
        model.addAttribute("dto", boardService.getById(bor_id));
        return "/boards/info";
    }
}

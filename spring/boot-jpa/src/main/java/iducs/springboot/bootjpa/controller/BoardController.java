package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("")
    public String getBoards(PageRequestDTO pageRequestDTO, Model model) {
        // ?page=nn&size=mm : mm 페이지 단위로 nn 페이지 접근. 만일 mm = 5, nn = 3 -> 11 ~ 15 레코드 접근
        // new PageRequestDTO(3, 5) : 초기화
        model.addAttribute("list", boardService.getList(pageRequestDTO));
        return "boards/lists";
    }
}

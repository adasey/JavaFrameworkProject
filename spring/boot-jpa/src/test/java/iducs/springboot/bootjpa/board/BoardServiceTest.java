package iducs.springboot.bootjpa.board;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void testRegister() {
        IntStream.rangeClosed(4301, 4347).forEach(i -> {
            Board dto = Board.builder()
                    .title("title " + i)
                    .content("Content.....")
                    .writerSeq(Long.valueOf("" + i))
                    .build();

            Long bor_id = boardService.register(dto);
        });
    }

    @Test
    void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(3);
        PageResultDTO<Board, Object[]> result = boardService.getList(pageRequestDTO);
        for (Board dto : result.getDtoList()) {
            System.out.println(dto.getBor_id() + ", " + dto.getTitle());
        }
    }

}

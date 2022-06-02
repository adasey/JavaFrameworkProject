package iducs.springboot.bootjpa.board;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import iducs.springboot.bootjpa.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testRegister() {
        IntStream.rangeClosed(1, 47).forEach(i -> {
            Long seqLong = Long.valueOf(new Random().nextInt(50));
            seqLong = (seqLong == 0) ? 1 : seqLong;
            Board dto = Board.builder()
                    .title("title " + i)
                    .content("Content.....")
                    .writerSeq(seqLong) // member entity의 seq 값 중 존재하는 값이여야 한다.
                    .build();

            Long bor_id = boardService.register(dto);
        });
    }

    @Test
    void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(3);
        pageRequestDTO.setSize(4);
        PageResultDTO<Board, Object[]> result = boardService.getList(pageRequestDTO);
        for (Board dto : result.getDtoList()) {
            System.out.println(dto.getBor_id() + ", " + dto.getTitle());
        }
        System.out.println("result = " + result);
        System.out.println("pageRequestDTO = " + pageRequestDTO);
    }

    @Transactional // spring transactional db에 대해 매번 새롭게 접근하게 해줌.
    @Test
    void testLazyLoading() {
        Optional<BoardEntity> result = boardRepository.findById(10L);
        BoardEntity boardEntity = result.get();
        System.out.println("Bor_id = " + boardEntity.getBor_id());
        System.out.println("Writer = " + boardEntity.getWriter());
    }

}

package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;

public interface BoardService {
    Long register(Board dto);
    Long modifyById(Board dto);
    Board getById(Long bor_id);
    void deleteWithRepliesById(Long bor_id); // with : join 관계된 값들을 전부 삭제함.
    PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO);
}

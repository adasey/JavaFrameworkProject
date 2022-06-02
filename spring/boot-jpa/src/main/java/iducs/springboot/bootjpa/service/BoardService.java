package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;

public interface BoardService {
    Long register(Board dto);
    Long modifyById(Board dto);
    Board getById(Long bor_id);
    void deleteWithRepliesById(Long bor_id);
    PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO);
}

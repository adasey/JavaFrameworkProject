package iducs.springboot.bootjpa.service;

import com.querydsl.core.BooleanBuilder;
import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;

public interface BoardPageService {
    PageResultDTO<Board, Object[]> readListBy(PageRequestDTO pageRequestDTO);
    BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO);
}

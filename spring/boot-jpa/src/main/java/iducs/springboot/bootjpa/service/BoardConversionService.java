package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;

public interface BoardConversionService {
    BoardEntity dtoToEntity(Board dto);
    Board entityToDto(BoardEntity entity, MemberEntity member, Long replyCount);
}

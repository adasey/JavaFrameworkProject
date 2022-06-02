package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;

public interface BoardConversionService {
    // 이로부터 구현되는 모든 impl들에 공통적으로 존재하게 되는 코드
    BoardEntity dtoToEntity(Board dto);
    Board entityToDto(BoardEntity entity, MemberEntity member, Long replyCount);
}

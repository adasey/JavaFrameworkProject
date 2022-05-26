package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;

public interface BoardService {
    Long register(Board dto);

    PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO);
    
    // 이로부터 구현되는 모든 impl들에 공통적으로 존재하게 되는 코드
    default BoardEntity dtoToEntity(Board dto) {
        MemberEntity memberEntity = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();
        BoardEntity entity = BoardEntity.builder()
                .bor_id(dto.getBor_id())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(memberEntity)
                .build();

        return entity;
    }

    default Board entityToDto(BoardEntity entity, MemberEntity member, Long replyCount) {
        Board dto = Board.builder()
                .bor_id(entity.getBor_id())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerSeq(member.getSeq())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .replyCount(replyCount.intValue())
                .build();

        return dto;
    }
}

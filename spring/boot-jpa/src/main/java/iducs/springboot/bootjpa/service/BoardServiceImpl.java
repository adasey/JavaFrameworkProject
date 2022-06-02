package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService, BoardConversionService {
    private final BoardRepository boardRepository;

    @Override
    public Long register(Board dto) {
        log.info("board register : " + dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        boardRepository.save(boardEntity);
        return boardEntity.getBor_id();
    }

    @Override
    public Long modifyById(Board dto) {
        return null;
    }

    @Override
    public Board getById(Long bor_id) {
        Object result = boardRepository.getBoardWithWriter(bor_id);
        Object[] resultEntity = (Object[]) result;
        return entityToDto((BoardEntity) resultEntity[0], (MemberEntity) resultEntity[1], (Long) resultEntity[2]);
    }

    @Override
    public void deleteWithRepliesById(Long bor_id) {

    }

    @Override
    public PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(">>>>" + pageRequestDTO);
        // entites -> object[], dto - board
        Function<Object[], Board> fn = (entities -> entityToDto((BoardEntity) entities[0], (MemberEntity) entities[1], (Long) entities[2]));
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bor_id").descending()));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardEntity dtoToEntity(Board dto) {
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

    @Override
    public Board entityToDto(BoardEntity entity, MemberEntity member, Long replyCount) {
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

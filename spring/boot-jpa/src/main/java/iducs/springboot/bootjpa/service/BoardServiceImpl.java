package iducs.springboot.bootjpa.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.entity.QBoardEntity;
import iducs.springboot.bootjpa.entity.QMemberEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import iducs.springboot.bootjpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService, BoardPageService, BoardConversionService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(Board dto) {
        log.info("board register : " + dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        boardRepository.save(boardEntity);
        return boardEntity.getBor_id();
    }

    @Override
    public Long modifyById(Board dto) {
        Optional<BoardEntity> result = boardRepository.findById(dto.getBor_id());
        BoardEntity boardEntity = null;

        if (result.isPresent()) {
            boardEntity = (BoardEntity) result.get();
            boardEntity.changeTitle(dto.getTitle());
            boardEntity.changeContent(dto.getContent());
            boardRepository.save(boardEntity);
        }

        return boardEntity.getBor_id();
    }

    @Override
    public Board getById(Long bor_id) {
        Object result = boardRepository.getBoardByBor_id(bor_id);
        Object[] resultEntity = (Object[]) result;

        return entityToDto((BoardEntity) resultEntity[0], (MemberEntity) resultEntity[1], (Long) resultEntity[2]);
    }

    @Transactional // 실행 중단에도 계속 실행됨.
    @Override
    public void deleteWithRepliesById(Long bor_id) {
        replyRepository.deleteByBor_id(bor_id);
        boardRepository.deleteById(bor_id); // board record 객체 삭제
    }

    @Override
    public PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(">>>>" + pageRequestDTO);
        // entites -> object[], dto - board
        Function<Object[], Board> fn = (entities -> entityToDto((BoardEntity) entities[0], (MemberEntity) entities[1], (Long) entities[2]));
        log.info("get test entities : {}", fn);
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bor_id").descending()));

        return new PageResultDTO<>(result, fn);
    }

//    @Query(value = "select b, w, count(r) " + "from BoardEntity b left join b.writer w " +
//            "left join ReplyEntity r on r.boardEntity = b " + "group by b",
//            countQuery = "select  count(b) from BoardEntity b")

    @Override
    public PageResultDTO<Board, Object[]> readListBy(PageRequestDTO pageRequestDTO) {
        log.info("read start");
        Sort sort = pageRequestDTO.getOrder() == 0 ? Sort.by("bor_id").descending() : Sort.by("bor_id").ascending();
        Pageable pageable = pageRequestDTO.getPageable(sort);

        BooleanBuilder booleanBuilder = findByCondition(pageRequestDTO);

        Page<BoardEntity> result = boardRepository.findAll(booleanBuilder, pageable);
        Function<Object[], Board> fn = (entities -> entityToDto((BoardEntity) entities[0], (MemberEntity) entities[1], (Long) entities[2]));
        log.info("test : {}", fn);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;

        BooleanExpression expression = qBoardEntity.bor_id.gt(0L); // where seq > 0 and title == "ti"
        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }
        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")) // title로 검색
            conditionBuilder.or(qBoardEntity.title.contains(keyword));
        if(type.contains("u")) // 유저 id로 검색
            conditionBuilder.or(qBoardEntity.writer.id.contains(keyword));
        if(type.contains("b")) // 내용으로 검색
            conditionBuilder.or(qBoardEntity.bor_id.like(keyword));

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder; // 완성된 조건 or 술어(predicate)
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

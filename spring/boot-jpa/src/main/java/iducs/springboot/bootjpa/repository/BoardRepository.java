package iducs.springboot.bootjpa.repository;

import iducs.springboot.bootjpa.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// JPARepository 인터페이스를 상속받아 save 메소드 사용.
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, QuerydslPredicateExecutor<BoardEntity> {
    @Query("select b, w from BoardEntity b left join b.writer w where b.bor_id =:bor_id") // jpqlD
    Object getBoardWithWriter(@Param("bor_id") Long bor_id);

    @Query("select b, w, count(r) from BoardEntity b left join b.writer w left join ReplyEntity r on r.boardEntity = b where b.bor_id = :bor_id") // jpqlD
    Object getBoardByBor_id(@Param("bor_id") Long bor_id);

//    @Query(value = "select b, w, count(r) " + "from BoardEntity b left join b.writer w " +
//            "left join ReplyEntity r on r.boardEntity = b where booleanBuilder group by b",
//            countQuery = "select  count(b) from BoardEntity b") // 복잡한 질의를 자바로 객체를 통해 저리하기 위함.
//    Page<Object[]> getBoardWithReplyCount(BooleanBuilder booleanBuilder, Pageable pageable);

    @Query(value = "select b, w, count(r) " + "from BoardEntity b left join b.writer w " +
            "left join ReplyEntity r on r.boardEntity = b " + "group by b",
            countQuery = "select  count(b) from BoardEntity b") // 복잡한 질의를 자바로 객체를 통해 저리하기 위함.
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
}

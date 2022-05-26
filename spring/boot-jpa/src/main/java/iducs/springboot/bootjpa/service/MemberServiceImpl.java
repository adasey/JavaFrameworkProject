package iducs.springboot.bootjpa.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.entity.QMemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void create(Member member) {
        // .seq(member.getSeq())
        MemberEntity entity = dtoToEntity(member);

        memberRepository.save(entity);
    }

    @Override
    public Member readById(Long seq) {
        Member member = null;

        Optional<MemberEntity> result = memberRepository.findById(seq);

        if (result.isPresent()) { // result의 값이 입력 받았는가?
            member = entityToDto(result.get());
        }

        return member;
    }

    @Override
    public List<Member> readAll() {
        List<Member> members = new ArrayList<>();
        List<MemberEntity> entities = memberRepository.findAll();
        // JpaRepository 구현체의 메소드 findAll(), List<T>

        for (MemberEntity entity : entities) {
            Member member = entityToDto(entity);
            members.add(member);
        }

        return members;
    }

    @Override
    public PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("seq").descending());

        BooleanBuilder booleanBuilder = findByCondition(pageRequestDTO);

        Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);
        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

        BooleanExpression expression = qMemberEntity.seq.gt(0L); // where seq > 0 and title == "ti"
        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }
        String keyword = pageRequestDTO.getKeyword();

        System.out.println(qMemberEntity.email);

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")) // email로 검색
            conditionBuilder.or(qMemberEntity.email.contains(keyword));
        if(type.contains("p")) // phone로 검색
            conditionBuilder.or(qMemberEntity.phone.contains(keyword));
        if(type.contains("a")) // address로 검색
            conditionBuilder.or(qMemberEntity.address.contains(keyword));

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder; // 완성된 조건 or 술어(predicate)
    }


    @Override
    public void update(Member member) {
        MemberEntity entity = dtoToEntity(member);

        memberRepository.save(entity);
    }

    @Override
    public void delete(Member member) {
        MemberEntity entity = dtoToEntity(member);
        //memberRepository.delete(entity); entity로 들어온 값 삭제
        memberRepository.deleteById(entity.getSeq());
    }
}

package iducs.springboot.bootjpa.service;

import com.querydsl.core.BooleanBuilder;
import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.MemberEntity;

public interface MemberPageService {
    PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO);
    BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO);
}

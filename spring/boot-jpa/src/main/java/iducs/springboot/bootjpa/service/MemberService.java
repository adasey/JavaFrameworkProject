package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void create(Member member);

    Member readById(Long seq);
    List<Member> readAll();
    PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO);

    void update(Member member);
    void delete(Member member);

    default Member entityToDto(MemberEntity entity) {
        Member dto = Member.builder()
                .seq(entity.getSeq())
                .id(entity.getId())
                .pw(entity.getPw())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();

        return dto;
    }

    default MemberEntity dtoToEntity(Member member) {
        MemberEntity entity = MemberEntity.builder()
                .seq(member.getSeq())
                .id(member.getId())
                .pw(member.getPw())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();

        return entity;
    }
}

package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void create(Member member) {
        // .seq(member.getSeq())
        MemberEntity entity = MemberEntity.builder()
                .id(member.getId())
                .pw(member.getPw())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();

        memberRepository.save(entity);
    }

    @Override
    public Member readById(Long seq) {
        Member member = null;

        Optional<MemberEntity> result = memberRepository.findById(seq);

        if (result.isPresent()) { // result의 값이 입력 받았는가?
            member = member.builder()
                    .seq(result.get().getSeq())
                    .id(result.get().getId())
                    .pw(result.get().getPw())
                    .name(result.get().getName())
                    .email(result.get().getEmail())
                    .phone(result.get().getPhone())
                    .address(result.get().getAddress())
                    .build();
        }

        return member;
    }

    @Override
    public List<Member> readAll() {
        Member member = null;

        List<MemberEntity> entities = memberRepository.findAll();
        List<Member> members = new ArrayList<>(entities.size());

        for (int i = 0; i < entities.size(); i++) {

            member = member.builder()
                    .seq(entities.get(i).getSeq())
                    .id(entities.get(i).getId())
                    .pw(entities.get(i).getPw())
                    .name(entities.get(i).getName())
                    .email(entities.get(i).getEmail())
                    .phone(entities.get(i).getPhone())
                    .address(entities.get(i).getAddress())
                    .build();

            members.add(member);
        }

        return members;

    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {

    }
}

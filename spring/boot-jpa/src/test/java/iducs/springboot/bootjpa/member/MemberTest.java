package iducs.springboot.bootjpa.member;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import iducs.springboot.bootjpa.service.MemberService;
import iducs.springboot.bootjpa.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

//@Transactional
public class MemberTest {

    @Autowired MemberRepository memberRepository;

    @Autowired MemberService memberService;

    @Test
    void memberCreateTest() {
        Member member = Member.builder()
                .id("1")
                .pw("1")
                .name("1")
                .email("1")
                .phone("1")
                .address("1")
                .build();
        memberService.create(member);

        assertThat(member).isSameAs(memberRepository.findById(1L));
    }
}

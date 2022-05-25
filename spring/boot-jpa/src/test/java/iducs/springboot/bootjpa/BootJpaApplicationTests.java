package iducs.springboot.bootjpa;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import iducs.springboot.bootjpa.service.MemberService;
import iducs.springboot.bootjpa.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class BootJpaApplicationTests {

    // POJO (Plain Old Java Object) : 가장 기본적인 자바 객체 형태
    // Beans 규약을 준수, 생성자가 복잡하지 않음
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberServiceImpl memberService;

//    @Test // Unit Test : JUnit 도구 활용 -> 통합 테스트(Integration Test) 사이즈가 클 수록 unit test 하기.
//    void contextLoads() { // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            MemberEntity member =
//                    MemberEntity.builder().id("id" + i).pw("pw" + i).name("name" + i).build();
//            memberRepository.save(member); // insert 문
//
////            member = MemberEntity.builder().pw("pw" + i).build();
////            member = MemberEntity.builder().name("name" + i).build();
////            member = MemberEntity.builder().pw("email" + i).build();
////            member = MemberEntity.builder().pw("phone" + i).build();
////            member = MemberEntity.builder().pw("pw" + i).build();
////            member = MemberEntity.builder().pw("pw" + i).build();
////            memoRepository.save(memo);
//        });
//    }

    @Test
    void memberCreateLotsTest() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            MemberEntity entity = MemberEntity.builder()
                    .id("id " + i)
                    .pw("pw " + i)
                    .name("name " + i)
                    .email("email " + i + "@email")
                    .phone("phone" + i)
                    .address("address " + i)
                    .build();

            memberService.create(memberService.entityToDto(entity));
        });
    }
}

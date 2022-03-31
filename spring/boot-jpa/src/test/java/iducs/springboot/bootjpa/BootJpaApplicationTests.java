package iducs.springboot.bootjpa;


import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;


@SpringBootTest
class BootJpaApplicationTests {

    // POJO (Plain Old Java Object)
    @Autowired
    MemberRepository memberRepository;

    @Test
    void contextLoads() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemberEntity member =
                    MemberEntity.builder().id("id" + i).pw("pw" + i).name("name" + i).build();
            memberRepository.save(member); // insert 문

//            member = MemberEntity.builder().pw("pw" + i).build();
//            member = MemberEntity.builder().name("name" + i).build();
//            member = MemberEntity.builder().pw("email" + i).build();
//            member = MemberEntity.builder().pw("phone" + i).build();
//            member = MemberEntity.builder().pw("pw" + i).build();
//            member = MemberEntity.builder().pw("pw" + i).build();
//            memoRepository.save(memo);
        });
    }
}

package iducs.springboot.bootjpa.reply;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.ReplyEntity;
import iducs.springboot.bootjpa.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    void replyRepoInitTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Long seqLong = Long.valueOf(new Random().nextInt(10));
            seqLong = (seqLong == 0) ? 1 : seqLong;
            BoardEntity boardEntity = BoardEntity.builder()
                    .bor_id(seqLong)
                    .build();
            ReplyEntity entity = ReplyEntity.builder()
                    .text("text " + i)
                    .replier("who" + i)
                    .boardEntity(boardEntity) // member entity의 seq 값 중 존재하는 값이여야 한다.
                    .build();

            replyRepository.save(entity);
        });
    }

    @Test
    void replyDelete(){

    }

}

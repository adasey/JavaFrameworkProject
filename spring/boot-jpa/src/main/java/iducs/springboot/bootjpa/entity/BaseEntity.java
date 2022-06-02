package iducs.springboot.bootjpa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 부모 클래스는 상속된 것처럼 아래의 함수들이 각자 객체에서 사용 가능하다.
@EntityListeners(value = {AuditingEntityListener.class}) // 감시자
@Getter
public abstract class BaseEntity {
    // 매핑되어 넘어감.
    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}

package iducs.springboot.bootjpa.entity;

import lombok.*;

import javax.persistence.*;

// spring data annotation - 주석
@Entity // Spring Data JPA의 엔티티
@Table(name="tbl_member")
// Lombok 관련 Annotations
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor // 디폴트 생성자 (아무런 매개변수가 없음)
@AllArgsConstructor // 모든 매개변수 갖는 생성자
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto db에 따라 저절로 선택하겠다는 뜻. identity는 mysql, mariadb 등 auto increment 되어 시퀀스가 저절로 올라감.
    // oracle db의 경우 문제가 될 수도 있다.
    private Long seq; // long 기본형의 wrapper 클래스 유용한 사용을 위한 wrapper

    @Column(length = 30, nullable = false)
    private String id;

    @Column(length = 30, nullable = false)
    private String pw;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 30, nullable = true)
    private String phone;

    @Column(length = 100)
    private String address;
}

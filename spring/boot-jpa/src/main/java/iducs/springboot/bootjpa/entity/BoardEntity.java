package iducs.springboot.bootjpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bor_id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩, join에 대해 나중에 필요시에만 사용한다.
    private MemberEntity writer; // 1대 다 관계 연결
}

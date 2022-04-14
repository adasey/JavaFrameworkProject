package iducs.springboot.bootjpa.domain;

import lombok.Builder;
import lombok.Data;

@Data // @getter @setter @equalsandhash @requiredargconstructor 통합팩
@Builder // builder().build() 로 객체 생성. 초기화 할 수 있도록 하는 설정
public class Memo { // DTO : controller -> service
    private Long mno;
    private String memoText;
}

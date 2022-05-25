package iducs.springboot.bootjpa.domain;

import lombok.*; // 파이썬처럼 @로 선언하여 해당 클래스나 함수를 @에 해당하는 기능을 사용할 수 있도록 만듬.

@Data // @Getter, @Setter, @Equalsandhash, @Requiredargsconstructor
@Builder
// client -> controller -> service 에서 교환됨
// client <- (view | data - api) controller <- service
public class Member { // 레코드 ResultSet 객체를 활용
    private Long seq;
    private String id;
    private String name;
    private String pw;
    private String email;
    private String phone;
    private String address;
    // 숫자, 불리언, Date : 날짜, 시간 관련
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MemberDTO memberDTO = (MemberDTO) o;
//        return seq == memberDTO.seq && Objects.equals(id, memberDTO.id) && Objects.equals(name, memberDTO.name) && Objects.equals(pw, memberDTO.pw);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(seq, id, name, pw, email, phone, address);
//    }
//
//    @Override
//    public String toString() {
//        return "MemberDTO{" +
//                "seq=" + seq +
//                ", id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", pw='" + pw + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }
//
//    public MemberDTO() {
//        // default 생성자 __init__
//    }
//
//    public MemberDTO(long seq, String id, String name, String pw, String email, String phone, String address) {
//        this.seq = seq;
//        this.id = id;
//        this.name = name;
//        this.pw = pw;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//    }
}

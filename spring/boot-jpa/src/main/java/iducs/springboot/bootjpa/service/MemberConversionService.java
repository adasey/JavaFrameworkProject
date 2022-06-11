package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.entity.MemberEntity;

public interface MemberConversionService {
    Member entityToDto(MemberEntity entity);
    MemberEntity dtoToEntity(Member member);
}

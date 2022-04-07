package iducs.springboot.bootjpa.repository;

import iducs.springboot.bootjpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}

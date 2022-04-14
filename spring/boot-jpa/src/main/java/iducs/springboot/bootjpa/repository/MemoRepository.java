package iducs.springboot.bootjpa.repository;

import iducs.springboot.bootjpa.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
    // Spring Data JPA 를 활용해 데이터 액세스를 담당하는 객체를 생성하는데 사용
}

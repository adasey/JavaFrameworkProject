package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Memo;
import iducs.springboot.bootjpa.entity.MemoEntity;
import iducs.springboot.bootjpa.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {
    final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public void create(Memo memo) {
        MemoEntity entity = dtoToEntity(memo);

        memoRepository.save(entity);
    }

    @Override
    public Memo readById(Long mno) {
        MemoEntity entity = memoRepository.findById(mno).get();

        Memo memo = entityToDto(entity);

        return memo;
    }

    @Override
    public List<Memo> readAll() {
        List<Memo> memos = new ArrayList<>();
        List<MemoEntity> entities = memoRepository.findAll();
        // JpaRepository 구현체의 메소드 findAll(), List<T>

        for (MemoEntity entity : entities) {
            Memo memo = entityToDto(entity);
            memos.add(memo);
        }

        return memos;
    }

    @Override
    public void update(Memo memo) {
        MemoEntity entity = dtoToEntity(memo);
        memoRepository.save(entity);
    }

    @Override
    public void delete(Memo memo) {
        MemoEntity entity = dtoToEntity(memo);
        memoRepository.deleteById(entity.getMno());
    }

    public Memo entityToDto(MemoEntity entity) {
        Memo memo = Memo.builder()
                .mno(entity.getMno())
                .memoText(entity.getMemoText())
                .build();

        return memo;
    }

    public MemoEntity dtoToEntity(Memo memo) {
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();

        return entity;
    }
}

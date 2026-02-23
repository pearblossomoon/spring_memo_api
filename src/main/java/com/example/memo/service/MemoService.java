package com.example.memo.service;

import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo save(Memo memo) {
        return memoRepository.save(memo);
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    public Memo findById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 메모가 없습니다."));
    }

    public Memo update(Long id, Memo updatedMemo) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 메모가 없습니다."));

        memo.setTitle(updatedMemo.getTitle());
        memo.setContent(updatedMemo.getContent());

        return memoRepository.save(memo);
    }

    public void delete(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 메모가 없습니다."));
        memoRepository.delete(memo);
    }
}
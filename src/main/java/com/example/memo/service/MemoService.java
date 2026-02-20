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
}

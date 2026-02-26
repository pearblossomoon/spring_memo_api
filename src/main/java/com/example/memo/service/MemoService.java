package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    public MemoResponseDto create(MemoRequestDto requestDto) {

        Memo memo = new Memo();
        memo.setTitle(requestDto.getTitle());
        memo.setContent(requestDto.getContent());

        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo);
    }
}
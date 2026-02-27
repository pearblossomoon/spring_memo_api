package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.exception.MemoNotFoundException;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<MemoResponseDto> findAll() {
        return memoRepository.findAll()
                .stream()
                .map(MemoResponseDto::new)
                .collect(Collectors.toList());
    }

    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException("해당 메모가 없습니다."));

        return new MemoResponseDto(memo);
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto) {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException("해당 메모가 없습니다."));

        memo.setTitle(requestDto.getTitle());
        memo.setContent(requestDto.getContent());

        // save() 필요 없음 (Dirty Checking)
        return new MemoResponseDto(memo);
    }

    public void delete(Long id) {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException("해당 메모가 없습니다."));

        memoRepository.delete(memo);
    }
}
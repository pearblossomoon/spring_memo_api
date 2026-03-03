package com.example.memo.service;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.exception.MemoNotFoundException;
import com.example.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // ✅ 생성
    public MemoResponseDto create(MemoRequestDto requestDto) {

        Memo memo = new Memo();
        memo.setTitle(requestDto.getTitle());
        memo.setContent(requestDto.getContent());

        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo);
    }

    // ✅ 페이징 전체 조회
    public Page<MemoResponseDto> findAll(Pageable pageable) {

        return memoRepository.findAll(pageable)
                .map(MemoResponseDto::new);
    }

    // ✅ 단건 조회
    public MemoResponseDto findById(Long id) {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException("해당 메모가 없습니다."));

        return new MemoResponseDto(memo);
    }

    // ✅ 수정 (Dirty Checking)
    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto) {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException("해당 메모가 없습니다."));

        memo.setTitle(requestDto.getTitle());
        memo.setContent(requestDto.getContent());

        return new MemoResponseDto(memo);
    }

    // ✅ 삭제
    public void delete(Long id) {

        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException("해당 메모가 없습니다."));

        memoRepository.delete(memo);
    }
}
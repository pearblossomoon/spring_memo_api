package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public MemoResponseDto create(@RequestBody MemoRequestDto requestDto) {
        return memoService.create(requestDto);
    }

    @GetMapping
    public List<MemoResponseDto> findAll() {
        return memoService.findAll();
    }

    @GetMapping("/{id}")
    public MemoResponseDto findById(@PathVariable Long id) {
        return memoService.findById(id);
    }

    @PutMapping("/{id}")
    public MemoResponseDto update(@PathVariable Long id,
                                  @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memoService.delete(id);
    }
}
package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import com.example.memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public MemoResponseDto create(@RequestBody MemoRequestDto requestDto) {
        return memoService.create(requestDto);
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    public List<Memo> getAll() {
        return memoService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    public Memo getMemo(@PathVariable Long id) {
        return memoService.findById(id);
    }

    @PutMapping("/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody Memo memo) {
        return memoService.update(id, memo);
    }
}
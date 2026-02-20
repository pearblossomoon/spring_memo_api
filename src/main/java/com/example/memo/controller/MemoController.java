package com.example.memo.controller;

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
    public Memo createMemo(@RequestBody Memo memo) {
        return memoService.save(memo);
    }

    @GetMapping
    public List<Memo> getAll() {
        return memoService.findAll();
    }
}
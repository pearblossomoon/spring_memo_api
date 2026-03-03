package com.example.memo.controller;

import com.example.memo.dto.ApiResponse;
import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // ✅ 생성
    @PostMapping
    public ApiResponse<MemoResponseDto> create(
            @Valid @RequestBody MemoRequestDto requestDto) {

        return ApiResponse.success(memoService.create(requestDto));
    }

    // ✅ 페이징 조회
    @GetMapping
    public ApiResponse<Page<MemoResponseDto>> findAll(
            @PageableDefault(size = 5) Pageable pageable) {

        return ApiResponse.success(memoService.findAll(pageable));
    }

    // ✅ 단건 조회
    @GetMapping("/{id}")
    public ApiResponse<MemoResponseDto> findById(@PathVariable Long id) {

        return ApiResponse.success(memoService.findById(id));
    }

    // ✅ 수정
    @PutMapping("/{id}")
    public ApiResponse<MemoResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MemoRequestDto requestDto) {

        return ApiResponse.success(memoService.update(id, requestDto));
    }

    // ✅ 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        memoService.delete(id);
        return ApiResponse.success("삭제 완료");
    }
}
package com.example.memo.exception;

public class MemoNotFoundException extends RuntimeException {

    public MemoNotFoundException(String message) {
        super(message);
    }
}
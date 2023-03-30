package com.example.login_demo;

import java.util.Optional;

public class Result<T> {
    private Optional<T> data;
    private String message;
    private boolean success;

    private Result(T data, String message, boolean success) {
        this.data = Optional.ofNullable(data);
        this.message = message;
        this.success = success;
    }

    public T getData() {
        return data.orElse(null);
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, "操作成功", true);
    }

    public static <T> Result<T> success() {
        return new Result<>(null, "操作成功", true);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(data, message, true);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(null, message, false);
    }
    public static <T> Result<T> fail( ) {
        return new Result<>(null, "默认错误返回信息", false);
    }
}

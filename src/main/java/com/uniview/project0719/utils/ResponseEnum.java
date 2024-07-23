package com.uniview.project0719.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {
    SUCCESS(200,"请求成功"),
    FAIL(400,"请求参数错误"),
    NO_AUTHORITY(401,"未授权"),
    NOT_FOUND(404,"资源未找到"),
    INTERVAL_ERROR(500,"服务器内部错误");
    private final Integer code;
    private final String msg;
}

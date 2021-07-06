package com.admin.zhonghe.enums;

public enum ExceptionEnum {
    // 数据操作错误定义
    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
    PERMISSION_DENIED(403, "权限不足!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!")
    ;

    /** 错误码 */
    private int code;

    /** 错误描述 */
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
         this.code=code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
         this.message=message;
    }

}

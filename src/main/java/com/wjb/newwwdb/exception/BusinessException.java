package com.wjb.newwwdb.exception;

/**
 * BussinessException业务逻辑异常
 */
public class BusinessException extends RuntimeException{
    private String code;
    private String msg;
    public BusinessException(String code , String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
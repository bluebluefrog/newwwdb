package com.wjb.newwwdb.emuns;


public enum ResponseEnum {

    ERROR(-1,"服务端错误"),

    SUCCESS(0,"成功"),

    PASSWORD_ERROR(1,"密码错误"),

    USERNAME_EXIST(2,"用户名已存在"),

    WECHAT_EXIST(4,"微信号已存在"),

    PARAM_ERROR(3,"参数错误"),

    USERNAME_OR_PASSWORD_ERROR(3,"参数错误"),

    NEED_LOGIN(10,"用户未登录"),

    NEED_ADMIN(11,"用户非管理员"),

    NEED_RIGHT_NAME(12,"用户只能操作和自己相同用户名的资料"),

   PERSON_DATA_EXIST(13,"用户资料已存在"),
                    ;
    Integer code;

    String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

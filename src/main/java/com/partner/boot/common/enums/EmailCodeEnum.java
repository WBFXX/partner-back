package com.partner.boot.common.enums;


import lombok.Getter;

@Getter
public enum EmailCodeEnum {
    REGISTER("REGISTER","register:"),
    RESET_PASSWORD("RESETPASSWORD","resetPassword:"),
    LOGIN("LOGIN","login"),
    CHANGEPASSWORD("CHANGEPASSWORD","changePassword:"),
    UNKNOW("","")
    ;


    private final String type;

    private final String value;

    EmailCodeEnum(String type, String value) {
        this.type = type;
        this.value = value;
    }
    //获取前缀
    public static String getValue(String type){
        EmailCodeEnum[] values = values();
        for (EmailCodeEnum codeEnum : values) {
            if (type.equals(codeEnum.type)) {
                return codeEnum.value;
            }
        }
        return "";
    }
    //获取类型
    public static EmailCodeEnum getEnum(String type){
        EmailCodeEnum[] values = values();
        for (EmailCodeEnum codeEnum : values) {
            if (type.equals(codeEnum.type)) {
                return codeEnum;
            }
        }
        return UNKNOW;
    }
}

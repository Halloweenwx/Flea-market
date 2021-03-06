package com.tet.fleamarket.util.status;

import com.tet.fleamarket.util.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * @author Hou Weiying
 * @date 2019-12-01 20:39
 */
@AllArgsConstructor
@NoArgsConstructor
public enum UserStatus implements Status {
    /**
     * 用户登录/注册状态
     */
    SUCCESS(200,"请求成功"),
    UPDATE_SUCCESS(200,"资料修改成功"),
    NO_UPDATE(200,"无修改"),
    LOGIN_SUCCESS(200, "登陆成功"),
    REGISTER_SUCCESS(200, "注册成功"),
    WRONG_PASSWORD(400, "密码错误"),
    USER_NOT_FOUND(400, "无法找到用户"),
    ALREADY_EXISTS(400,"当前用户名已存在"),
    BAD_REQUEST(400, "无效请求");

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg + "(" + code + ")";
    }
}

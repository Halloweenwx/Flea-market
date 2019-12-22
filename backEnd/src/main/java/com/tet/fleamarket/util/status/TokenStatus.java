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
public enum TokenStatus implements Status {
    /**
     * 用户登录/注册状态
     */
    TOKEN_NOT_FOUND(404,"无token，请重新登录"),
    TOKEN_INVALID(400,"token错误"),
    USER_NOT_FOUND(400,"用户不存在，请重新登录"),
    TOKEN_OVERDUE(400, "token过期");

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

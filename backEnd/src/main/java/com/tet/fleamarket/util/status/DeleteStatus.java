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
public enum DeleteStatus implements Status {
    /**
     * 删除
     */
    DELETE_SUCCESS(200,"删除成功"),
    RESOURCE_NOT_EXSITS(404,"资源不存在"),
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

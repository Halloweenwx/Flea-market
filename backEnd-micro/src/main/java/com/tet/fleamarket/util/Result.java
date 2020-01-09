package com.tet.fleamarket.util;

import lombok.*;

/**
 * @author Hou Weiying
 * @date 2019-12-01 20:30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    public static int SUCCESS_CODE = 200;
    public static int FAIL_CODE = 400;

    Integer code;
    String msg;
    Object data;

    public Result(Status status,Object data){
        code = status.getCode();
        msg = status.getMsg();
        this.data = data;
    }
    public Result(Status status){
        code = status.getCode();
        msg = status.getMsg();
    }
    public static Result success() {
        return new Result(SUCCESS_CODE,"请求成功",null);
    }
    public static Result success(Object data) {
        return new Result(SUCCESS_CODE,"请求成功",data);
    }
    public static Result fail(String message) {
        return new Result(FAIL_CODE,message,null);
    }


}

package com.tet.fleamarket.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Hou Weiying
 * @date 2019-12-01 20:46
 */
public interface Status {
    public Integer getCode();

    public void setCode(Integer code);

    public String getMsg();

    public void setMsg(String msg);

}

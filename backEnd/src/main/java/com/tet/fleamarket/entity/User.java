package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String type;
    private String nickname;
    private int phoneNo;
    private int level;
    private String city;
    private Date registerTime;
    private Date modifiedTime;

}

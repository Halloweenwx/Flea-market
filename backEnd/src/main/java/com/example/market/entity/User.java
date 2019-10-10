package com.example.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

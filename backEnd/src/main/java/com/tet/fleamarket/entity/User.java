package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    private String nickname;
    private String password;
    private String salt;
    @Column(name = "client_level")
    private String clientLevel;
    @Column(name = "real_name")
    private String realName;
    private String type;
    private int phone;
    private String city;
    @Column(name = "register_time")
    private Date registerTime;
    @Column(name = "modified_time")
    private Date modifiedTime;

}

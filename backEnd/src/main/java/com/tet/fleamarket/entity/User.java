package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32, unique = true)
    private String uid;
    @Column(length = 32)
    private String username;
    @Column(length = 32)
    private String password;
    @Column(length = 32)
    private String salt;
    private String realName;
    @Column(length = 11)
    private String phone;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp registerTime;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp modifiedTime;

}

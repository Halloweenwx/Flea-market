package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {
    @Id
    @Column(length = 16, unique = true)
    private String code;
    private String cnName;

}

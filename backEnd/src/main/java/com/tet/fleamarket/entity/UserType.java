package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserType{
    @Id
    @Column(length = 32, unique = true)
    private String enType;
    private String cnType;

}

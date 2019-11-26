package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Administrator extends User{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="privilege_level", referencedColumnName="enLevel")})
    private PrivilegeLevel privilegeLevel;
}

package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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
public class Customer extends User{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="member_level", referencedColumnName="enLevel")})
    private MemberLevel memberLevel;
    @ManyToOne
    @JoinColumns({@JoinColumn(name="city", referencedColumnName="code")})
    private City city;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "uid"))
    private Set<String> address;

}

package com.tet.fleamarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends User {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name = "member_level", referencedColumnName = "enLevel")})
    @ColumnDefault(value = "ordinary")
    private MemberLevel memberLevel;
    /**
     * 懒加载错误
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "uid"))
    private Set<String> address;

    public Customer(User user) {
        super(user.getUid(), user.getUsername(), user.getPassword(), user.getSalt(), user.getRealName(), user.getPhone(), user.getRegisterTime(), user.getModifiedTime(), user.getCity(), user.getIsCustomer());
    }
}

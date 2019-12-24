package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Hou Weiying
 * @date 2019-12-23 18:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, unique = true)
    private String cid;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "cid"))
    private Set<IdleItem> idleItems;
    @OneToOne
    @JoinColumns({@JoinColumn(name = "customer", referencedColumnName = "uid")})
    private Customer customer;
}

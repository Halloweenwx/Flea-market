package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Hou Weiying
 * @date 2019-12-24 00:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deal {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, unique = true)
    private String did;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "payer", referencedColumnName = "uid")})
    private Customer payer;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "owner", referencedColumnName = "uid")})
    private Customer owner;
    private double dealPrice;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createTime;
}

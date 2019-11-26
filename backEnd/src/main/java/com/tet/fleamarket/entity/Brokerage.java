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
 * @date 2019-11-26 14:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brokerage {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32, unique = true)
    private String brid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="iid", referencedColumnName="iid")})
    private Item item;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="req_uid", referencedColumnName="uid")})
    private User requester;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="res_uid", referencedColumnName="uid")})
    private User responser;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp finishTime;
    private Double salePrice;
    private Double reqRate;
    private Double resRate;
    private Double total;
}

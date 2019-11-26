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
public class Bidding {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32, unique = true)
    private String bid;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="bidder", referencedColumnName="uid")})
    private User bidder;
    private Double biddingPrice;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createTime;
}

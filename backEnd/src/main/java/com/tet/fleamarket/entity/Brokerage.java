package com.tet.fleamarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brokerage {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, unique = true)
    private String brid;
    @OneToOne
    @JoinColumns({@JoinColumn(name="iid", referencedColumnName="iid")})
    private Item item;
    @ManyToOne
    @JoinColumns({@JoinColumn(name="req_uid", referencedColumnName="uid")})
    private Customer requester;
    @ManyToOne
    @JoinColumns({@JoinColumn(name="res_uid", referencedColumnName="uid")})
    private Customer responser;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp finishTime;
    private Double salePrice;
    private Double reqRate;
    private Double resRate;
    private Double total;
}

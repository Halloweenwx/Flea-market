package com.tet.fleamarket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, unique = true)
    private String iid;
    //不可设置为CascadeType.ALL，会导致save失败（persist)和误删除（remove） https://www.jianshu.com/p/e8caafce5445
    @ManyToOne
    // 将user对应值返回此处
    @JoinColumns({@JoinColumn(name = "belong", referencedColumnName = "uid")})
    private Customer belong;
    @OneToOne(cascade={CascadeType.MERGE})
    @JoinColumns({@JoinColumn(name = "category", referencedColumnName = "enCategory")})
    private Category category;
    private String name;
    private String description;
    @OneToOne
    @JoinColumns({@JoinColumn(name = "status", referencedColumnName = "enStatus")})
    private ItemStatus itemStatus;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "iid"))
    private Set<Picture> pictures;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createTime;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp modifiedTime;
    private double dealPrice;
}

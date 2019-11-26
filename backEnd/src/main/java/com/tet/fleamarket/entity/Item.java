package com.tet.fleamarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32, unique = true)
    private String iid;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="belong", referencedColumnName="uid")})
    private User belong;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="category", referencedColumnName="enCategory")})
    private Category category;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name="status", referencedColumnName="enStatus")})
    private ItemStatus itemStatus;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "iid"))
    private Set<String> pictureUrl;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createTime;
    @Column(columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp modifiedTime;
}

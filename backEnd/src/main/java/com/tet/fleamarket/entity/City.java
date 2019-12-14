package com.tet.fleamarket.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @Id
    @Column(length = 16, unique = true)
    @JsonProperty(value = "value")
    private String code;
    @JsonProperty(value = "label")
    private String cnName;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<City> children;

}

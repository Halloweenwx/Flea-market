package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

/**
 * interface **Dao extends JpaRepository<**, id>
 */
@Repository
public interface CityDao extends JpaRepository<City, String> {
    @Override
    ArrayList<City> findAll();
}

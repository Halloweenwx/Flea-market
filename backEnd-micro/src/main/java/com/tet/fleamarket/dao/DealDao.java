package com.tet.fleamarket.dao;

import com.tet.fleamarket.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hou Weiying
 * @date 2019-12-24 00:17
 */
public interface DealDao extends JpaRepository<Deal, String> {

}

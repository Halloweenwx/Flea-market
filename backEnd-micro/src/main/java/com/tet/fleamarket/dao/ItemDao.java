package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

/**
 * interface **Dao extends JpaRepository<**, id>
 */
@Repository
public interface ItemDao extends JpaRepository<Item, String> {
}

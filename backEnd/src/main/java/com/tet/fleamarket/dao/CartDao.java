package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.Cart;
import com.tet.fleamarket.entity.City;
import com.tet.fleamarket.entity.IdleItem;
import com.tet.fleamarket.entity.Item;
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
public interface CartDao extends JpaRepository<Cart, String> {
    Cart findCartByCustomer_Uid(String uid);

}

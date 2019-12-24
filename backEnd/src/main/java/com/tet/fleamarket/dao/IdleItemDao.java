package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.Customer;
import com.tet.fleamarket.entity.IdleItem;
import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hou Weiying
 * @date 2019-10-22 19:14
 */

/**
 * interface **Dao extends JpaRepository<**, id>
 */
@Repository
public interface IdleItemDao extends JpaRepository<IdleItem, String> {
    IdleItem findByName(String name);

    IdleItem findByIid(String iid);

    Page<IdleItem> findByBelong_Uid(String uid, Pageable pageable);

    Page<IdleItem> findByBelong_UidAndNameContains(String uid, String name, Pageable pageable);

    List<IdleItem> findTop5ByItemStatus_EnStatusOrderByCreateTimeDesc(String status);
}

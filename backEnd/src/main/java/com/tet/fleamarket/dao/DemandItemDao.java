package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.DemandItem;
import com.tet.fleamarket.entity.IdleItem;
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
public interface DemandItemDao extends JpaRepository<DemandItem, String> {
    DemandItem findByName(String name);

    DemandItem findByIid(String iid);

    Page<DemandItem> findByBelong_Uid(String uid, Pageable pageable);

    Page<DemandItem> findByBelong_UidAndNameContains(String uid, String name, Pageable pageable);

    List<DemandItem> findTop5ByItemStatus_EnStatusOrderByCreateTimeDesc(String status);
}

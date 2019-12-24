package com.tet.fleamarket.dao;


import com.tet.fleamarket.entity.DemandItem;
import com.tet.fleamarket.entity.IdleItem;
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
public interface DemandItemDao extends JpaRepository<DemandItem, String> {
    DemandItem findByName(String name);

    DemandItem findByIid(String iid);

    //pageable要放在后面，不然会无法匹配
    Page<DemandItem> findByBelong_Uid(String uid, Pageable pageable);
}

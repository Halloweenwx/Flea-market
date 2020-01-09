package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.DealDao;
import com.tet.fleamarket.dao.IdleItemDao;
import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hou Weiying
 * @date 2019-12-24 12:03
 */
@Service
public class DealService {
    @Autowired
    DealDao dealDao;

    @Autowired
    IdleItemDao idleItemDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Transactional
    public boolean buyThis(IdleItem item, Customer customer) {
        Deal deal = new Deal();
        boolean state = false;
        try {
            IdleItem idleItemInBase = idleItemDao.findByIid(item.getIid());

            deal.setPayer(customer);
            deal.setOwner(item.getBelong());
            deal.setDealPrice(item.getDealPrice());

            //易主
            idleItemInBase.setBelong(customer);
            //下架
            idleItemInBase.setItemStatus(new ItemStatus("off"));
            idleItemInBase.setDealPrice(item.getDealPrice());
            state = true;
        } catch (Exception e) {
            logger.error("交易失败");
        }

        dealDao.save(deal);
        idleItemDao.save(item);

        return state;
    }

    public Cart removeItem(Cart cart,String iid){
        Set<IdleItem> items = cart.getIdleItems();
        Set<IdleItem> itemsRemoved = new HashSet<>();
        for (IdleItem idleItem:items){
            if(!idleItem.getIid().equals(iid)){
                itemsRemoved.add(idleItem);
            }
        }
        cart.setIdleItems(itemsRemoved);
        return cart;
    }
}

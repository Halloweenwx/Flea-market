package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.DealDao;
import com.tet.fleamarket.dao.IdleItemDao;
import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean buyThis(IdleItem item, Customer customer) {
        Deal deal = new Deal();
        boolean state = false;
        try {
            deal.setPayer(customer);
            deal.setOwner(item.getBelong());
            deal.setDealPrice(item.getDealPrice());

            //易主
            item.setBelong(customer);
            item.setIid(null);
            //下架
            item.setItemStatus(new ItemStatus("off"));
            state = true;
        } catch (Exception e) {
            logger.error("交易失败");
        }

        dealDao.save(deal);
        idleItemDao.save(item);

        return state;
    }
}

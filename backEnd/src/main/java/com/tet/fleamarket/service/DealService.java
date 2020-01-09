package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.*;
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

    @Autowired
    BrokerageDao brokerageDao;

    @Autowired
    CustomerDao customerDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean buyThis(IdleItem item, Customer customer) {
        Deal deal = new Deal();
        boolean state = false;
        try {
            IdleItem idleItemInBase = idleItemDao.findByIid(item.getIid());

            deal.setPayer(customer);
            deal.setOwner(item.getBelong());
            deal.setDealPrice(item.getDealPrice());

            Brokerage brokerage = new Brokerage();
            brokerage.setItem(idleItemInBase);
            // 提出者，闲置物品挂卖者，求购需求提出者
            brokerage.setRequester(customerDao.findByUid(item.getBelong().getUid()));
            // 响应者，闲置物品买者，求购需求卖者
            brokerage.setResponser(customerDao.findByUid(customer.getUid()));

            brokerage.setReqRate(0.01);
            brokerage.setResRate(0.02);

            brokerage.setSalePrice(item.getDealPrice());

            brokerage.setTotal(item.getDealPrice()*brokerage.getReqRate()+item.getDealPrice()*brokerage.getResRate());
            logger.info(brokerage.toString());
            brokerageDao.save(brokerage);
            //易主
            idleItemInBase.setBelong(customer);
            //下架
            idleItemInBase.setItemStatus(new ItemStatus("off"));
            idleItemInBase.setDealPrice(item.getDealPrice());
            state = true;
            dealDao.save(deal);
            idleItemDao.save(idleItemInBase);
        } catch (Exception e) {
            logger.error("交易失败");
        }



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

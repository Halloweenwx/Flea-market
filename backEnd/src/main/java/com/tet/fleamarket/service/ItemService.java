package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.DemandItemDao;
import com.tet.fleamarket.dao.IdleItemDao;
import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.dao.PictureOss;
import com.tet.fleamarket.entity.DemandItem;
import com.tet.fleamarket.entity.IdleItem;
import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.entity.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Hou Weiying
 * @date 2019-12-01 17:26
 */
@Service
public class ItemService {
    @Autowired
    private DemandItemDao demandItemDao;

    @Autowired
    private IdleItemDao idleItemDao;

    @Autowired
    private PictureOss pictureOss;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * idle
     */

    public Item getIdleItemByIid(String iid) {
        if (!iid.isEmpty()) {
            return idleItemDao.findByIid(iid);
        } else {
            return new IdleItem();
        }
    }

    public Item getIdleItemByName(String name) {
        if (!name.isEmpty()) {
            return idleItemDao.findByName(name);
        } else {
            return new IdleItem();
        }
    }

    public Boolean idleNameExists(String name) {
        if (name.isEmpty()) {
            return false;
        }
        IdleItem itemInBase = idleItemDao.findByName(name);
        return itemIsLegal(itemInBase);
    }


    public Item addIdleItem(IdleItem itemToAdd) {
        return idleItemDao.save(itemToAdd);
    }

    /**
     * demand
     */
    public DemandItem getDemandItemByIid(String iid) {
        if (!iid.isEmpty()) {
            return demandItemDao.findByIid(iid);
        } else {
            return new DemandItem();
        }
    }

    public DemandItem getDemandItemByName(String name) {
        if (!name.isEmpty()) {
            return demandItemDao.findByName(name);
        } else {
            return new DemandItem();
        }
    }

    public Boolean demandNameExists(String name) {
        if (name.isEmpty()) {
            return false;
        }
        DemandItem itemInBase = demandItemDao.findByName(name);
        return itemIsLegal(itemInBase);
    }


    public Item addDemandItem(DemandItem itemToAdd) {
        return demandItemDao.save(itemToAdd);
    }


    /**
     * item
     */
    public Boolean idleIidExists(String iid) {
        Item itemInBase = idleItemDao.findByIid(iid);
        return itemIsLegal(itemInBase);
    }

    public Boolean itemIsLegal(Item item) {
        if (item != null) {
            logger.info(item.getIid());
            return !item.getIid().isEmpty() && !item.getName().isEmpty();
        } else {
            logger.error("该物品不存在");
            return false;
        }
    }

    public void addPicUrl(Item item) throws IOException {
        for (Picture pic:item.getPictures()){
            pic.setUrl(pictureOss.findByPid(pic.getPid()).getUrl());
        }
    }

}

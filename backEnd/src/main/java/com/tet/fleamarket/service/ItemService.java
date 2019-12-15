package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hou Weiying
 * @date 2019-12-01 17:26
 */
@Service
public class ItemService {
    @Autowired
    private ItemDao itemDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public Item getItemByIid(String iid){
        if(!iid.isEmpty()) {
            return itemDao.findByIid(iid);
        }else{
            return new Item();
        }
    }
    public Item getItemByName(String name){
        if(!name.isEmpty()){
            return itemDao.findByName(name);
        }else{
            return new Item();
        }
    }

    public Boolean nameExists(String name) {
        if(name.isEmpty()){
            return false;
        }
        Item itemInBase = itemDao.findByName(name);
        return itemIsLegal(itemInBase);
    }
    public Boolean iidExists(String iid){
        Item itemInBase = itemDao.findByIid(iid);
        return itemIsLegal(itemInBase);
    }

    public Boolean itemIsLegal(Item item) {
        if (item != null) {
            logger.info(item.getIid());
            return !item.getIid().isEmpty() && !item.getName().isEmpty();
        }else{
            logger.error("物品不合法");
            return false;
        }
    }
    public Item addItem(Item itemToAdd){
        return itemDao.save(itemToAdd);
    }
}

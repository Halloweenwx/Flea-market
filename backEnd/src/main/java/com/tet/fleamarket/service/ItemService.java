package com.tet.fleamarket.service;

import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.entity.User;
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

    public Boolean nameExists(String name) {
        Item itemInBase = itemDao.findByName(name);
        return itemIsLegal(itemInBase);
    }
    public Boolean iidExists(String iid){
        Item itemInBase = itemDao.findByIid(iid);
        return itemIsLegal(itemInBase);
    }

    public Boolean itemIsLegal(Item item) {
        return !item.getIid().isEmpty();
    }
}

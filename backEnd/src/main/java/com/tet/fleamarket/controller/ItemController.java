package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.ItemDao;
import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.tet.fleamarket.util.status.FetchStatus.BAD_REQUEST;
import static com.tet.fleamarket.util.status.FetchStatus.FATCH_SUCCESS;

/**
 * @author Hou Weiying
 * @date 2019-12-03 11:25
 */
@RestController
public class ItemController {
    @Autowired
    ItemDao itemDao;

    @GetMapping("/item/{iid}")
    public Result fetch(@PathVariable String iid) {
        Status status;
        Item itemFetched = new Item();
        try {
            itemFetched = itemDao.findByIid(iid);
            status = FATCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, itemFetched);
    }
}

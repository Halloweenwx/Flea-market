package com.tet.fleamarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.tet.fleamarket.entity.Item;
import com.tet.fleamarket.service.ItemService;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.tet.fleamarket.util.status.FetchStatus.*;

/**
 * @author Hou Weiying
 * @date 2019-12-03 11:25
 */
@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/item/{iid}")
    public Result fetch(@PathVariable String iid) {
        Status status;
        Item itemFetched = new Item();
        try {
            itemFetched = itemService.getItemByIid(iid);
            status = FATCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, itemFetched);
    }

    @PostMapping("/item/add")
    public Result add(@RequestBody() Item itemToAdd) {
        Status status;
        JSONObject res = new JSONObject();
        Item itemInBase = itemService.getItemByName(itemToAdd.getName());
        if (itemService.itemIsLegal(itemInBase)) {
            //名称重复
            status = NAME_EXISTS;
            return new Result(status,res);
        } else {
            status = FATCH_SUCCESS;
            itemService.addItem(itemToAdd);
        }
        return new Result(status, res);
    }
}

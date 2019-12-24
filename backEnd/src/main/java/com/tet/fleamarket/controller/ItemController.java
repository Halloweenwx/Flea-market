package com.tet.fleamarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.tet.fleamarket.dao.DemandItemDao;
import com.tet.fleamarket.dao.IdleItemDao;
import com.tet.fleamarket.entity.*;
import com.tet.fleamarket.service.ItemService;
import com.tet.fleamarket.service.TokenService;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import com.tet.fleamarket.util.TokenRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.tet.fleamarket.util.status.AddStatus.ADD_SUCCESS;
import static com.tet.fleamarket.util.status.AddStatus.NAME_EXISTS;
import static com.tet.fleamarket.util.status.FetchStatus.*;
import static org.springframework.data.domain.PageRequest.of;

/**
 * @author Hou Weiying
 * @date 2019-12-03 11:25
 */
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private IdleItemDao idleItemDao;

    @Autowired
    private DemandItemDao demandItemDao;

    @Autowired
    private TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/item/{iid}")
    public Result fetch(@PathVariable String iid) {
        Status status;
        Item itemFetched = new Item();
        try {
            itemFetched = itemService.getIdleItemByIid(iid);
            status = FETCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, itemFetched);
    }

    @TokenRequired
    @PostMapping("/item/idle/add")
    public Result add(HttpServletRequest httpServletRequest, @RequestBody() IdleItem itemToAdd) {
        String token= httpServletRequest.getHeader("Authorization");
        Customer customer = new Customer(tokenService.getUserFromToken(token));
        itemToAdd.setBelong(customer);
        Status status = BAD_REQUEST;
        JSONObject res = new JSONObject();
        try {
            Item itemInBase = itemService.getIdleItemByName(itemToAdd.getName());
            if (itemService.itemIsLegal(itemInBase)) {
                //名称重复
                status = NAME_EXISTS;
                return new Result(status, res);
            } else {
                status = ADD_SUCCESS;
                itemService.addIdleItem(itemToAdd);
            }
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, res);
    }

    @TokenRequired
    @PostMapping("/item/demand/add")
    public Result add(HttpServletRequest httpServletRequest, @RequestBody() DemandItem itemToAdd) {
        String token = httpServletRequest.getHeader("Authorization");
        Customer customer = new Customer(tokenService.getUserFromToken(token));
        itemToAdd.setBelong(customer);
        Status status = BAD_REQUEST;
        JSONObject res = new JSONObject();
        try {
            DemandItem itemInBase = itemService.getDemandItemByName(itemToAdd.getName());
            if (itemService.itemIsLegal(itemInBase)) {
                //名称重复
                status = NAME_EXISTS;
                return new Result(status, res);
            } else {
                status = ADD_SUCCESS;
                itemService.addDemandItem(itemToAdd);
            }
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, res);
    }

    @TokenRequired
    @GetMapping("/home/item/{type}")
    public Result fetchByUid(HttpServletRequest httpServletRequest,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @PathVariable String type
    ) {
        String token = httpServletRequest.getHeader("Authorization");
        Customer customer = new Customer(tokenService.getUserFromToken(token));
        Status status = BAD_REQUEST;
        Page<IdleItem> idleItemPage = null;
        Page<DemandItem> demandItemPage = null;
        try {
            Sort sort = new Sort(Sort.Direction.DESC, "iid");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            if ("idle".equals(type)) {
                idleItemPage = idleItemDao.findAllByBelong_Uid(pageable, customer.getUid());
            } else if ("demand".equals(type)) {
                demandItemPage = demandItemDao.findByBelong_Uid(customer.getUid(), pageable);

            }
            status = FETCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        if ("idle".equals(type)) {
            return new Result(status, idleItemPage);
        } else if ("demand".equals(type)) {
            return new Result(status, demandItemPage);
        }
        return new Result(status);
    }

}

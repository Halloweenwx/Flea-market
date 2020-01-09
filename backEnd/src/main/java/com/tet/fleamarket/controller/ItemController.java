package com.tet.fleamarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.tet.fleamarket.dao.DemandItemDao;
import com.tet.fleamarket.dao.IdleItemDao;
import com.tet.fleamarket.dao.PictureOss;
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

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.tet.fleamarket.util.status.AddStatus.ADD_SUCCESS;
import static com.tet.fleamarket.util.status.AddStatus.NAME_EXISTS;
import static com.tet.fleamarket.util.status.DeleteStatus.DELETE_SUCCESS;
import static com.tet.fleamarket.util.status.FetchStatus.*;

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

    @Autowired
    private PictureOss pictureOss;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param pageNum  第几页
     * @param pageSize 一页几个
     * @param query    查询关键词
     * @param isIdle   是否为idle
     * @param lprice   价格左边界
     * @param rprice   价格右边界
     * @param category 分类
     * @return
     */
    @GetMapping("/fore/search")
    public Result foreSearch(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "query", required = false, defaultValue = "") String query,
                             @RequestParam(name = "isIdle", required = false, defaultValue = "true") Boolean isIdle,
                             @RequestParam(name = "lprice", required = false, defaultValue = "0") Double lprice,
                             @RequestParam(name = "rprice", required = false, defaultValue = "99999") Double rprice,
                             @RequestParam(name = "category", required = false, defaultValue = "") String category
    ) {
        Page<IdleItem> idleItems = null;
        Page<DemandItem> demandItems = null;
        Sort sort = new Sort(Sort.Direction.DESC, "iid");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        if (isIdle) {
//            idleItems = idleItemDao.findByNameContainingAndCategory_EnCategory(query,category,pageable);
            if("".equals(category)) {
                idleItems = idleItemDao.findByNameContainingAndStartPriceBetween(query, lprice, rprice, pageable);
            }else{
                idleItems = idleItemDao.findByNameContainingAndCategory_EnCategoryAndStartPriceBetween(query, category, lprice, rprice, pageable);
            }
            for (IdleItem idleItem : idleItems) {
                try {
                    itemService.addPicUrl(idleItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new Result(FETCH_SUCCESS, idleItems);
        } else {
            demandItems = demandItemDao.findByNameContainingAndCategory_EnCategoryContainingAndLowestPriceGreaterThanEqualAndHighestPriceLessThanEqual(query, category, lprice, rprice, pageable);
            for (DemandItem demandItem : demandItems) {
                try {
                    itemService.addPicUrl(demandItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new Result(FETCH_SUCCESS, demandItems);
        }
    }

    /**
     * 首页请求物品
     *
     * @param type
     * @return
     */
    @GetMapping("/fore/item/{type}")
    public Result foreFetch(@PathVariable String type) {
        Status status = BAD_REQUEST;
        List<IdleItem> idleItems = new ArrayList<>();
        List<DemandItem> demandItems = new ArrayList<>();
        if ("idle".equals(type)) {
            idleItems = idleItemDao.findTop5ByItemStatus_EnStatusOrderByCreateTimeDesc("on");
            for (IdleItem idleItem : idleItems) {
                try {
                    itemService.addPicUrl(idleItem);
                    status = FETCH_SUCCESS;
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        } else if ("demand".equals(type)) {
            demandItems = demandItemDao.findTop5ByItemStatus_EnStatusOrderByCreateTimeDesc("on");
            for (DemandItem demandItem : demandItems) {
                try {
                    itemService.addPicUrl(demandItem);
                    status = FETCH_SUCCESS;
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }

        }
        if ("idle".equals(type)) {
            return new Result(status, idleItems);
        } else if ("demand".equals(type)) {
            return new Result(status, demandItems);
        }
        return new Result(status);
    }


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
    public Result add(HttpServletRequest httpServletRequest, @PathVariable() IdleItem itemToAdd) {
        String token = httpServletRequest.getHeader("Authorization");
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
    @DeleteMapping("/item/{type}/delete/{iid}")
    public Result del(HttpServletRequest httpServletRequest, @PathVariable("type") String type, @PathVariable("iid") String iid) {
        String token = httpServletRequest.getHeader("Authorization");
        Customer customer = new Customer(tokenService.getUserFromToken(token));
        Status status = BAD_REQUEST;
        try {
            if ("idle".equals(type)) {
                IdleItem idleItemInBase = idleItemDao.findByIid(iid);
                idleItemDao.delete(idleItemInBase);
            }else if("demand".equals(type)){
                DemandItem demandItemInBase = demandItemDao.findByIid(iid);
                demandItemDao.delete(demandItemInBase);
            }
            status = DELETE_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }

        return new Result(status);
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
                itemToAdd.setItemStatus(new ItemStatus("on"));
                itemService.addDemandItem(itemToAdd);
            }
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, res);
    }

    @TokenRequired
    @GetMapping("/item/{type}/query")
    public Result queryName(HttpServletRequest httpServletRequest,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "name", required = false, defaultValue = "") String name,
                            @PathVariable String type) {

        String token = httpServletRequest.getHeader("Authorization");
        Customer customer = new Customer(tokenService.getUserFromToken(token));
        Status status = BAD_REQUEST;
        Page<IdleItem> idleItemPage = null;
        Page<DemandItem> demandItemPage = null;

        try {
            Sort sort = new Sort(Sort.Direction.DESC, "iid");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            if ("idle".equals(type)) {
                idleItemPage = idleItemDao.findByBelong_UidAndNameContains(customer.getUid(), name, pageable);
                status = FETCH_SUCCESS;
            } else if ("demand".equals(type)) {
                demandItemPage = demandItemDao.findByBelong_UidAndNameContains(customer.getUid(), name, pageable);
                status = FETCH_SUCCESS;
            }
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

    /**
     * 个人主页请求物品
     *
     * @param httpServletRequest
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
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
                idleItemPage = idleItemDao.findByBelong_Uid(customer.getUid(), pageable);
                for (IdleItem item : idleItemPage.getContent()) {
                    for (Picture pic : item.getPictures()) {
                        pic.setUrl(pictureOss.findByPid(pic.getPid()).getUrl());
                    }
                }
            } else if ("demand".equals(type)) {
                demandItemPage = demandItemDao.findByBelong_Uid(customer.getUid(), pageable);
                for (DemandItem item : demandItemPage.getContent()) {
                    for (Picture pic : item.getPictures()) {
                        pic.setUrl(pictureOss.findByPid(pic.getPid()).getUrl());
                    }
                }
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

package com.tet.fleamarket.controller;

import com.alibaba.fastjson.JSONObject;
import com.tet.fleamarket.dao.CartDao;
import com.tet.fleamarket.entity.*;
import com.tet.fleamarket.service.DealService;
import com.tet.fleamarket.service.TokenService;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import com.tet.fleamarket.util.TokenRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

import static com.tet.fleamarket.util.status.AddStatus.ADD_SUCCESS;
import static com.tet.fleamarket.util.status.DealStatus.DEAL_SUCCESS;
import static com.tet.fleamarket.util.status.FetchStatus.FETCH_SUCCESS;
import static com.tet.fleamarket.util.status.UserStatus.BAD_REQUEST;

/**
 * @author Hou Weiying
 * @date 2019-12-23 19:02
 */
@RestController
public class CartController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CartDao cartDao;

    @Autowired
    private DealService dealService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @TokenRequired
    @GetMapping("/cart")
    public Result fetchCart(HttpServletRequest httpServletRequest) {
        Customer customer = new Customer(tokenService.getUserFromToken(httpServletRequest.getHeader("Authorization")));
        Status status = BAD_REQUEST;
        Cart cart = new Cart();
        try {
            cart = cartDao.findCartByCustomer_Uid(customer.getUid());
            Set<IdleItem> items = cart.getIdleItems();
            status = FETCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, cart);
    }

    @TokenRequired
    @PostMapping("/cart/item/add")
    public Result addThis(HttpServletRequest httpServletRequest, @RequestBody() IdleItem idleItem) {
        Customer customer = new Customer(tokenService.getUserFromToken(httpServletRequest.getHeader("Authorization")));
        Cart cart = new Cart();
        Status status = BAD_REQUEST;
        try {
            cart = cartDao.findCartByCustomer_Uid(customer.getUid());
            Set<IdleItem> items = cart.getIdleItems();
            items.add(idleItem);
            cart.setIdleItems(items);
            cartDao.save(cart);
            status = ADD_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }

        return new Result(status);
    }

    @TokenRequired
    @PostMapping("/cart/item/update")
    public Result update(HttpServletRequest httpServletRequest, @RequestBody() Set<IdleItem> items) {
        Customer customer = new Customer(tokenService.getUserFromToken(httpServletRequest.getHeader("Authorization")));
        Status status = BAD_REQUEST;
        Cart cart = new Cart();
        cart.setCustomer(customer);
        try {
            cartDao.save(cart);
            status = ADD_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status);
    }

    @TokenRequired
    @GetMapping("/cart/num")
    public Result count(HttpServletRequest httpServletRequest) {
        Customer customer = new Customer(tokenService.getUserFromToken(httpServletRequest.getHeader("Authorization")));
        JSONObject data = new JSONObject();
        Status status = BAD_REQUEST;
        Cart cart = new Cart();
        try {
            cart = cartDao.findCartByCustomer_Uid(customer.getUid());
            Set<IdleItem> items = cart.getIdleItems();
            data.put("cartNum", items.size());
            status = FETCH_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
            logger.error(e.getMessage());
        }
        return new Result(status, data);
    }

    @TokenRequired
    @PostMapping("/cart/buy")
    public Result buy(HttpServletRequest httpServletRequest, @RequestBody() Set<IdleItem> items) {
        Customer customer = new Customer(tokenService.getUserFromToken(httpServletRequest.getHeader("Authorization")));
        Status status = BAD_REQUEST;
        Cart cart = new Cart();
        cart = cartDao.findCartByCustomer_Uid(customer.getUid());
        try {
            for (IdleItem item : items) {
                dealService.buyThis(item, customer);
            }
            status = DEAL_SUCCESS;
        } catch (Exception e) {
            status = BAD_REQUEST;
            logger.error(e.getMessage());
        }
        items.clear();
        cart.setIdleItems(items);
        cartDao.save(cart);
        return new Result(status);
    }
}

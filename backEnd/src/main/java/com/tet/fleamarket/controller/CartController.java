package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.CartDao;
import com.tet.fleamarket.entity.*;
import com.tet.fleamarket.service.TokenService;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import com.tet.fleamarket.util.TokenRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

import static com.tet.fleamarket.util.status.AddStatus.ADD_SUCCESS;
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

    @TokenRequired
    @GetMapping("/cart")
    public Result fetchCart(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Customer customer = new Customer(tokenService.getUserFromCookies(cookies));
        Status status = BAD_REQUEST;
        Cart cart = new Cart();
        try {
            cart = cartDao.findCartByCustomer_Uid(customer.getUid());
            Set<IdleItem> items = cart.getIdleItems();
        } catch (Exception e) {
            status = BAD_REQUEST;
        }
        return new Result(status, cart);
    }

    @TokenRequired
    @PostMapping("/cart/item/add")
    public Result addThis(HttpServletRequest httpServletRequest, @RequestBody() IdleItem idleItem) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Customer customer = new Customer(tokenService.getUserFromCookies(cookies));
        Cart cart = new Cart();
        Status status = BAD_REQUEST;
        try {
            cart = cartDao.findCartByCustomer_Uid(customer.getUid());
            Set<IdleItem> items = cart.getIdleItems();
            items.add(idleItem);
            cart.setIdleItems(items);
            cartDao.save(cart);
        } catch (Exception e) {
            status = BAD_REQUEST;
        }

        return new Result(status);
    }

    @TokenRequired
    @PostMapping("/cart/item/update")
    public Result update(HttpServletRequest httpServletRequest, @RequestBody() Set<IdleItem> items) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Customer customer = new Customer(tokenService.getUserFromCookies(cookies));
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
    @PostMapping("/cart/buy")
    public Result buy(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        Customer customer = new Customer(tokenService.getUserFromCookies(cookies));
        Status status = BAD_REQUEST;



        return new Result();
    }
}

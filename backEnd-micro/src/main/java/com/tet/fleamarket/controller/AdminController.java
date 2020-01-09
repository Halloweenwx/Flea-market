package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.UserDao;
import com.tet.fleamarket.entity.User;
import com.tet.fleamarket.util.Result;
import com.tet.fleamarket.util.Status;
import com.tet.fleamarket.util.TokenRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.tet.fleamarket.util.status.FetchStatus.BAD_REQUEST;
import static com.tet.fleamarket.util.status.FetchStatus.FETCH_SUCCESS;


/**
 * @author Hou Weiying
 * @date 2019-12-24 01:31
 */
@RestController
public class AdminController {
    @Autowired
    UserDao userDao;

    @TokenRequired
    @GetMapping("/admin/user")
    public Result fetchUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        Status status = BAD_REQUEST;
        Sort sort = new Sort(Sort.Direction.DESC, "uid");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<User> users = userDao.findAll(pageable);
        status = FETCH_SUCCESS;
        return new Result(status, users);
    }

    @TokenRequired
    @GetMapping("/stat")
    public Result statistic(){


        return new Result();
    }

}

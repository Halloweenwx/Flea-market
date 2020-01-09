package com.tet.fleamarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tet.fleamarket.dao.CustomerDao;
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

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/stat")
    public Result statistic(){
        Status status = FETCH_SUCCESS;
        JSONObject res = new JSONObject();
        JSONObject xAxis = new JSONObject();
        JSONObject yAxis = new JSONObject();
        JSONObject tooltip = new JSONObject();
        JSONArray series = new JSONArray();
        JSONObject seriesObj = new JSONObject();
        JSONObject areaStyle = new JSONObject();
        List<String> Xdata = new ArrayList<>();
        List<Double> sdata = new ArrayList<>();
        Xdata.add("Mon");
        Xdata.add("Tue");
        Xdata.add("Wed");
        Xdata.add("Thu");
        Xdata.add("Fri");
        Xdata.add("Sat");
        Xdata.add("Sum");

        sdata.add(100d);
        sdata.add(120d);
        sdata.add(130d);
        sdata.add(140d);
        sdata.add(150d);
        sdata.add(160d);
        sdata.add(170d);

        seriesObj.put("data",sdata);
        seriesObj.put("type","line");
        seriesObj.put("areaStyle",areaStyle);
        series.add(seriesObj);

        tooltip.put("trigger","axis");

        xAxis.put("boundaryGap",false);
        xAxis.put("data",Xdata);
        xAxis.put("type","category");
        yAxis.put("type","value");

        res.put("title","统计图");
        res.put("xAxis", xAxis);
        res.put("tooltip", tooltip);
        res.put("yAxis", yAxis);
        res.put("series",series);


        return new Result(status,res);
    }

}

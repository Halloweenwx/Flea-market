package com.tet.fleamarket.controller;

import com.tet.fleamarket.dao.CityDao;
import com.tet.fleamarket.entity.City;
import com.tet.fleamarket.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hou Weiying
 * @date 2019-12-01 21:57
 */
@RestController
public class CityController {
    @Autowired
    CityDao cityDao;

    @GetMapping("/city")
    public Result fetchCityList() {
        ArrayList<City> cityList = cityDao.findAll();
        Map<Integer, City> cityMap = new HashMap<>();
        ArrayList<City> cityRes = new ArrayList<>();
        for (City city : cityList) {
            cityMap.put(Integer.valueOf(city.getCode()), city);
        }
        for (City city : cityList) {
            try{
                if(city.getCnName().contains("本级")){
                    continue;
                }
                if (!"00".equals(city.getCode().substring(4))) {
                    Integer father = Integer.valueOf(city.getCode().substring(0, 4) + "00");
//                    System.out.println(city.getCode()+":father->"+father);
                    if(cityMap.get(father).getChildren() == null){
                        cityMap.get(father).setChildren(new ArrayList<>());
                    }
                    cityMap.get(father).getChildren().add(city);
                }else if (!"0000".equals(city.getCode().substring(2))){
                    Integer father = Integer.valueOf(city.getCode().substring(0, 2) + "0000");
                    if(cityMap.get(father).getChildren() == null){
                        cityMap.get(father).setChildren(new ArrayList<>());
                    }
                    cityMap.get(father).getChildren().add(city);
                }
            }catch (Exception e){
                Integer father = Integer.valueOf(city.getCode().substring(0, 2) + "0000");
                if(cityMap.get(father).getChildren() == null){
                    cityMap.get(father).setChildren(new ArrayList<>());
                }
                cityMap.get(father).getChildren().add(city);
            }

        }

        for (City city : cityList){
            if("0000".equals(city.getCode().substring(2))) {
                cityRes.add(city);
            }
        }
        return Result.success(cityRes);
    }
}

package com.tet.fleamarket.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hou Weiying
 * @date 2019-11-02 20:33
 */

@RestController
public class ForeRESTController {
    @GetMapping("/forehome")
    public Object home(){
        return "yoo!";
    }
}

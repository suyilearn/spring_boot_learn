package com.didispace;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SunYing on 2017/6/9.
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index(){
        return "hello World";
    }

}

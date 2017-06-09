package com.didispace;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by SunYing on 2017/6/9.
 */
@RestController @RequestMapping("/users") public class UserController {
    //创建线程安全的Map
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = "/", method = RequestMethod.GET) public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST) public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) public User getUser(@PathVariable Long id) {
        // 通过构造这个变量可以直接使用userid中的内容作为一个查询条件直接get出来
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) public String delUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}




package com.rwt.cache.springdata.controller;

import com.rwt.cache.springdata.entity.User;
import com.rwt.cache.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 数据新增或更新，save方法可以执行添加也可以执行更新，如果需要执行持久化的实体存在主键值则更新数据，如果不存在则添加数据。
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public User save(User user) {
        return userService.save(user);
    }

    /**
     * 查询用户信息
     * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.findAll();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<User> delete(Long id){
        userService.deleteById(id);
        return userService.findAll();
    }

}

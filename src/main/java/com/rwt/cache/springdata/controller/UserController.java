package com.rwt.cache.springdata.controller;

import com.rwt.cache.springdata.entity.User;
import com.rwt.cache.springdata.service.UserService;
import com.rwt.cache.springdata.service.UserServiceDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceDemo userServiceDemo;
    @Autowired
    private UserService userService;

    /**
     * 数据新增或更新，save方法可以执行添加也可以执行更新，如果需要执行持久化的实体存在主键值则更新数据，如果不存在则添加数据。
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public User save(User user) {
        return userServiceDemo.save(user);
    }

    /**
     * 查询用户信息
     * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userServiceDemo.findAll();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public List<User> delete(Long id){
        userServiceDemo.deleteById(id);
        return userServiceDemo.findAll();
    }

    @RequestMapping(value = "/findByAgeNotNull",method = RequestMethod.GET)
    public List<User> findByAgeNotNull(){
        return userService.findByAgeNotNull();
    }

    @RequestMapping(value = "/findByNameLike",method = RequestMethod.GET)
    public List<User> findByNameLike(String name){
        return userService.findByNameLike(name);
    }

    @RequestMapping("/findUserPage")
    public Object findUserPage(int pageNo, int rows) {
        Page<User> page = userService.findUserPage(pageNo, rows);
        Map map = new HashMap<>();
        map.put("content", page.getContent());
        map.put("totalElements", page.getTotalElements());
        map.put("totalPages", page.getTotalPages());
        return map;
    }

    @RequestMapping("findByNameLikeNative")
    public List<User> findByNameLikeNative(String name) {
        return userService.findByNameLikeNative(name);
    }

    @RequestMapping("getCountOfUsers")
    public Integer getCountOfUsers() {
        return userService.getCountOfUsers();
    }
}

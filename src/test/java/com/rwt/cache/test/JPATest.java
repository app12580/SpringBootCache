package com.rwt.cache.test;

import com.rwt.cache.CacheApplication;
import com.rwt.cache.springdata.entity.User;
import com.rwt.cache.springdata.service.UserService;
import com.rwt.cache.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class JPATest {

    @Autowired
    private UserService userService;

    @Test
    public void findByNameLikeJPQLTest() {
        List<User> userList = userService.findByNameLikeJPQL("zhang");
        System.out.println(JsonUtils.toString(userList));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateUserAgeByIdTest() {
        userService.updateUserAgeById("2", "33");
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateUserAgeByAgeGreaterThan() {
        int i = userService.updateUserAgeByAgeGreaterThan("14");
        System.out.println(i);
    }

    @Test
    public void findByAgeDesc() {
        List<User> userList = userService.findAllByOrderByIdDesc();
        System.out.println(userList);
    }

    @Test
    public void findAllByIdDescTest() {
        List<User> userList = userService.findAllByIdDesc();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findAllByTwoColTest() {
        List<User> userList = userService.findAllByTwoCol();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySpecTest() {
        List<User> userList = userService.findBySpec();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySpecListTest() {
        List<User> userList = userService.findBySpecList();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySPecOrListTest() {
        List<User> userList = userService.findBySpecOrList();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySpecAndPageTest() {
        Page<User> page = userService.findBySpecAndPage();
        List<User> userList = page.getContent();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySpecAndSortTest() {
        List<User> userList= userService.findBySpecAndSort();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySpecAndPageAndSortTest() {
        Page<User> page = userService.findBySpecAndPageAndSort();
        List<User> userList = page.getContent();
        for(User user: userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findBySpecAndCriteriaQueryTest() {
        List<User> userList= userService.findBySpecAndCriteriaQuery();
        for(User user: userList) {
            System.out.println(user);
        }
    }


}

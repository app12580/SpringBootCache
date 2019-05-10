package com.rwt.cache.test;

import com.rwt.cache.CacheApplication;
import com.rwt.cache.springdata.entity.User;
import com.rwt.cache.springdata.service.UserService;
import com.rwt.cache.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


}

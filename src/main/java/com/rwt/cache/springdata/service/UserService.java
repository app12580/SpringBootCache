package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.dao.UserDao;
import com.rwt.cache.springdata.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findByAgeNotNull() {
        return userDao.findByAgeNotNull();
    }

    public List<User> findByNameLike(String name) {
        return userDao.findByNameLike(name);
    }

}

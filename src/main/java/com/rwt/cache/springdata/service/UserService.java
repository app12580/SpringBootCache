package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.dao.UserDao;
import com.rwt.cache.springdata.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<User> findUserPage(int pageNo, int rows) {
        PageRequest request = new PageRequest(pageNo - 1, rows);
        return userDao.findAll(request);
    }

    public List<User> findByNameLikeNative(String name) {
        return userDao.findByNameLikeNative(name);
    }

    public Integer getCountOfUsers() {
        return userDao.getCountOfUsers();
    }

    public List<User> findByNameLikeJPQL(String name) {
        return userDao.findByNameLikeJPQL("%" + name +"%");
    }
}

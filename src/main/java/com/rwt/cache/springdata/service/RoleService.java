package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.dao.RoleDao;
import com.rwt.cache.springdata.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role save(Role role) {
        Role save = roleDao.save(role);
        return save;
    }

}

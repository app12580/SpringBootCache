package com.rwt.cache.springdata.dao;

import com.rwt.cache.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, Serializable {

    List<User> findByAgeNotNull();

    List<User> findByNameLike(String name);

}

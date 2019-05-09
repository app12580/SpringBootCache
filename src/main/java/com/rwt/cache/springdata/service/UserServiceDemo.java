package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserServiceDemo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, Serializable {

}

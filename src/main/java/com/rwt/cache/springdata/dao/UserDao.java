package com.rwt.cache.springdata.dao;

import com.rwt.cache.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    List<User> findByAgeNotNull();

    List<User> findByNameLike(String name);

    @Query(value = "select * from t_user t where t.t_name like %:name%", nativeQuery = true)
    List<User> findByNameLikeNative(@Param("name") String name);

    @Query(value = "select count(*) from t_user ", nativeQuery=true)
    Integer getCountOfUsers();

    //JPQL 类似于Hibernate的HQL
    @Query("from User where name like :name")
    List<User> findByNameLikeJPQL(@Param("name") String name);

    @Query(value = "update t_user t set t.t_age = :age where t.t_id = :id", nativeQuery = true)
    @Modifying
    void updateUserAgeById(@Param("id") String id, @Param("age") String age);

    @Query(value = "update t_user t set t.t_age = '13' where t.t_age > :age", nativeQuery = true)
    @Modifying
    int updateUserAgeByAgeGreaterThan(@Param("age") String age);

    List<User> findAllByOrderByIdDesc();
}

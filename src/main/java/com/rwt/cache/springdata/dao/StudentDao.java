package com.rwt.cache.springdata.dao;

import com.rwt.cache.springdata.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentDao extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
}

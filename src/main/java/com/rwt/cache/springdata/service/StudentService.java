package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.dao.StudentDao;
import com.rwt.cache.springdata.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Student save(Student student) {
        return studentDao.save(student);
    }


}

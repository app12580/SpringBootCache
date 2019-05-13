package com.rwt.cache.test;

import com.rwt.cache.CacheApplication;
import com.rwt.cache.springdata.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTemplateTest1() {
        redisTemplate.opsForValue().set("k1", "v1");
        Object k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1.getClass().getName());
        System.out.println(k1);
    }

    @Test
    public void redisTemplateTest2() {
        Student student = new Student();
        student.setId(1L);
        student.setSex('0');
        student.setAge(12);
        student.setName("hanna");
        redisTemplate.opsForValue().set("stu1", student);
        Object stu1 = redisTemplate.opsForValue().get("stu1");
        System.out.println(stu1.getClass().getName());
        System.out.println(stu1);
        System.out.println((Student)stu1);
    }

}

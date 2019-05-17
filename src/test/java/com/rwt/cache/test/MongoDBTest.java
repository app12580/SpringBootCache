package com.rwt.cache.test;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.rwt.cache.CacheApplication;
import com.rwt.cache.springdata.entity.User;
import com.rwt.cache.springdata.mongodb.dao.CustomerRepository;
import com.rwt.cache.springdata.mongodb.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class MongoDBTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void testMongodb() {
        try {
            // 连接到 mongodb 服务
            Mongo mongo = new Mongo("127.0.0.1", 27017);
            //根据mongodb数据库的名称获取mongodb对象 ,
            DB db = mongo.getDB("test");
            //db的用法和MongoDB的客户端用法基本一致
            Set<String> collectionNames = db.getCollectionNames();
            // 打印出test中的集合
            for (String name : collectionNames) {
                System.out.println("collectionName===" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}

package com.rwt.cache.test;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.client.result.UpdateResult;
import com.rwt.cache.CacheApplication;
import com.rwt.cache.springdata.entity.User;
import com.rwt.cache.springdata.mongodb.dao.CustomerRepository;
import com.rwt.cache.springdata.mongodb.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class MongoDBTest {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 原生客户端连接
     */
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


    /**
     * springdata 整合
     */
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

    /**
     * 测试MongoTemplate
     */
    @Test
    public void mongoTemplateTest() {
        //增加
        Customer customer = new Customer("aa", "bb");
        mongoTemplate.insert(customer);

        //查找
        Customer find = mongoTemplate.findById(customer.getId(), Customer.class);

        //更新
        Query query=new Query(Criteria.where("id").is(find.getId()));

        Update update = new Update().set("lastName", "ccc");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Customer.class);
        System.out.println(updateResult);

        find = mongoTemplate.findById(customer.getId(), Customer.class);
        System.out.println("修改后：" + find);

        List<Customer> all = mongoTemplate.findAll(Customer.class);
        System.out.println("删除前：" + all.size());
        for(Customer c: all) {
            System.out.println(c);
        }

        //删除
        mongoTemplate.remove(find);
        List<Customer> all2 = mongoTemplate.findAll(Customer.class);
        System.out.println("删除后：" + all2.size());
        for(Customer c: all2) {
            System.out.println(c);
        }

        //删除集合
//        mongoTemplate.dropCollection(Customer.class);
    }

    @Test
    public void pageTest() {

        Customer customer = new Customer("aa", "bb");
        mongoTemplate.insert(customer);


        Query query = new Query();
//        query.addCriteria(Criteria.where("firstName").is("aa"));
        //query的排序
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"id")));
        //query的分页
        Pageable pageable = new PageRequest(0, 5); // get 5 profiles on a page
        query.with(pageable);
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        for(Customer c: customers) {
            System.out.println(c);
        }
    }
}

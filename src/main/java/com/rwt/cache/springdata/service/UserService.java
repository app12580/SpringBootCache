package com.rwt.cache.springdata.service;

import com.rwt.cache.springdata.dao.UserDao;
import com.rwt.cache.springdata.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        return userDao.findByNameLikeJPQL("%" + name + "%");
    }

    public void updateUserAgeById(String id, String age) {
        userDao.updateUserAgeById(id, age);
    }

    public int updateUserAgeByAgeGreaterThan(String age) {
        int i = userDao.updateUserAgeByAgeGreaterThan(age);
        return i;
    }

    /**
     * 排序方式一
     *
     * @return
     */
    public List<User> findAllByOrderByIdDesc() {
        List<User> userList = userDao.findAllByOrderByIdDesc();
        return userList;
    }

    /**
     * 排序方式二： 通过Sort排序
     *
     * @return
     */
    public List<User> findAllByIdDesc() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return userDao.findAll(sort);
    }

    public List<User> findAllByTwoCol() {
        Order order1 = new Order(Sort.Direction.DESC, "age");
        Order order2 = new Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order1, order2);
        return userDao.findAll(sort);
    }


    public List<User> findBySpec() {
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate pre = criteriaBuilder.equal(root.get("name"), "zhangsan");
                return pre;
            }
        };
        return userDao.findAll(spec);
    }

    public List<User> findBySpecList() {
        Specification<User> spec = new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> preList = new ArrayList<>();
                preList.add(criteriaBuilder.equal(root.get("name"), "zhangsan"));
                preList.add(criteriaBuilder.lessThan(root.get("age"), 14));
                Predicate[] arr = new Predicate[preList.size()];
                return criteriaBuilder.and(preList.toArray(arr));
            }
        };
        return userDao.findAll(spec);
    }

    public List<User> findBySpecOrList() {
        Specification<User> spec = new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%li%"),
                        criteriaBuilder.lessThan(root.get("age"), 15));
            }
        };
        return userDao.findAll(spec);
    }

    public Page<User> findBySpecAndPage() {
        Specification<User> spec = new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "zhangsan");
            }
        };
        PageRequest pageRequest = new PageRequest(1, 4);
        return userDao.findAll(spec, pageRequest);
    }

    public List<User> findBySpecAndSort() {
        Specification<User> spec = new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "zhangsan");
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        return userDao.findAll(spec, sort);
    }

    /**
     * 备注： 在PageRequst的定义时，将sort数据存进去
     *
     * @return
     */
    public Page<User> findBySpecAndPageAndSort() {
        Sort sort = new Sort(Sort.Direction.ASC, "age");
        Specification<User> spec = new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%zhang%");
            }
        };
        PageRequest pageRequest = new PageRequest(2, 2, sort);
        return userDao.findAll(spec, pageRequest);
    }

    public List<User> findBySpecAndCriteriaQuery() {
        Specification<User> spec = new Specification<User>() {
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.like(root.get("name").as(String.class), "%zhang%");
                Predicate p2 = cb.lessThan(root.get("age").as(String.class), "19");
                //把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的
                query.where(cb.or(p1, p2));
                //添加排序的功能
//                query.orderBy(cb.desc(root.get("id").as(Long.class)));
                query.orderBy(cb.desc(root.get("id")));
                return query.getRestriction();
            }
        };
        return userDao.findAll(spec);
    }
}



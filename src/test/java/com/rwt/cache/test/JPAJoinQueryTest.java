package com.rwt.cache.test;

import com.rwt.cache.CacheApplication;
import com.rwt.cache.springdata.entity.Document;
import com.rwt.cache.springdata.entity.Role;
import com.rwt.cache.springdata.entity.Student;
import com.rwt.cache.springdata.service.DocumentService;
import com.rwt.cache.springdata.service.RoleService;
import com.rwt.cache.springdata.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheApplication.class)
public class JPAJoinQueryTest {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoleService roleService;

    @Test
    @Transactional
    @Rollback(false)
    public void saveNewDocumentTest() {
        Student student = new Student();
        student.setName("Valen");
        student.setAge(15);
        student.setSex('1');
        Document document = new Document();
        document.setName("Valen个人档案");
        document.setDescription("Laura档案介绍");
        document.setStudent(student);
        studentService.save(student);
        documentService.save(document);
        System.out.println("新档案保存成功");
    }

    @Test
    public void documentGetTest() {
        Document document = documentService.get(1L);
        System.out.println(document);
        System.out.println("documentGetTest success");
    }

    @Test
    @Transactional
    @Rollback(false)
    public void documentDeleteTest() {
        Document document = documentService.get(1L);
        documentService.delete(document);   //物理删除数据，并且默认配置下是没有级联删除的
    }

    @Test
    @Transactional
    @Rollback(false)
    public void manyToOneTest() {
        Role role = new Role();
        role.setName("测试角色1");
        Student s1 = new Student();
        s1.setName("lisa");
        s1.setAge(15);
        s1.setSex('1');
        Student s2 = new Student();
        s2.setName("antasa");
        s2.setAge(14);
        s2.setSex('1');

        role.setStudents(new HashSet<Student>(){{
            this.add(s1);
            this.add(s2);
        }});
        s1.setRole(role);
        s2.setRole(role);
        studentService.save(s1);
        studentService.save(s2);
        roleService.save(role);
    }

}

package com.baizhi.demo;


import com.baizhi.App;
import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentService studentService;
    @Test
    public void select(){
        Student student = new Student();
        student.setName("盛子维");
        student.setId("1");
        studentService.update(student);
    }
    @Test
    public void select1(){
        List<Student> students = studentService.queryAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

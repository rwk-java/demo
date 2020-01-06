package com.baizhi.dao;

import com.baizhi.entity.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> queryAll();
    public void update(Student student);
    public Student queryByName(String name);
}

package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private String id;
    private String name;
    private String password;
    private Integer age;
    private String sex;
    private Date birthday;
}

package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private int age;
    private String studentClass;
    private String email;
    private String address;
    private String phone;
}
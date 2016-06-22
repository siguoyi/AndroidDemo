package com.example.chenlei1_iri.androiddemo;

/**
 * Created by chenlei1-iri on 2016/6/16.
 */
public class Student {
    private static final String TAG = Student.class.getSimpleName();

    private int id;
    private int age;
    private String name;
    private String sex;

    public Student(int id, int age, String name, String sex){
        this.id = id;
        this.age = age;
        this.name = name;
        this.sex = sex;
    }
}

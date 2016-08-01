package com.example.chenlei1_iri.androiddemo.model;

import java.util.ArrayList;

/**
 * Created by chenlei1-iri on 2016/6/16.
 */
public class Student {
    private static final String TAG = Student.class.getSimpleName();

    private int id;
    private int age;
    private String name;
    private String sex;
    private ArrayList<Course> courseList;

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(){};

    public Student(int id, int age, String name, String sex, ArrayList<Course> courseList){
        this.id = id;
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.courseList = courseList;
    }

    public class Course {
        private String chinese_laguage = "语文";
        private String math ="数学";
        private String english = "英语";
        private String physics = "物理";
        private String chemistry = "化学";

        public Course(int i){
            switch (i){
                case 0:

                    break;
                case 1:
                    this.math = "";
                    this.chemistry = "";
                    break;
                case 2:
                    this.physics = "";
                    break;
            }
        }

        public String getChinese_laguage() {
            return chinese_laguage;
        }

        public void setChinese_laguage(String chinese_laguage) {
            this.chinese_laguage = chinese_laguage;
        }

        public String getMath() {
            return math;
        }

        public void setMath(String math) {
            this.math = math;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public String getPhysics() {
            return physics;
        }

        public void setPhysics(String physics) {
            this.physics = physics;
        }

        public String getChemistry() {
            return chemistry;
        }

        public void setChemistry(String chemistry) {
            this.chemistry = chemistry;
        }

        public String getCourse(){
            StringBuilder sb = new StringBuilder();
            if(!chinese_laguage.equals("")){
                sb.append(chinese_laguage);
                sb.append(" ");
            }
            if(!math.equals("")){
                sb.append(math);
                sb.append(" ");
            }
            if(!english.equals("")){
                sb.append(english);
                sb.append(" ");
            }
            if(!physics.equals("")){
                sb.append(physics);
                sb.append(" ");
            }
            if(!chemistry.equals("")){
                sb.append(chemistry);
                sb.append(" ");
            }
            return sb.toString();
        }
    }
}

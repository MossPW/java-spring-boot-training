package com.training.platform.repositories;

import com.training.platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
//extends JpaRepository คือการสืบทอด class มาจาก class แม่ คือ JpaRepository ทำให้ class UserRepository มีความสามารถเช่นเดียวกับ class JpaRepository ทำให้สามารถใช้คำสั่งต่างๆ ที่มีมาให้ใน JPA ได้เลย
//User คือ Entity Class ที่ชื่อ User ที่อยู่ใน package entities ที่เราสร้าง เพื่อใช้ในการ Map ORM ระหว่าง java class กับ table ของ db ที่เราจะทำงาน
//Integer คือ Data type (ประเภทข้อมูล) ของ Primary Key ของตัวแปร Entity นั้นๆ

    //เราสามารถ and where condition ได้โดยมี Pattern เป็น camel case ดังนี้ findBy<field1_name><condition1><field2_name><condition2><field3_name>(Type paramField1, Type paramField2, Type paramField3) เช่นจากตัวอย่าง findByCityAndActiveAndAge(String city, Integer active, Integer age)
    public List<User> findByCityAndActiveAndAge(String city, Integer active, Integer age);
    public List<User> findByAgeIn(List<Integer> ages);

    //JPA
    @Query(value = "SELECT * FROM users u WHERE u.active = 1 and u.city = 'Bangkok' ",nativeQuery = true)
    public  List<User> findAllByQuery();

    @Query(value = "SELECT * FROM users u WHERE u.active =?1 and u.city =?2",nativeQuery = true)
    public List<User> findAllByParamsQuery(Integer active , String city);

    //JPQL
    @Query("SELECT u FROM User u WHERE u.active = 1 and u.city = 'Bangkok' ")
    public List<User> findAllByJpqlQuery();

    @Query("SELECT u FROM User u WHERE u.active = ?1 and u.city = ?2 ")
    public List<User> findAllByJpqlParamsQuery(Integer active, String city);

    //new method1
    public List<User> findByCityIn(List<String> cities);

    //new method2 JPQL
    @Query("SELECT u FROM User u WHERE u.age = ?1 and u.city = ?2 ")
    public List<User> findByAgeAndCity(Integer age, String city);

    //new method3
    public List<User> findByAgeGreaterThan(Integer age);



}

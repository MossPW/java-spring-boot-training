package com.training.platform.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
//Persistence เป็นการเข้าถึง entityให้อัติโนมัติ
import javax.persistence.*;
//Serializable คือตัวที่ทำการเก็บ object ลงไฟล์และคืนรูปจากไฟล์ ใช้โดยการใส่ implements Serializable ในคลาสที่เก็บข้อมูล
import java.io.Serializable;
import java.util.Date;

//@Data คือ annotation ที่เป็นของ lombok library ที่เราแอดเพิ่มขึ้นมา เพื่อมาช่วยลด Code ในส่วนของการ getter/setter method ทำให้ code สั้นลง
//@Entity คือ  annotation ที่บอกว่า Java Class นี้คือ Entity Class ที่จะทำการ map ระหว่าง java class กับ table ใน db ที่ต้องการ
//@Table(name = "users") คือ annotation ที่ระบุว่า class นี้จะ map กับ table ที่ชื่อว่าอะไร โดยระบุจากตัว parameter ที่ชื่อว่า name ว่าเป็น Table ที่ชื่อว่า users
//@Column(name = "xxxxx") คือ annotation ที่ระบุว่า ตัวแปรนี้ เช่น private int xxxxx; จะ map กับ field ชื่ออะไรใน Table ที่เราระบุใน @Table โดยจะต้องมี field type ตรงกับ ตัวแปร
@Data
@Entity
@Table(name ="users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User implements  Serializable{
    //@Transient คือ annotation ที่ระบุว่า ตัวแปรนี้ไม่ต้อง map กับ field ใน table เช่น ในกรณีของ confirm_password
    //@Id คือ annotation ที่ระบุว่า ตัวแปรที่ map กับ field นี้เป็น Primary key ของ table
    //@GeneratedValue(strategy = GenerationType.IDENTITY) คือ annotation ที่บอกว่า ตัวแปรที่ map กับ field นี้ เป็น auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirm_password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "active")
    private Integer active;

    @Column(name = "api_token")
    private String api_token;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Shop shop;

}
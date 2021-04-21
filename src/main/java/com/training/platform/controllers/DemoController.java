package com.training.platform.controllers;

import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//Mapping url
@RequestMapping("/demo")
public class DemoController {
    //@Autowired คือ annotation ที่ทำให้เราสามารถใช้ความสามารถในการทำ dependency injection (DI) เพื่อทำการ Inject object ขึ้นมาใช้งานได้ โดย object ดังกล่าวจะต้องถูกประกาศไว้ก่อนหน้า และเป็น object ที่ register อยู่ใน Spring Bean หรือพูดง่ายๆคือ class ที่สร้างมาแล้ว Spring รู้จัก Class นั้น นั่นเอง
    //เรียก object ของคลาส UserRepository ได้ทันทีไม่ต้อง new class ทุกครั้ง
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "")
    public String index() {
        //List<User> users = userRepository.findAll();
        //List<User> users คือ ประกาศตัวแปร users เป็นตัวแปรประเภท List ที่เก็บข้อมูลแบบ array ของ Entity User ที่ได้จากการ query ทั้งหมด
        //userRepository.findAll(); คือการเรียกใช้ Repository class userRepository เพื่อทำการ Query select ทุก rows ที่มาจาก Table users
        List<User> users = userRepository.findAll();
        System.out.println("############### Find All User (In Console) ###############");
//        System.out.println(users);
        for(int i=0 ; i<users.size() ;i++) System.out.println(users.get(i));
        return "Method GET, Function : index => SHOW data list on page";
    }

    @GetMapping(value = "/{id}")
    public String showWithPath(@PathVariable String id) {
        //Optional<User> user อธิบายรายละเอียดคร่าวๆคือ
        //Optional<T> โดย <T> ในที่นี้คือ Type (ประเภทข้อมูล) เป็น Feature ที่เรียกว่า Generic Type ทำให้ Class 1 ตัว สามารถยืดหยุ่นต่อการเปลี่ยนแปลงชนิดข้อมูลที่จะส่งเข้า หรือ ออก  Class ได้ตามต้องการ  เช่น String, Integer, Class เป็นต้น
        //Optional คือ Class ใหม่ที่ถูกเพิ่มเข้ามาใน Java 8 คือ java.util.Optional<T> โดยมีจุดประสงค์คือไว้จัดการกับ NullPointerExceptions อย่างเหมาะสม เพื่อลดความเสี่ยงในการเกิด bugs ของ program ซึ่ง bugs ส่วนใหญ่มักเกิดจาก NullPointerException
        Optional<User> user = userRepository.findById(Integer.parseInt(id));
        System.out.println("############### Find User By ID (In Console) ###############");
        System.out.println(user);

        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with path";
    }

    @GetMapping(value = "/findBy")
    public List<User> findByCityAndActiveAndAge() {
        List<User> users = userRepository.findByCityAndActiveAndAge("nakornpathom", 1, 18);
        return users;
    }

    @GetMapping(value = "/findByAges")
    public List<User> findByAges(@RequestParam(name = "ages") List<Integer> listAge) {
        //RequestParam(name = "ages") List<Integer> listAge
        //กำหนดชื่อ Param ที่จะไปรับจาก url ซึ่งสามารถรับได้มากกว่าหนึ่ง โดยใช้ comma คั่นเช่นน ?age=18,19
//        List<Integer> ages = new ArrayList<Integer>(Arrays.asList(18, 19, 22) );
        List<User> users = userRepository.findByAgeIn(listAge);
        return users;
    }

    @GetMapping(value = "/findByAges2")
    public List<User> findByAges2(@RequestBody Map<String,Object> dataInput){
        //การรับในลักษณะ body ผ่าน array จาก json เช่น  "age":[19,20]
        List<User> users = userRepository.findByAgeIn((List<Integer>) dataInput.get("age"));
        return users;
    }

    // Example for findAllByQuery
    @GetMapping(value = "/test1")
    public List<User> test1() {
        return userRepository.findAllByQuery();
    }
    // Example for findAllByParamsQuery
    @GetMapping(value = "/test2")
    public List<User> test2() {
        return userRepository.findAllByParamsQuery(0, "nakornpathom");
    }

    // Example for findAllByJpqlQuery
    @GetMapping(value = "/test3")
    public List<User> test3() {
        return userRepository.findAllByJpqlQuery();
    }

    // Example for findAllByJpqlParamsQuery
    @GetMapping(value = "/test4")
    public List<User> test4() {
        return userRepository.findAllByJpqlParamsQuery(0, "bangkok");
    }





//    @GetMapping(value = "")
//    @ResponseStatus(code = HttpStatus.OK)
//    public String index() {
//        return "Hello Univers!";
//    }
//
//    @GetMapping(value="")
//    public String showWithParam(@RequestParam String id){
//        return "Method Get, Function : show, ID : "+ id + " => SHOW data by id on page with query string";
//    }
//
//    @PostMapping(value = "")
//    public String create(@RequestBody Map<String,Object> inputs) {
//        System.out.println("########### POST Param ###########");
//        System.out.println(inputs);
//
//        return "Method POST, Function : create => INSERT data to DB";
//    }
//    @GetMapping(value = "/{id}")
//    public String showWithPath(@PathVariable String id) {
//        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with path";
//    }
//
//    @PatchMapping(value = "/{id}")
//    public String update(@PathVariable String id, @RequestBody Map<String,String> inputs) {
//        System.out.println("########### PATCH Param ###########");
//        System.out.println(inputs);
//
//        return "Method PATCH, Function : update => ID : " + id + " UPDATE data to DB";
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public String destroy(@PathVariable String id)  {
//        return "Method DELETE, Function : delete, ID : " + id + " => DELETE data to DB";
//    }
//
//    @RequestMapping(value = "/healthcheck")
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String healthCheck() {
//        return "Hello World!";
//    }

}


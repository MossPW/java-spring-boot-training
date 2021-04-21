package com.training.platform.controllers.api;

import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController("api-user")
//Mapping url
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public List<User> index() throws Exception {
        return userService.findAll();
    }

    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/findById/{id}")
    //Example  /findById/2
    public Optional<User> findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping(value = "/findAllByLimit")
    //Example /findAllByLimit?start=1&limit=5&&field=id
    public Page<User> findAllByLimit(@RequestParam Integer start, Integer limit, String field){
        return userService.findAllByLimit(start,limit,field);
    }

    @GetMapping(value = "/findByCityAndActiveAndAge")
    //Example /findByCityAndActiveAndAge?city="Bangkok"&active=1&&age=18
    public List<User> findByCityAndActiveAndAge(@RequestParam String city, Integer active, Integer age){
        return userService.findByCityAndActiveAndAge(city,active,age);
    }

    @GetMapping(value = "/findByAgeIn")
    public List<User> findByAges2(@RequestBody Map<String,Object> dataInput){
        //การรับในลักษณะ body ผ่าน array จาก json เช่น  "age":[19,20]
        List<User> users = userService.findByAgeIn((List<Integer>) dataInput.get("age"));
        return users;
    }

    @GetMapping(value = "/findAllByQuery")
    public List<User> findAllByQuery() {
        return userService.findAllByQuery();
    }


    @GetMapping(value = "/findAllByParamsQuery")
    public List<User> findAllByParamsQuery(@RequestParam Integer active, String city){
        return userService.findAllByParamsQuery(active,city);
    }

    @GetMapping(value = "/findAllByJpqlQuery")
    public List<User> findAllByJpqlQuery() {
        return userService.findAllByJpqlQuery();
    }


    @GetMapping(value = "/findAllByJpqlParamsQuery")
    public List<User> findAllByJpqlParamsQuery(@RequestParam Integer active, String city) {
        return userService.findAllByJpqlParamsQuery(active, city);
    }

    //new method1
    @GetMapping(value = "/findByCityIn")
    //example /findByCityIn?city=bangkok,nakornpathom
    public List<User> findByAges(@RequestParam(name = "city") List<String> listCity) {
        List<User> users = userService.findByCityIn(listCity);
        return users;
    }

    //new method2
    @GetMapping(value = "/findByAgeAndCity")
    //example /findByCityIn?age=19&city=nakornpathom
    public List<User> findByAgeAndCity(@RequestParam Integer age, String city) {
        return userService.findByAgeAndCity(age, city);
    }

    //new method3
    @GetMapping(value = "/findByAgeGreaterThan/{age}")
    //example /findByAgeGreaterThan/19
    public List<User> findByAgeAndCity(@PathVariable Integer age) {
        return userService.findByAgeGreaterThan(age);
    }




}

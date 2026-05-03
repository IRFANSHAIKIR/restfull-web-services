package com.springboot.restfull_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public  Personv1 getFirstVersionOfPerson(){
        return  new Personv1("irfan shaik");
    }
    @GetMapping("/v2/person")
    public  Person getSecondVersionOfPerson(){
        return  new Person("irfan", "shaik");
    }
}

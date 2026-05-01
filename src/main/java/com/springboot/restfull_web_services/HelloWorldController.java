package com.springboot.restfull_web_services;


import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello world";
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(Locale locale){
        return messageSource.getMessage("good.morning.message", null, "Good Morning", locale);
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world bean");
    }

    @GetMapping("/hello-world-bean/{name}")
    public  HelloWorldBean helloWorldBeanPath(@PathVariable String name){
        return  new HelloWorldBean(String.format("Hello %s, welcome to String boot", name));
    }
}

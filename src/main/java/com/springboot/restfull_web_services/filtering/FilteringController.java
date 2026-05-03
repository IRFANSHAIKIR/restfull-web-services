package com.springboot.restfull_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    @JsonView(Views.Basic.class)
    public SomeBean filtering() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    @JsonView(Views.Details.class)
    public List<SomeBean> filteringList() {
        return List.of(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6")
        );
    }
}

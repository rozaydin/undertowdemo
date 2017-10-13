package com.rhtech.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloRest {

    @GetMapping()
    public String getPerson() {
        return "cant believe this actually works";
    }

}

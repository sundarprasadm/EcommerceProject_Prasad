package com.prasad.ecommerceproject.Controllor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HeloCotrollor {
    @GetMapping("/say/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "Hello qq"+name;
    }
}

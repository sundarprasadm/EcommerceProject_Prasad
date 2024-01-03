package com.prasad.ecommerceproject.Controllor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductControllor {

    @GetMapping("/{id}")
    public String singleProduct(@PathVariable("id") Long id) {
        return "Hello " + id;
    }
}

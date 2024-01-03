package com.prasad.ecommerceproject.Controllor;

import com.prasad.ecommerceproject.Models.Product;
import com.prasad.ecommerceproject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductControllor {

    ProductService productService;
    RestTemplate restTemplate;

    @Autowired
    public void ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public Product singleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }
}

package com.prasad.ecommerceproject.Service;

import com.prasad.ecommerceproject.Models.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product getSingleProduct(Long id);

}

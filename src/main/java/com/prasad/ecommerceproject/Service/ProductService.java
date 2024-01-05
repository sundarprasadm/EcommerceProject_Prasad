package com.prasad.ecommerceproject.Service;

import com.prasad.ecommerceproject.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    //Create new Product
    Product createNewProduct(Product product);

    Product replaceproduct(Long id,Product product);
    Product updatePartialProduct(Long id,Product product);

    void deleteProduct(Long id);



}

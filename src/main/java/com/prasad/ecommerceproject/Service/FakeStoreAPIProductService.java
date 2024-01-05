package com.prasad.ecommerceproject.Service;

import com.prasad.ecommerceproject.Models.Category;
import com.prasad.ecommerceproject.Models.Product;
import com.prasad.ecommerceproject.dto.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreAPIProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreAPIProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());
        product.getCategory().setId(fakeStoreProduct.getId());

        return product;
    }
    @Override
    public Product getSingleProduct(Long id){
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts(){
        //        List<FakeStoreProductDto> response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                List<FakeStoreProductDto>.class
//        );
        // runtime
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> answer = new ArrayList<>();
        if(response!=null){
            for (FakeStoreProductDto dto: response) {
                answer.add(convertFakeStoreProductToProduct(dto));
            }
        }


        return answer;
    }

    @Override
    public Product createNewProduct(Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        if(null!=product.getImageUrl()){
            fakeStoreProductDto.setImage(product.getImageUrl());
        }
        FakeStoreProductDto fakeStoreProduct = restTemplate.postForObject(
                "https://fakestoreapi.com/products",fakeStoreProductDto,
                FakeStoreProductDto.class);
        if(fakeStoreProduct!=null){
            return convertFakeStoreProductToProduct(fakeStoreProduct);
        }
        return new Product();

    }

    @Override
    public Product updatePartialProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceproduct(Long id, Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public void deleteProduct(Long id){

    }
}

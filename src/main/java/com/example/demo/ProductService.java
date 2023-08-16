package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductJpaRepository productRepository;

    public ProductService(ProductJpaRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEnity getProductById(@RequestParam Long id) {

        Optional<ProductEnity> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new RuntimeException("찾을 수 없는 상품입니다.");
        }
        return product.get();
    }
    public String saveProducts(@RequestBody ProductEnity newProductEntity){

        Optional<ProductEnity> product = productRepository.findById(newProductEntity.getId());
        if(product.isPresent()){
            throw new RuntimeException("등록에 실패했습니다.");
        }
        return "실패";
    }
    public List<ProductEnity> getProducts(){
        return productRepository.findAll();
    }
    public List<ProductEnity> getProductByName(@RequestParam String name){
        return productRepository.findByName(name);
    }
    public List<ProductEnity>getProductByNameAndPrice(@RequestParam String name, @RequestParam Long price){
        return productRepository.findByNameAndPrice(name, price);
    }
    public List<ProductEnity>getProductByNameOrderByPrice(@RequestParam String name){
        return productRepository.findByNameOrderByPrice(name);
    }
}


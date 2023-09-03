package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductJpaRepository productRepository;

    public ProductService(ProductJpaRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEnity getProductById( Long id) {

        Optional<ProductEnity> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new RuntimeException("찾을 수 없는 상품입니다.");
        }
        return product.get();
    }
    public String saveProduct(ProductEnity newProductEntity){

//        Optional<ProductEnity> product = productRepository.findById(newProductEntity.getId());
//        if(product.isPresent()){
//            throw new RuntimeException("등록에 실패했습니다.");
//        }
        productRepository.save(newProductEntity);
        return "성공";
    }
    public  List<ProductEnity> getProducts(){
        return productRepository.findAll();
    }
    public List<ProductEnity> getProductByName( String name){
        return productRepository.findByName(name);
    }
    public List<ProductEnity>getProductByNameAndPrice( String name,  Long price){
            return productRepository.findByNameAndPrice(name, price);
    }
    public List<ProductEnity>getProductByNameOrderByPrice( String name){
        return productRepository.findByNameOrderByPrice(name);
    }
}


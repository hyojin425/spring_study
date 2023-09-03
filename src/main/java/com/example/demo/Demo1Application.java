package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }


    @RestController
    public class ProductController{

        private final ProductJpaRepository productRepository;
        private final ProductService productService;

        public ProductController(ProductJpaRepository productRepository, ProductService productService) {
            this.productRepository = productRepository;
            this.productService=productService;
        }


        @GetMapping("/products")
        public List<ProductEnity> getProducts(){
            return productService.getProducts();
        }

        @GetMapping("/products/{id}")
        public ProductEnity getProductById(@PathVariable Long id) {
            ProductEnity result=productService.getProductById(id);
            return result;
        }

        @PostMapping("/products")
        public String saveProduct(@RequestBody ProductEnity newProductEntity){

            Random random = new Random();
            Long randomId=random.nextLong();

            ProductEnity product=new ProductEnity();
            product.setPrice(newProductEntity.getPrice());
            product.setName(newProductEntity.getName());
            product.setId(randomId);

            productService.saveProduct(product);

            return "성공";
        }

        @GetMapping(value = "/products",params="name")
        public List<ProductEnity> getProductByName(@RequestParam String name){
            return productService.getProductByName(name);
        }

        @GetMapping(value = "/products", params = {"name", "price"})
        public List<ProductEnity>getProductByNameAndPrice(@RequestParam String name, @RequestParam Long price){
            return productService.getProductByNameAndPrice(name, price);
        }

        @GetMapping("/productsOrderByPrice")
        public List<ProductEnity>getProductByNameOrderByPrice(@RequestParam String name){
            return productService.getProductByNameOrderByPrice(name);
        }
    }
}

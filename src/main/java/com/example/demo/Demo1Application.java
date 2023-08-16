package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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


        @GetMapping("products")
        public List<ProductEnity> getProducts(){
            List<ProductEnity> result=productService.getProducts();
            return result;
        }

        @GetMapping(value="products",params ="id")
        public ProductEnity getProductById(@RequestParam Long id) throws Exception {
            ProductEnity result=productService.getProductById(id);
            return result;
        }

        @PostMapping("products")
        public String saveProducts(@RequestBody ProductEnity newProductEntity){

            Random random = new Random();
            ProductEnity product=new ProductEnity();
            Long randomId=random.nextLong();

            product.setPrice(newProductEntity.getPrice());
            product.setName(newProductEntity.getName());
            product.setId(randomId);

            productService.saveProducts(product);
            productRepository.save(product);

            return "성공";
        }

        @GetMapping(value = "products",params="name")
        public List<ProductEnity> getProductByName(@RequestParam String name){
            List<ProductEnity> result=productService.getProductByName(name);
            return result;
        }

        @GetMapping(value = "products", params = {"name", "price"})
        public List<ProductEnity>getProductByNameAndPrice(@RequestParam String name, @RequestParam Long price){
            List<ProductEnity> result=productService.getProductByNameAndPrice(name,price);
            return result;
        }

        @GetMapping("/productsOrderByPrice")
        public List<ProductEnity>getProductByNameOrderByPrice(@RequestParam String name){
            List<ProductEnity> result=productService.getProductByNameOrderByPrice(name);
            return result;
        }
    }
}

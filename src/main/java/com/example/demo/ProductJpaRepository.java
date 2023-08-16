package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEnity,Long> {

    List<ProductEnity> findByName(String name);

    List<ProductEnity> findByNameAndPrice(String name, Long price);

    List<ProductEnity> findByNameOrderByPrice(String name);


}

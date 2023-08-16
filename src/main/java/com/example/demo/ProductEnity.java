package com.example.demo;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Table
@Entity
@Getter
@Setter
public class ProductEnity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    //생성자와 getter

    public ProductEnity(){
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Long getPrice(){
        return price;
    }


}

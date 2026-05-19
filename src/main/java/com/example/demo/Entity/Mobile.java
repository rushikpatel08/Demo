package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="mobile")
public class Mobile {

    //define table structure
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Long price;

}

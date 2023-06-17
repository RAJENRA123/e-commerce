package com.bikkadiT.demo.entyties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String productId;

    @Column(name = "product_title",length = 60, nullable = false)
    private String title;

    @Column(name = "category_desc",length = 50)
    private String description;

    private double price;

    @Column(length = 10000)
    private double discountedPrice;

    private Integer quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;


}

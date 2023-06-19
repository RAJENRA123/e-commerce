package com.bikkadiT.demo.entyties;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Builder
public class Product {

    @Id
    private String productId;

    private String title;

    @Column(length = 10000)
    private String description;

    private double price;


    private double discountedPrice;

    private Integer quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;

    private String productImageName;

    private String ContentType;
}

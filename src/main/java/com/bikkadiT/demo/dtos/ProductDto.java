package com.bikkadiT.demo.dtos;


import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private String productId;

    private String title;

    private String description;

    private double price;


    private double discountedPrice;

    private Integer quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;

}

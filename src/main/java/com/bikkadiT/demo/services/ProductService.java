package com.bikkadiT.demo.services;

import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.ProductDto;

public interface ProductService {
    //create
    ProductDto create(ProductDto productDto);

    //update
 ProductDto update(ProductDto productDto,String productId);

    //delete
     void detete (String productId);

    //get single
    ProductDto get(String productId);

    //getAll
    PageableResponse<ProductDto> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get all:live
    PageableResponse<ProductDto> getAllLive(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //search product
    PageableResponse<ProductDto> searchByTitle(String subTitle, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);



}

package com.bikkadiT.demo.controllers;

import com.bikkadiT.demo.dtos.ApiResponseMessage;
import com.bikkadiT.demo.dtos.CategoryDto;
import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.ProductDto;
import com.bikkadiT.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    //creat
    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Initiated request for create Product Details");

        ProductDto createdProduct = productService.create(productDto);
        log.info("Completed request for create Product details");

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);

    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
        log.info("Initiate request for update product details with productId:{}*",productId);
        ProductDto updatedProduct = productService.update(productDto, productId);
        log.info("Completed request for update product details with productId:{}*",productId);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    //delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId) {
        log.info("Initiate request for delete product details with productid:{}*",productId);

        productService.detete(productId);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Product is deleted successfully!!").status(HttpStatus.OK).success(true).build();

        log.info("Completed request for delete product details with productid:{}*",productId);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    //get single
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {

        ProductDto productDto = productService.get(productId);


        return new ResponseEntity<>(productDto, HttpStatus.OK);


    }

    @GetMapping("/productId")
    public ResponseEntity<PageableResponse<ProductDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        log.info("Initiate request to get all product details ");

        PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed request to get all product details");

        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);


    }

    //get all live
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(

            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        log.info("Initiate request to get allLive product details ");

        PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed request to get allLive product details");

        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);

    }



    //search all
    @GetMapping("/search/{query}")
    public <pageNumber> ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer  pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> pageableResponse = productService.searchByTitle(query,pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);

    }}
package com.bikkadiT.demo.controllers;

import com.bikkadiT.demo.dtos.*;

import com.bikkadiT.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    @Value("$(product.image.path)")
    private String imagePath;

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
        log.info("Initiate request for update product details with productId:{}*", productId);
        ProductDto updatedProduct = productService.update(productDto, productId);
        log.info("Completed request for update product details with productId:{}*", productId);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    //delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId) {
        log.info("Initiate request for delete product details with productid:{}*", productId);

        productService.detete(productId);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Product is deleted successfully!!").status(HttpStatus.OK).success(true).build();

        log.info("Completed request for delete product details with productid:{}*", productId);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    //get single
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {

        ProductDto productDto = productService.get(productId);


        return new ResponseEntity<>(productDto, HttpStatus.OK);


    }

    @GetMapping
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
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> pageableResponse = productService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);

    }

    //upload image
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uplodProductImage(
            @PathVariable String productId,
            @RequestParam("productImage") MultipartFile image
    ) throws IOException {
        String fileName = fileService.uploadFile(image, imagePath);

        ProductDto productDto = productService.get(productId);
        productDto.setProductImageName(fileName);
        ProductDto updatedProducct = productService.update(productDto, productId);
        ImageResponse response = ImageResponse.builder().imageName(updatedProducct.getProductImageName()).message("product image successfully uploaded").status(String.valueOf(HttpStatus.CREATED)).success(String.valueOf(true)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping(value = "/image/{productId}")
    public void serveProductImage(@PathVariable String productId, HttpServletResponse response) throws IOException, IOException {
        ProductDto productDto = productService.get(productId);
        InputStream resource = fileService.getResource(imagePath);
        resource.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
//
    }
    }


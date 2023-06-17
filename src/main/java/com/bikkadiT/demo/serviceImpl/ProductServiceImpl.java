package com.bikkadiT.demo.serviceImpl;

import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.ProductDto;
import com.bikkadiT.demo.entyties.Product;
import com.bikkadiT.demo.exceptions.ResourceNotFoundException;
import com.bikkadiT.demo.helper.Helper;
import com.bikkadiT.demo.repositories.ProductRepository;
import com.bikkadiT.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto create(ProductDto productDto) {
        log.info("Initiating dao call for create product");
        Product product = mapper.map(productDto, Product.class);
        //product id
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        //added date
        product.setAddedDate(new Date());
        Product saveProduct = productRepository.save(product);
        log.info("Completed dao call for create product");

        return mapper.map(saveProduct, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        log.info("Initiating dao call for Update the product details with productId:{}", productId);

        //fetching the product of given id
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        //save the entity
        Product updatedProduct = productRepository.save(product);
        log.info("Completed dao call for Update the product details with productId:{}", productId);

        return mapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void detete(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id"));
        productRepository.delete(product);

    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id"));
        return mapper.map(product, ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> getAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        log.info("Initiate dao call for get all product details");
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
      Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findAll(pageable);
        log.info("Completed dao call for get all product details");
        return Helper.getPageableReaponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        log.info("Initiate dao call for get allLive product details");
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        log.info("Completed dao call for get allLive product details");
        return Helper.getPageableReaponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return Helper.getPageableReaponse(page, ProductDto.class);
    }
}

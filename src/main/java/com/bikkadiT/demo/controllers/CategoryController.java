package com.bikkadiT.demo.controllers;

import com.bikkadiT.demo.dtos.ApiResponseMessage;
import com.bikkadiT.demo.dtos.CategoryDto;
import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.UserDto;
import com.bikkadiT.demo.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //create
    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        log.info("Initiated request for create Category Details");
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        log.info("Completed request for create Category details");
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody CategoryDto categoryDto){
        log.info("Initiate request for update category details with categoryId:{}*",categoryId);
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        log.info("Completed request for update category details with categoryId:{}*",categoryId);

        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable Integer categoryId){
        log.info("Initiate request for delete category details with userid:{}*",categoryId);
        categoryService.delete(categoryId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Category is deleted successfully").status(HttpStatus.OK).success(true).build();

        log.info("Completed request for delete category details with userid:{}*",categoryId);

        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }
    //get all
    @GetMapping("/category")
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
        @RequestParam(value = "pageNumber",defaultValue="0",required=false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue="10",required=false) Integer pageSize,
        @RequestParam(value = "sortBy",defaultValue="title",required=false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue="asc",required=false) String sortDir)
    {
        log.info("Initiate request to get all category details ");

        PageableResponse<CategoryDto> pageableResponse = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);

        log.info("Completed request to get all category details");

        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }
    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable Integer categoryId){
        log.info("Initiate request for get category details with categoryId:{}*",categoryId);

        CategoryDto categoryDto = categoryService.get(categoryId);
        log.info("Completed request for get category details with categoryId:{}*",categoryId);

        return ResponseEntity.ok(categoryDto);
    }
}

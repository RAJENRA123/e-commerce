package com.bikkadiT.demo.services;

import com.bikkadiT.demo.dtos.CategoryDto;
import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.dtos.UserDto;

public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto,Integer categoryId);

    //delete
    void delete(Integer categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortdir);



    //get single category detaill
    CategoryDto get(Integer categoryId);

    UserDto updateCategory(UserDto userDto, Integer userId);

    //search

}

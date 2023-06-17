package com.bikkadiT.demo.serviceImpl;

import com.bikkadiT.demo.dtos.CategoryDto;
import com.bikkadiT.demo.dtos.PageableResponse;
import com.bikkadiT.demo.entyties.Category;
import com.bikkadiT.demo.exceptions.ResourceNotFoundException;
import com.bikkadiT.demo.helper.Helper;
import com.bikkadiT.demo.repositories.CategoryRepository;
import com.bikkadiT.demo.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        log.info("Initiating dao call for create category");

        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        log.info("Completed dao call for create category");

        return mapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Integer categoryId) {
        //get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception!!"));
        log.info("Initiating dao call for Update the product details with categoryId:{}", categoryId);

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        log.info("Completed dao call for Update the category details with userId:{}", categoryId);

        return mapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void delete(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception!!"));
        categoryRepository.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortdir) {
        log.info("Initiate dao call for get all category details");
        Sort sort=(sortdir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableReaponse = Helper.getPageableReaponse(page, CategoryDto.class);
        log.info("Completed dao call for get all category details");
        return pageableReaponse;
    }

    @Override
    public CategoryDto get(Integer categoryId) {
        log.info("Initiate dao call for get  category details");

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found exception!!"));
        log.info("Completed dao call for get category details");

        return mapper.map(category,CategoryDto.class);
    }
}

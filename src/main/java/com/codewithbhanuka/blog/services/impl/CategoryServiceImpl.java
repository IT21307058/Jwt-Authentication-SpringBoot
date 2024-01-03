package com.codewithbhanuka.blog.services.impl;

import com.codewithbhanuka.blog.entities.Category;
import com.codewithbhanuka.blog.exception.ResourceNotFoundException;
import com.codewithbhanuka.blog.payloads.CategoryDto;
import com.codewithbhanuka.blog.repositories.CategoryRepo;
import com.codewithbhanuka.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = this.categoryRepo.save(cat);

        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatecat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatecat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category deleteCat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        this.categoryRepo.delete(deleteCat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category deleteCat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        return this.modelMapper.map(deleteCat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> listcat = this.categoryRepo.findAll();

        List<CategoryDto> categoryDtos = listcat.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}

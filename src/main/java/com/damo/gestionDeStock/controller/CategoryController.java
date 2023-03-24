package com.damo.gestionDeStock.controller;

import com.damo.gestionDeStock.controller.api.CategoryApi;
import com.damo.gestionDeStock.dto.CategoryDto;
import com.damo.gestionDeStock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {


    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categDto) {
        return categoryService.save(categDto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {
        return categoryService.findByCodeCategory(codeCategory);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {

        categoryService.delete(id);

    }
}

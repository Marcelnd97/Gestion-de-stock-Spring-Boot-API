package com.damo.gestionDeStock.service;

import com.damo.gestionDeStock.dto.CategoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryService {

    CategoryDto save(CategoryDto categDto);
    CategoryDto findById(Integer id);
    CategoryDto findByCodeCategory(String codeCategory);
    List<CategoryDto> findAll();
    void delete(Integer id);
}

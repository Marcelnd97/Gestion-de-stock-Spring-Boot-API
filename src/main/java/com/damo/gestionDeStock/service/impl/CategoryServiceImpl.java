package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.CategoryDto;
import com.damo.gestionDeStock.exception.EntityNotFoundException;
import com.damo.gestionDeStock.exception.ErrorCodes;
import com.damo.gestionDeStock.exception.InvalidEntityException;
import com.damo.gestionDeStock.model.Category;
import com.damo.gestionDeStock.repository.CategoryRepository;
import com.damo.gestionDeStock.service.CategoryService;
import com.damo.gestionDeStock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categDto) {

        List<String> errors = CategoryValidator.validator(categDto);

        if (!errors.isEmpty()){
             log.error("Category is not valid {}", categDto);
             throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_FOUND, errors);
        }

        return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categDto)));
    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucune categorie avec l'ID =" + id + "n'est trouver dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCodeCategory(String codeCategory) {
        if (codeCategory == null){
            log.error("Category CODE is not valid");
        return null;
        }
        Optional<Category> category = categoryRepository.findByCode(codeCategory);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucune categorie avec le CODE =" + codeCategory + "n'est trouver dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND));
    }

        @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return;
        }
        categoryRepository.findById(id);
    }
}

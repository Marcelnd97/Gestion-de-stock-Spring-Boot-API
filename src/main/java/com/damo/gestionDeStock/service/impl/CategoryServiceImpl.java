package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.CategoryDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.handlers.exception.InvalideOperationException;
import com.damo.gestionDeStock.model.Article;
import com.damo.gestionDeStock.model.Category;
import com.damo.gestionDeStock.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository){
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categDto) {

        List<String> errors = CategoryValidator.validator(categDto);

        if (!errors.isEmpty()){
             log.error("Category is not valid {}", categDto);
             throw new InvalidEntityException("La categorie n'est pas valide",
                     ErrorCodes.CATEGORY_NOT_VALID, errors);
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
        List<Article> article = articleRepository.findAlArticleByCategoryId(id);
        if (!article.isEmpty()) {
            throw new InvalideOperationException("Impossible de supprimer cette categorie qui est deja utilise",
                    ErrorCodes.CATEGORY_ALREADY_IN_USE);
        }
        categoryRepository.deleteById(id);
    }
}

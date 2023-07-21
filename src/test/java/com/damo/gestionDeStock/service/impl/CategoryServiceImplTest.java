package com.damo.gestionDeStock.service.impl;

import com.damo.gestionDeStock.dto.CategoryDto;
import com.damo.gestionDeStock.handlers.exception.EntityNotFoundException;
import com.damo.gestionDeStock.handlers.exception.ErrorCodes;
import com.damo.gestionDeStock.handlers.exception.InvalidEntityException;
import com.damo.gestionDeStock.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldSaveCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(4)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getCode(), savedCategory.getCode());
        assertEquals(expectedCategory.getDesignation(), savedCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(), savedCategory.getIdEntreprise());

    }

    @Test
    public void shouldUpdateCategoryWithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCode("Cat update");

        assertNotNull(categoryToUpdate);
        assertNotNull(categoryToUpdate.getId());
        assertEquals(categoryToUpdate.getCode(), savedCategory.getCode());
        assertEquals(categoryToUpdate.getDesignation(), savedCategory.getDesignation());
        assertEquals(categoryToUpdate.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldThrowInvalidException(){
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> categoryService.save(expectedCategory));
        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, expectedException.getErrorCodes());
        assertEquals(1, expectedException.getErrors().size());
        assertEquals("Veuillez renseigner le code de la categorie", expectedException.getErrors().get(0));


    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> categoryService.findById(0));

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, expectedException.getErrorCodes());
        assertEquals("Aucune category avec l'ID = 0 n' ete trouve dans la BDD", expectedException.getMessage());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2() {
        categoryService.findById(0);
    }
}
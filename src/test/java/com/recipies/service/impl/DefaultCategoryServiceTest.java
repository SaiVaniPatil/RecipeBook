package com.recipies.service.impl;

import com.recipies.exception.ElementNotFoundException;
import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.repository.CategoryRepository;
import com.recipies.repository.RecipeRepository;
import com.recipies.service.impl.DefaultCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultCategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private RecipeRepository recipeRepository;

	@InjectMocks
	private DefaultCategoryServiceImpl categoryService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	void getAllCategories() {

		List<Category> categories = new ArrayList<>();
		Category category1 = new Category(1L, "Main Dish");
		Category category2 = new Category(2L, "Side Dish");
		categories.add(category1);
		categories.add(category2);

		when(categoryRepository.findAll()).thenReturn(categories);

		List<Category> result = categoryService.getAllCategories();

		assertEquals(categories, result);
	}

	@Test
	void getCategoryById() {

		long categoryId = 1L;
		Category category = new Category();

		when(categoryRepository.findById( categoryId)).thenReturn(Optional.of(category));

		Category result = categoryService.getCategoryById(categoryId);

		assertEquals(category, result);
	}

	@Test
	void getCategoryByIdNotFound() {

		long categoryId = 1L;
		when(categoryRepository.findById( categoryId)).thenThrow(ElementNotFoundException.class);

		assertThrows(ElementNotFoundException.class, () -> categoryService.getCategoryById(categoryId));
	}

	
	void getRecipesByCategoryId() {

		long categoryId = 1L;
		Category category = new Category(1L, "Main Dish");
		category.setId(categoryId);
		Recipe recipe1 = new Recipe(1L, "Rice", "10");
		Recipe recipe2 = new Recipe(1L, "Noodles", "20");
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe1);
		recipes.add(recipe2);

		
		when(recipeRepository.findRecipeBycategoriesId(categoryId)).thenReturn(recipes);
		when(categoryRepository.existsById(categoryId)).thenReturn(true);

		List<Recipe> result = categoryService.getRecipesByCategoryId(categoryId);

		assertEquals(recipes, result);
	}

	@Test
	void getRecipesByCategoryIdNotFound() {

		long categoryId = 101L;
		 when(categoryRepository.existsById(categoryId)).thenReturn(false);
		when(recipeRepository.findRecipeBycategoriesId(categoryId)).thenThrow(ElementNotFoundException.class);

		assertThrows(ElementNotFoundException.class, () -> categoryService.getRecipesByCategoryId(categoryId));
	}
}
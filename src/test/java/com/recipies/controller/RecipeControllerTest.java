package com.recipies.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.recipies.model.Recipe;
import com.recipies.service.RecipeService;


public class RecipeControllerTest {

	@Mock
	private RecipeService recipeService;

	@InjectMocks
	private RecipeController recipeController;

	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testGetAllRecipes() {
		 
		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe1 = new Recipe(1L,"Recipe 1","10");
		Recipe recipe2 = new Recipe(2L,"Recipe 2","20");
		recipes.add(recipe1);
		recipes.add(recipe2);

		when(recipeService.getAllRecipes(null, null)).thenReturn(recipes);

		
		ResponseEntity<CollectionModel<Recipe>> response = recipeController.getAllRecipes(null, null);

	 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(recipes.size(), response.getBody().getContent().size());
	}

	@Test
	void testGetRecipeById() {
		 
		long recipeId = 1L;
		Recipe recipe = new Recipe();
		when(recipeService.getRecipeById(recipeId)).thenReturn(recipe);

		
		Recipe result = recipeController.getRecipeById(recipeId);

	 
		assertEquals(recipe, result);
	}

	@Test
	void testGetRecipeByCategoryId() throws Exception {
		 
		long categoryId = 1L;
		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe1 = new Recipe();
		Recipe recipe2 = new Recipe();
		recipes.add(recipe1);
		recipes.add(recipe2);

		when(recipeService.getRecipesByCategoryId(categoryId)).thenReturn(recipes);

		
		List<Recipe> result = recipeController.getRecipeByCategoryId(categoryId);

	 
		assertEquals(recipes, result);
	}

	@Test
	void testCreateRecipe() throws Exception {
		 
		Recipe recipe = new Recipe();
		when(recipeService.saveRecipe(recipe)).thenReturn(recipe);

		
		Recipe result = recipeController.createRecipe(recipe);

	 
		assertEquals(recipe, result);
	}

}

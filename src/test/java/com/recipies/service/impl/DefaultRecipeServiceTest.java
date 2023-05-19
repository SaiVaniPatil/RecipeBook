package com.recipies.service.impl;

import com.recipies.exception.ElementNotFoundException;
import com.recipies.exception.ElementWithSameNameAlreadyExistsException;
import com.recipies.exception.InvalidInputException;
import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.model.RecipeDirections;
import com.recipies.model.RecipeIngredients;
import com.recipies.repository.CategoryRepository;
import com.recipies.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.recipies.service.impl.DefaultRecipeServiceImpl;

class DefaultRecipeServiceTest {

	@Mock
	private RecipeRepository recipeRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private DefaultRecipeServiceImpl recipeService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllRecipes() {

		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe1 = new Recipe(1L, "Main Dish", "10");
		Recipe recipe2 = new Recipe(2L, "Side Dish", "11");
		recipes.add(recipe1);
		recipes.add(recipe2);

		when(recipeRepository.findAll()).thenReturn(recipes);

		List<Recipe> result = recipeService.getAllRecipes();

		assertEquals(recipes, result);
	}

	@Test
	void getAllRecipesNotfound() {

		List<Recipe> recipes = new ArrayList<>();

		when(recipeRepository.findAll()).thenReturn(recipes);

		List<Recipe> result = recipeService.getAllRecipes();

		assertEquals(recipes, result);
	}

	@Test
	void getRecipeById() {

		long recipeId = 1L;
		Optional<Recipe> recipe = Optional.of(new Recipe(1L, "Recipe1", "15"));
		when(recipeRepository.findById(recipeId)).thenReturn(recipe);
		when(recipeRepository.existsById(recipeId)).thenReturn(true);
		
		
		Recipe result = recipeService.getRecipeById(recipeId);

		assertEquals(recipe.get().getName(), result.getName());
	}

	@Test
	void getRecipeByIdNotFound() {

		long recipeId = 1L;
		when(recipeRepository.findById(recipeId)).thenThrow(ElementNotFoundException.class);

		assertThrows(ElementNotFoundException.class, () -> recipeService.getRecipeById(recipeId));
	}

	@Test
	void getRecipesByCategoryId() {

		long categoryId = 1L;
		Recipe recipe1 = new Recipe();
		Recipe recipe2 = new Recipe();
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe1);
		recipes.add(recipe2);

		when(recipeRepository.findRecipeBycategoriesId(categoryId)).thenReturn(recipes);

		when(categoryRepository.existsById(categoryId)).thenReturn(true);

		List<Recipe> result = recipeService.getRecipesByCategoryId(categoryId);

		assertEquals(recipes, result);
	}

	
	void getAllRecipesWithSearchParams() {

		String name = "Pizza";
		String category = "Italian";
		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe1 = new Recipe(1L, "Pizza", "11");
		recipes.add(recipe1);

		Category cat = new Category(1L, "Italian");
		cat.setRecipes(recipes);
		
		List<Category> categories = new ArrayList<>();
		
		
		categories.add(cat);
		
		when(recipeRepository.searchRecipeswithName(name)).thenReturn(recipes);
		when(categoryRepository.searchCategorywithName(category)).thenReturn(categories);

		List<Recipe> result = recipeService.getAllRecipes(name, category);

		assertEquals(1, result.size());
	}

	@Test
	void getAllRecipesWithSearchParamsNoResults() {

		String name = "Rice";
		String category = "Main Dish";

		when(recipeRepository.searchRecipeswithName(name)).thenReturn(List.of());
		when(categoryRepository.searchCategorywithName(category)).thenReturn(List.of());

		assertThrows(ElementNotFoundException.class, () -> recipeService.getAllRecipes(name, category));
	}

	@Test
	void saveRecipeInvalidInput() {
		
		  Recipe recipe = new Recipe(101L, "Recipe1", "6");	
		
		 assertThrows(InvalidInputException.class, ()-> recipeService.saveRecipe(recipe) );	
	
			
		
	}
	
	@Test
	void saveRecipe() {

		Recipe recipe = new Recipe(101L, "Recipe1", "6");
		recipe.addCategory(new Category(20L,"test"));
		RecipeIngredients rig = new RecipeIngredients();
		rig.setItem("tomato");
		RecipeDirections rd = new RecipeDirections();
		rd.setStep("test");
		
		
		Set<RecipeIngredients> rigSet =new HashSet<RecipeIngredients>();
		rigSet.add(rig);
		
		Set<RecipeDirections> rdSet =new HashSet<RecipeDirections>();
		rdSet.add(rd);		
		
		recipe.setRecipeIngredients(rigSet);
		recipe.setRecipeDirectons(rdSet);
		when(recipeRepository.save(recipe)).thenReturn(recipe);
		assertEquals("Recipe1",  recipeService.saveRecipe(recipe).getName());
	}
}

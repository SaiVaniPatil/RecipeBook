package com.recipies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.recipies.model.Recipe;

public interface RecipeService {

	List<Recipe> getAllRecipes();
	
	List<Recipe> getAllRecipes(String name, String category);

	Recipe getRecipeById(Long id);

	// Recipe createRecipe(Recipe recipe);
	
	List<Recipe> getRecipesByCategoryId(Long id);
	
	Recipe saveRecipe(Recipe recipe);
	
	Recipe getRecipeByName(String name);

}

package com.recipies.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.recipies.exception.ElementNotFoundException;
import com.recipies.exception.ElementWithSameNameAlreadyExistsException;
import com.recipies.exception.InvalidInputException;
import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.repository.CategoryRepository;
import com.recipies.repository.RecipeRepository;
import com.recipies.service.RecipeService;

@Component
public class DefaultRecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	private final CategoryRepository categoryRepository;

	@Autowired
	DefaultRecipeServiceImpl(RecipeRepository recipeRepository, CategoryRepository categoryRepository) {
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<Recipe> getAllRecipes() {

		return recipeRepository.findAll();

	}

	public Recipe getRecipeById(Long id) {

		if (!recipeRepository.existsById(id)) {
			throw new ElementNotFoundException("No recipe available with id :" + id);

		}

		return recipeRepository.findById(id).get();

	}

	public List<Recipe> getRecipesByCategoryId(Long id) {
		if (!categoryRepository.existsById(id)) // check this
		{
			throw new ElementNotFoundException("No category is found with category with id=" + id);
		}
		return recipeRepository.findRecipeBycategoriesId(id); // change name to cat

	}

	@Override
	public List<Recipe> getAllRecipes(String name, String category) {

		List<Recipe> recipes = null;

		// System.out.println("name "+name+" category "+category);

		if (name == null && category == null) {
			recipes = getAllRecipes();
		} else if (name != null && category != null) {

			recipes = categoryRepository.searchCategorywithName(category.toUpperCase()).stream()
					.map(cat -> cat.getRecipes()).flatMap(recipeList -> recipeList.stream())
					.filter(recipe -> (recipe.getName().toUpperCase()).contains(name.toUpperCase()))
					.collect(Collectors.toList());

		} else if (name != null && category == null) {
			recipes = recipeRepository.searchRecipeswithName(name.toUpperCase());

		} else if (category != null && name == null) {

			recipes = categoryRepository.searchCategorywithName(category.toUpperCase()).stream()
					.map(cat -> cat.getRecipes()).flatMap(recipeList -> recipeList.stream())
					.collect(Collectors.toList());

		}

		if (recipes == null || recipes.isEmpty()) {
			throw new ElementNotFoundException("No recipe available");
		}

		return recipes;

	}

	public Recipe saveRecipe(Recipe recipe) {

		validateRecipe(recipe);

		handleCategory(recipe);

		return recipeRepository.save(recipe);
	}

	private void handleCategory(Recipe recipe) {
		
		List<Category> recipeCategories = recipe.getCategories();
		List<String> categoryNames = recipeCategories.stream().map(category -> (category.getName().toUpperCase()))
				.collect(Collectors.toList());

		List<Category> categories = categoryRepository.findByNames(categoryNames);

		List<String> existingCategoryNames = categories.stream().map(category -> (category.getName().toUpperCase()))
				.collect(Collectors.toList());

		List<Category> newCategories = recipeCategories.stream()
				.filter(category -> !existingCategoryNames.contains(category.getName().toUpperCase()))
				.collect(Collectors.toList());

		recipe.setCategories(newCategories);

		for (Category c : categories) {
			recipe.addCategory(c);

		}
	}

	private void validateRecipe(Recipe recipe) {

		if (recipe.getName() == null || recipe.getName().isEmpty()) {
			throw new InvalidInputException("Invalid input, please add a valid name for recipe");

		} else if (recipe.getYield() == null || recipe.getYield().isEmpty()) {
			throw new InvalidInputException("Invalid input, please include valid Yield");

		}

		else if (recipe.getRecipeDirectons().isEmpty()) {
			throw new InvalidInputException("Invalid input, please include atleast one step");

		} else if (recipe.getRecipeIngredients().isEmpty()) {
			throw new InvalidInputException("Invalid input, please include atleast one Ingredient");

		} else if (recipe.getCategories().isEmpty()) {
			throw new InvalidInputException("Invalid input, please include atleast one category");

		}

		Recipe existingRecipe = getRecipeByName(recipe.getName());

		if (existingRecipe != null) {
			throw new ElementWithSameNameAlreadyExistsException(
					"Recipe with this name already exists, please create a new one");

		}

	}

	@Override
	public Recipe getRecipeByName(String name) {
		return recipeRepository.findByName(name.toUpperCase());
	}

}

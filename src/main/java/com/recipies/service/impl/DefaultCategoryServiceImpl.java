package com.recipies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.recipies.exception.ElementNotFoundException;
import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.repository.CategoryRepository;
import com.recipies.repository.RecipeRepository;
import com.recipies.service.CategoryService;

@Component
public class DefaultCategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;

	@Autowired
	DefaultCategoryServiceImpl(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
	}

	public List<Category> getAllCategories() {

		return categoryRepository.findAll();

	}

	public Category getCategoryById(Long id){
		
		//System.out.println("In category Service");
		return categoryRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("could not find category with id" + id));

		// .orElseThrow(() -> new Exception("could not find recipe with id" + id));
	}

	public List<Recipe> getRecipesByCategoryId(Long id){

		if (!categoryRepository.existsById(id)) // check this
		{
			throw new ElementNotFoundException("No category is found with category with id=" + id);
		}

		List<Recipe> recipes = recipeRepository.findRecipeBycategoriesId(id);
		return recipes;

	}

}

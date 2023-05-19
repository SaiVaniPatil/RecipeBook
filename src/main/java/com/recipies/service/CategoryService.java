package com.recipies.service;

import java.util.List;

import com.recipies.model.Category;
import com.recipies.model.Recipe;

public interface CategoryService {

	List<Category> getAllCategories();

	Category getCategoryById(Long id) throws Exception;

	//Category createCategory(Category category);
	
	List<Recipe> getRecipesByCategoryId(Long id);
}

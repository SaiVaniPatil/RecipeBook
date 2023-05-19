package com.recipies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.repository.RecipeRepository;
import com.recipies.service.CategoryService;
import com.recipies.service.RecipeService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<Category>> getAllCategories() {
		
		List<Category> categories = categoryService.getAllCategories();
		
		categories.forEach(category -> {			
			try {
				category.add(linkTo(methodOn(CategoryController.class).getRecipesByCategoryId(category.getId())).withRel("categoryRecipes"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    });
		
		Link allCategoriesLink = linkTo(methodOn(CategoryController.class).getAllCategories()).withSelfRel();
	    return ResponseEntity.ok(CollectionModel.of(categories, allCategoriesLink));		
		
	}

	

	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Category getCategoryById(@PathVariable("id") long id)throws Exception  {
			
		return categoryService.getCategoryById(id);

	}
	
	
	@GetMapping("/{id}/recipes")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<CollectionModel<Recipe>> getRecipesByCategoryId(@PathVariable("id") long id) throws Exception{

		//return categoryService.getRecipesByCategoryId(id);
		
		List<Recipe> recipes =categoryService.getRecipesByCategoryId(id);	
		

		recipes.forEach(recipe -> {
			try {
				recipe.add(
						linkTo(methodOn(RecipeController.class).getRecipeById(recipe.getId())).withRel("recipeInfo"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Link allRecipesLink = linkTo(methodOn(RecipeController.class).getAllRecipes(null,null)).withSelfRel();
		return ResponseEntity.ok(CollectionModel.of(recipes, allRecipesLink));

	}

}

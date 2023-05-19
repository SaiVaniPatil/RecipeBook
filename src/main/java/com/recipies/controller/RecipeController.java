package com.recipies.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.recipies.exception.ElementNotFoundException;
import com.recipies.exception.ElementWithSameNameAlreadyExistsException;
import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CollectionModel<Recipe>> getAllRecipes(@RequestParam(value="name",required=false)String name,@RequestParam(value="category",required=false)String category) {
		// return recipeService.getAllRecipes();
		List<Recipe> recipes =recipeService.getAllRecipes(name,category);
	
		

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

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Recipe getRecipeById(@PathVariable("id") long id) {

		return recipeService.getRecipeById(id);

	}
	
	@GetMapping("/categories/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Recipe> getRecipeByCategoryId(@PathVariable("id") long id) throws Exception {

		return recipeService.getRecipesByCategoryId(id);

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe createRecipe(@RequestBody Recipe recipe) throws Exception {
		//System.out.println("recipe_data : "+recipe.getCategories());
		
		//fix categories issue here
			
		return recipeService.saveRecipe(recipe);

	}
	

	
	
}

package com.recipies.controller;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recipies.model.Category;
import com.recipies.model.Recipe;
import com.recipies.repository.CategoryRepositoryTest;
import com.recipies.repository.RecipeRepositoryTest;
import com.recipies.service.CategoryService;
import com.recipies.service.RecipeService;


public class CategoryControllerTest {

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController categoryController;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllCategories() throws Exception {

		List<Category> categories = java.util.Arrays.asList(new Category(1L, "Main Dish"),
				new Category(2L, "Side Dish"));

		when(categoryService.getAllCategories()).thenReturn(categories);

		ResponseEntity<CollectionModel<Category>> response = categoryController.getAllCategories();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		CollectionModel<Category> responseModel = response.getBody();
		assertEquals(categories.size(), responseModel.getContent().size());

	}

	@Test
	void getCategoryByIdTest() throws Exception {

		long categoryId = 1L;
			
		when(categoryService.getCategoryById(categoryId)).thenReturn(new Category(1L, "Category 1"));
		
		Category result = categoryController.getCategoryById(categoryId);

		assertEquals(categoryId, result.getId());
		assertEquals("Category 1", result.getName());
	}

	@Test
	void getRecipesByCategoryId() throws Exception {

		long categoryId = 1L;
		List<Recipe> recipes = java.util.Arrays.asList(new Recipe(1L, "Rice", "10"), new Recipe(2L, "Curry", "10"));

		when(categoryService.getRecipesByCategoryId(categoryId)).thenReturn(recipes);

		ResponseEntity<CollectionModel<Recipe>> response = categoryController.getRecipesByCategoryId(categoryId);
		
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(recipes.size(), response.getBody().getContent().size());

	}

}

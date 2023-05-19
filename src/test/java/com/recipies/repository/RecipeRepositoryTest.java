package com.recipies.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipies.model.Recipe;

@DataJpaTest
public class RecipeRepositoryTest {

	@Autowired
	RecipeRepository recipeRepository;

	@Test
	public void findRecipeById() {

		Long id = 1L;
		Optional<Recipe> recipe = recipeRepository.findById(id);
	
		assertEquals("30 Minute Chili", (recipe.get()).getName());

	}

	@Test
	public void findRecipeByIdNotExists() {

		Long id = 101L;
		Optional<Recipe> recipe = recipeRepository.findById(id);
		assertEquals(Optional.empty(),recipe);

	}


	@Test
	public void findRecipeByName() {

		Recipe recipe = recipeRepository.findByName("30 Minute Chili".toUpperCase());
		assertEquals("30 Minute Chili", recipe.getName());

	}

	@Test
	public void findRecipeByNameNotExists() {

		Recipe recipe = recipeRepository.findByName("30 Minute Chili");
		assertEquals(null, recipe);

	}


}

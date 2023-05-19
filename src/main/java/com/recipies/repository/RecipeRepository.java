package com.recipies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipies.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	List<Recipe> findRecipeBycategoriesId(Long categoryId);

	@Query("select r from Recipe r where " + "Upper(r.name) like :name")
	Recipe findByName(String name);

	@Query("select r from Recipe r where " + "r.name like CONCAT('%',:name, '%')")
	List<Recipe> searchRecipeswithName(String name);

	
}

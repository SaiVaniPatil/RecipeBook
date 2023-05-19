package com.recipies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipies.model.RecipeIngredients;

import jakarta.transaction.Transactional;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> { 
	

}

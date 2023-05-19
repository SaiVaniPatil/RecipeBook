package com.recipies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipies.model.RecipeDirections;


import jakarta.transaction.Transactional;

public interface RecipeDirectionsRepository extends JpaRepository<RecipeDirections, Long> { 
	

}

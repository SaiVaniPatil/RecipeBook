package com.recipies.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class RecipeDirections {
	
	 private static final long serialVersionUID = 3252591505029724240L; 
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private Long stepId;
	 
	 @Lob
	 private String step;
	 
	
	public RecipeDirections() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

//	public Recipe getRecipe() {
//		return recipe;
//	}
//
//	public void setRecipe(Recipe recipe) {
//		this.recipe = recipe;
//	}

	public RecipeDirections(Long id, Long stepId, String step) {
		super();
		this.id = id;
		this.stepId = stepId;
		this.step = step;
		//this.recipe = recipe;
	}
	 
	 
	 

}

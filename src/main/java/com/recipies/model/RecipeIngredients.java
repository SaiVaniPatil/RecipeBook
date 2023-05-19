package com.recipies.model;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RecipeIngredients implements Serializable {

	private static final long serialVersionUID = 3252591505029724239L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String qty;
	private String unit;
	private String item;

	public RecipeIngredients() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RecipeIngredients(Long id, String title, String qty, String unit, String item) {
		super();
		this.id = id;
		this.title = title;
		this.qty = qty;
		this.unit = unit;
		this.item = item;
		// this.recipe = recipe;
	}

//	public Recipe getRecipe() {
//		return recipe;
//	}
//
//	public void setRecipe(Recipe recipe) {
//		this.recipe = recipe;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}

package com.recipies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;

@Entity
public class Recipe extends RepresentationModel<Recipe>  implements Serializable {

	private static final long serialVersionUID = 3252591505029724237L;

	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator ="receipe_id_generator")
	@SequenceGenerator(name="receipe_id_generator", sequenceName = "recipe_seq",initialValue = 5)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	@Nonnull
	private String yield;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="recipe_id")	
	private Set<RecipeIngredients> recipeIngredients = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="recipe_id")	
	private Set<RecipeDirections> recipeDirectons = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_category", joinColumns = { @JoinColumn(name = "recipe_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })	
	private List<Category> categories = new ArrayList<>();

	public Recipe(Long id, String name, String yield) {
		super();
		this.id = id;
		this.name = name;
		this.yield = yield;
	}
	
	
	

	public Recipe(Long id, String name, String yield, Set<RecipeIngredients> recipeIngredients,
			Set<RecipeDirections> recipeDirectons, List<Category> categories) {
		super();
		this.id = id;
		this.name = name;
		this.yield = yield;
		this.recipeIngredients = recipeIngredients;
		this.recipeDirectons = recipeDirectons;
		this.categories = categories;
	}
	
	public List<Category> getCategories() {
		return categories;
	}




	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void addCategory(Category cat) {
		this.categories.add(cat);
		cat.getRecipes().add(this);
	 }


	public Recipe()
	{
		
	}




//	public Set<Category> getCategories() {
//
//		return categories;
//	}
//
//	public void setCategories(Set<Category> categories) {
//		
//		System.out.println("In Recipe class : "+ categories);
//		this.categories = categories;
//	}

//	public void addCategory(Category category) {
//		this.categories.add(category);
//		category.getRecipes().add(this);
//	}
//
//	public void removeCategory(long categoryId) {
//		Category category = this.categories.stream().filter(cat -> cat.getId() == categoryId).findFirst().orElse(null);
//		if (category != null) {
//			this.categories.remove(category);
//			category.getRecipes().remove(this);
//		}
//	}

	public Set<RecipeIngredients> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(Set<RecipeIngredients> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}

	public Set<RecipeDirections> getRecipeDirectons() {
		return recipeDirectons;
	}

	public void setRecipeDirectons(Set<RecipeDirections> recipeDirectons) {
		this.recipeDirectons = recipeDirectons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", yield=" + yield + "]";
	}

}

package com.recipies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
public class Category extends RepresentationModel<Category> implements Serializable {
	
	 private static final long serialVersionUID = 3252591505029724236L;
	 
	 
	 @Id
	 //@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator ="category_id_generator")
	 @SequenceGenerator(name="category_id_generator", sequenceName = "category_seq",initialValue =10)
	 private Long id;
	 
	 @Column(unique = true)
	 private String name;
	 
	 
	  @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "categories")
	  @JsonIgnore	  
	  private List<Recipe> recipes = new ArrayList<>();
	 
	 public List<Recipe> getRecipes() {
		return recipes;
	}


	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}


	public Category()
	 {
		 
	 }
	 
	 
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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


	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	
	 
	
	
	 

}

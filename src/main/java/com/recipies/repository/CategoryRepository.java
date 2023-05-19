package com.recipies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipies.model.Category;
import com.recipies.model.Recipe;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	boolean existsById(Long id);
	
	@Query("select c from Category c where "+"Upper(c.name) like :name")	
	List<Category> findByName(String name);
	
	@Query("select c from Category c where "+"Upper(c.name) in (:name)")	
	List<Category> findByNames(List<String> name);
	
	@Query("select c from Category c where "+"Upper(c.name) like CONCAT('%',:name, '%')")	
	List<Category> searchCategorywithName(String name);
	
	

}

package com.recipies.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.recipies.model.Category;
import com.recipies.model.Recipe;

@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	public void testCategoryExists() {

		Long id = 1L;
		boolean exists = categoryRepository.existsById(id);
		assertEquals(true, exists);

	}

	@Test
	public void testCategoryNotExists() {

		Long id = 101L;
		boolean exists = categoryRepository.existsById(id);
		assertEquals(false, exists);

	}

}

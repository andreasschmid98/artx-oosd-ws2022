package de.hsaugsburg.oosd.artx.services;

import de.hsaugsburg.oosd.artx.repositories.CategoryRepository;
import de.hsaugsburg.oosd.artx.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }
}

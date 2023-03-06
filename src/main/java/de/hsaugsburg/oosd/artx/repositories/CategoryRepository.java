package de.hsaugsburg.oosd.artx.repositories;

import de.hsaugsburg.oosd.artx.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}

package de.hsaugsburg.oosd.artx.servicelayer;

import de.hsaugsburg.oosd.artx.models.Category;
import de.hsaugsburg.oosd.artx.repositories.CategoryRepository;
import de.hsaugsburg.oosd.artx.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(Category.CategoryType.MALEREI, "Malerei");
        categoryRepository.save(category);
    }

    @Test
    void testFindCategoryByName() {
        given(categoryRepository.findCategoryByName("Malerei")).willReturn(category);
        Category savedCategory = categoryService.findCategoryByName("Malerei");
        assertThat(savedCategory).isEqualTo(category);
    }
}


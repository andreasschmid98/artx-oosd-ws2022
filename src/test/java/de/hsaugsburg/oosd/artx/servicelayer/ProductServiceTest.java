package de.hsaugsburg.oosd.artx.servicelayer;

import de.hsaugsburg.oosd.artx.models.Category;
import de.hsaugsburg.oosd.artx.models.Product;
import de.hsaugsburg.oosd.artx.repositories.CategoryRepository;
import de.hsaugsburg.oosd.artx.repositories.ProductRepository;
import de.hsaugsburg.oosd.artx.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Product product, product2;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(Category.CategoryType.MALEREI, "Malerei");
        categoryRepository.save(category);
        product = new Product(
                "test",
                "test",
                new BigDecimal(1),
                "2000",
                category,
                "test",
                "test"
        );

        product2 = new Product(
                "test2",
                "test2",
                new BigDecimal(2),
                "2000",
                category,
                "test",
                "test"
        );
    }

    @Test
    void testFindProductsByCategory() {
        given(productRepository.findProductsByCategory(category)).willReturn(List.of(product));
        List<Product> products = productService.findProductsByCategory(product.getCategory());
        assertThat(products.get(0)).isEqualTo(product);
    }

    @Test
    void testGetSumOfProductPrices() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        assertThat(productService.getSumOfProductPrices(products)).isEqualTo(new BigDecimal(3));
    }
}
